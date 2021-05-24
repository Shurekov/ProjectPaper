package com.zodiac.user1;

public class Item {
    private String title;
    private String description;
    private String pubDate;

    public Item(String title, String description, String pubDate) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
    }

    public Item(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String toString() {
        return  "Заголовок: " + title + "\n" + description +"\nДата:"+pubDate ;
    }
}
