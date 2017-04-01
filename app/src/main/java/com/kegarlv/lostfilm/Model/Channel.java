package com.kegarlv.lostfilm.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by ivan on 04.02.17.
 */

public class Channel {

    @Element(name = "description")
    String description;

    @Element(name = "link")
    String link;

    @Element(name = "lastBuildDate")
    String lastBuildDate;

    @Element(name = "title")
    String title;

    @Element(name = "language")
    String language;

    @ElementList(name = "item", required = true, inline = true)
    List<Item> itemList;

    public Channel() {
    }

    public List<Item> getItemList() {

        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


}
