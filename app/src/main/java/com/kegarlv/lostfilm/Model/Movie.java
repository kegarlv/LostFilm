package com.kegarlv.lostfilm.Model;

import java.util.List;

/**
 * Created by ivan on 13.02.17.
 */

public class Movie {
    public String getName_ru() {
        return name_ru;
    }

    public void setName_ru(String name_ru) {
        this.name_ru = name_ru;
    }

    String name_ru;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    String link;
    int rating;
    int year;

    List<String> channels;
    List<String> genres;

    public Movie() {
    }
}
