package com.zodiac.user1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SingleNewsActivity extends AppCompatActivity {

    TextView titleOfNews;
    TextView mainNews;
    Bundle bundle = getIntent().getExtras();
//   String news = getIntent().getStringExtra("link");
    String news = bundle.get("link").toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_news);
        titleOfNews = findViewById(R.id.titleOfNews);
        mainNews = findViewById(R.id.news);
//        titleOfNews.setText(getIntent().getStringExtra(MyAdapter.class));
    }
    public boolean parse(String xmlData) {
        try {
            Document doc = Jsoup.connect(news).get();
            Elements listNews = doc.select("div class.entry-content");
            for (Element element : listNews.select("p")){
                mainNews.setText(element.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
