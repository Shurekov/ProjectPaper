package com.zodiac.user1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<String> messages;  //  Храниние строк

    LayoutInflater inflater;

    public DataAdapter(Context context, ArrayList<String> messages) {   //  Получение возможности от активности использовать LayoutInflater, для создания объекта
        this.messages = messages;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.element_message, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {    //  Метод отвечающий за прокрутку
        String msg = messages.get(position);
        holder.message.setText(msg);
    }

    @Override
    public int getItemCount() {   //  Метод сообщающий кол-во элементов, для корректного отображения ленты
        return messages.size();
    }
}
