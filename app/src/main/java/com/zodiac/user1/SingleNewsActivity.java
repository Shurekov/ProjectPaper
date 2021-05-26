package com.zodiac.user1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class SingleNewsActivity extends AppCompatActivity {

    TextView titleOfNews;
    TextView mainNews;
    Bundle bundle;
    String news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_news);
        titleOfNews = findViewById(R.id.titleOfNews);
        mainNews = findViewById(R.id.news);
        bundle = getIntent().getExtras();
        news = bundle.get("link").toString();

        NewsAsyncTask newsAsyncTask = new NewsAsyncTask();
        newsAsyncTask.execute();
    }

    private class NewsAsyncTask extends AsyncTask<Void, Void, Document> {

        @Override
        protected void onPostExecute(Document doc) {
            Elements listNews = doc.select("div.entry-content");
            System.out.println(listNews);
            for (Element element : listNews.select("p")) {
                mainNews.setText(mainNews.getText() + element.text() + "\n\n");
            }

            Element title = doc.selectFirst("h1.entry-title");
            titleOfNews.setText(title.text());
            titleOfNews.setTextSize(28);
        }

        @Override
        protected Document doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(news).get();
                return doc;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
