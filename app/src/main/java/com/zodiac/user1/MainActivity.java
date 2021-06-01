package com.zodiac.user1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private TextView testText;
    private ListView listView;
    Button chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        chat = findViewById(R.id.chatbutton);
        testText = findViewById(R.id.testtext);
        registerReceiver(receiver, new IntentFilter(GisService.CHANNEL));
        final Intent intent = new Intent(this, GisService.class);
        startService(intent);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, GisService.class);
        stopService(intent);
    }

    protected BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String content = intent.getStringExtra(GisService.INFO);
            listView.post(new Runnable() {
                public void run() {
                    UserXmlParser parser = new UserXmlParser();
                    if(parser.parse(content)) {
                        ArrayAdapter<Item> adapter = new MyAdapter(getBaseContext(),
                                R.layout.list_item_layout, parser.getItems().toArray(new Item[0]));
                        listView.setAdapter(adapter);
                    }
                }
            });
        }
    };
}