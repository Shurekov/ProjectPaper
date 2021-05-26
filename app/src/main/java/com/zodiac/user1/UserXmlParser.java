package com.zodiac.user1;

import android.provider.Settings;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.util.ArrayList;
import java.io.StringReader;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;

public class UserXmlParser {

    private ArrayList<Item> items;

    public UserXmlParser(){
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public boolean parse(String xmlData) {
        if (xmlData == null)
            return false;
        Document doc = Jsoup.parse(xmlData, "", Parser.xmlParser());
        for (Element e : doc.select("item")) {
            items.add(new Item( e.selectFirst("title").text(),
                                e.selectFirst("description").text().replace("&#160;",""),
                                e.selectFirst("pubDate").text().replace("+0000",""),
                                e.selectFirst("link").text()));
        }
        return true;
    }
}