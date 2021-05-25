package com.zodiac.user1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.IOException;

public class SingleNewsActivity extends AppCompatActivity {

    TextView titleOfNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_news);
        titleOfNews = findViewById(R.id.titleOfNews);
//        titleOfNews.setText(getIntent().getStringExtra(MyAdapter.class));
    }
    public boolean parse(String xmlData) {
        try {
            Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
