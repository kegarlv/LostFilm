package com.kegarlv.lostfilm.Model;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.kegarlv.lostfilm.App;
import com.squareup.picasso.Picasso;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.IOException;

/**
 * Created by ivan on 04.02.17.
 */

@Root(name = "item")
public class Item {

    @Element(name = "title")
    String title;

    @Element(name = "link")
    String link;

    @Element(name = "description")
    String description;

    @Element(name = "pubDate")
    String pubDate;

    public Item() {
    }

    public String getPubDate() {
        return pubDate.substring(0,pubDate.length()-6);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description.substring(10, description.length() - 17);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (title != null ? !title.equals(item.title) : item.title != null) return false;
        if (link != null ? !link.equals(item.link) : item.link != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null)
            return false;
        return pubDate != null ? pubDate.equals(item.pubDate) : item.pubDate == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        return result;
    }
}
