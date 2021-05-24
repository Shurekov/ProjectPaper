package com.zodiac.user1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyAdapter extends ArrayAdapter<Item> {


    public Context myContext;
    public MyAdapter(@NonNull Context context, int resource, @NonNull Item[] objects) {
        super(context, resource, objects);
        myContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(myContext);
            convertView = inflater.inflate(R.layout.list_item_layout,parent,false);
        }
        Item item = getItem(position);
        if (item != null){
            TextView title  = convertView.findViewById(R.id.title);
            title.setText(item.getTitle());
            TextView description  = convertView.findViewById(R.id.description);
            description.setText(item.getDescription());
            TextView pubDate  = convertView.findViewById(R.id.pubDate);
            pubDate.setText(item.getPubDate());
        }
        System.out.println(convertView.getHeight());
        return convertView;
    }
}
