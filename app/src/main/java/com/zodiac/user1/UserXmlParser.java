package com.zodiac.user1;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.util.ArrayList;
import java.io.StringReader;

public class UserXmlParser {

    private ArrayList<Item> items;

    public UserXmlParser(){
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    public boolean parse(String xmlData){
        boolean status = true;
        Item currentItem = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(xmlData));
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
                            } else if("description".equalsIgnoreCase(tagName)){
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
        return  status;
    }
}