package com.ealarcon.app.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Model for a board page
 * Created by ealarcon on 6/22/17.
 */

public class BoardPage implements Serializable {

    private int page;
    private List<BoardThread> threads;

    public int getPage() {
        return page;
    }

    public List<BoardThread> getThreads() {
        return threads;
    }
}
