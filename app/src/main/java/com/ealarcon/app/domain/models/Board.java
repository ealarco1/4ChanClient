package com.ealarcon.app.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model for a board
 * Created by ealarcon on 6/22/17.
 */

public class Board {

    @SerializedName("board")
    private String id;
    private String title;
    private int pages;
    @SerializedName("meta_description")
    private String description;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public String getDescription() {
        return description;
    }
}
