package com.zodiac.user1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private static int MAX_MESSAGE_SIZE = 100;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("messages");

    EditText EditTextMessage;
    Button SendButton;
    RecyclerView MessagesRecycler;

    ArrayList<String> messages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        SendButton = findViewById(R.id.send_message_b);
        EditTextMessage = findViewById(R.id.message_input);
        MessagesRecycler = findViewById(R.id.messages_recycler);

        MessagesRecycler.setLayoutManager(new LinearLayoutManager(this));


        final DataAdapter dataAdapter = new DataAdapter(this, messages);

        MessagesRecycler.setAdapter(dataAdapter);


        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = EditTextMessage.getText().toString();

                if (msg.equals("")) {
                    Toast.makeText(getApplicationContext(), "Введите сообщение.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(msg.length() > MAX_MESSAGE_SIZE){
                    Toast.makeText(getApplicationContext(), "Сообщение превысило 100 символов.", Toast.LENGTH_SHORT).show();
                    return;
                }

                myRef.push().setValue(msg);
                EditTextMessage.setText("");

            }
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String msg = dataSnapshot.getValue(String.class);
                messages.add(msg);
                dataAdapter.notifyDataSetChanged();
                MessagesRecycler.smoothScrollToPosition(messages.size());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
}