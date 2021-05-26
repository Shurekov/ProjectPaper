package com.zodiac.user1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.zodiac.user1.FirstActivity.context;

public class MyAdapter extends ArrayAdapter<Item>  {


    public Context myContext;

        public MyAdapter(@NonNull Context context, int resource, @NonNull Item[] objects) {
        super(context, resource, objects);
        myContext = context;
//        this.c = c;

    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(myContext);
            convertView = inflater.inflate(R.layout.list_item_layout,parent,false);
        }
        final Item item = getItem(position);
        if (item != null){
            TextView title  = convertView.findViewById(R.id.title);
            title.setText(item.getTitle()+ '\n');
            TextView description  = convertView.findViewById(R.id.description);
            description.setText(item.getDescription());
            TextView pubDate  = convertView.findViewById(R.id.pubDate);
            pubDate.setText(item.getPubDate());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myContext,SingleNewsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("link", item.getLink());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                myContext.startActivity(intent);

            }
        });
//        System.out.println(convertView.getHeight());
        return convertView;
    }
}
