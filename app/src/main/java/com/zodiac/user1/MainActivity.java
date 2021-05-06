package com.zodiac.user1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private TextView testText;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listView = findViewById(R.id.listView);
        testText = findViewById(R.id.testText);
        registerReceiver(receiver, new IntentFilter(GisService.CHANNEL));
        Intent intent = new Intent(this, GisService.class);
        startService(intent);

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

            boolean status = true;
            Item currentItem = null;
            boolean inEntry = false;
            String textValue = "";
            ArrayList<Item> items = new ArrayList<Item>();

            try{
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                testText.setText(intent.getStringExtra(GisService.INFO));
                xpp.setInput(new StringReader(intent.getStringExtra(GisService.INFO)));
                int eventType = xpp.getEventType();
                while(eventType != XmlPullParser.END_DOCUMENT){

                    String tagName = xpp.getName();
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            if("items".equalsIgnoreCase(tagName)){
                                inEntry = true;
                                currentItem = new Item();
                            }
                            break;
                        case XmlPullParser.TEXT:
                            textValue = xpp.getText();
                            break;
                        case XmlPullParser.END_TAG:
                            if(inEntry){
                               if("items".equalsIgnoreCase(tagName)){
                                    items.add(currentItem);
                                    inEntry = false;} else if("title".equalsIgnoreCase(tagName)){
                                    currentItem.setTitle(textValue);
                                } else if("comments".equalsIgnoreCase(tagName)){
                                    currentItem.setDescription(textValue);
                                }else if("pubDate".equalsIgnoreCase(tagName)){
                                    currentItem.setPubDate(textValue);
                                }
                            }
                            break;
                        default:
                    }
                    eventType = xpp.next();
                }
            }
            catch (Exception e){
                status = false;
                e.printStackTrace();
            }
        }
    };
}