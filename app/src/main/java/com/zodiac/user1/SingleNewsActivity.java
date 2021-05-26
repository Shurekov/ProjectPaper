package com.zodiac.user1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SingleNewsActivity extends AppCompatActivity {

    TextView titleOfNews;
    TextView mainNews;
    Bundle bundle;
//   String news = getIntent().getStringExtra("link");
    String news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_news);
        titleOfNews = findViewById(R.id.titleOfNews);
        mainNews = findViewById(R.id.news);
        bundle = getIntent().getExtras();
//   String news = getIntent().getStringExtra("link");
        news = bundle.get("link").toString();
        NewsAsynсTask newsAsynсTask = new NewsAsynсTask();
        newsAsynсTask.execute();
        mainNews.setText(ыуыуыу.text());
//        titleOfNews.setText(getIntent().getStringExtra(MyAdapter.class));
        
    }

    private class NewsAsynсTask extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            try {
            Document doc = Jsoup.connect(news).get();
            Elements listNews = doc.select("div class.entry-content");
                for (Element element : listNews.select("p")){
                    element = element + li
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
            return null;
        }
    }



}
