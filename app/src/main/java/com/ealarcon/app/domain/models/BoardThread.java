package com.ealarcon.app.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Model for a board thread
 * Created by ealarcon on 6/22/17.
 */

public class BoardThread {

    @SerializedName("name")
    private String user;
    @SerializedName("sub")
    private String title;
    @SerializedName("com")
    private String description;

    public String getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
