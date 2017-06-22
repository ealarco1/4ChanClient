package com.ealarcon.app.domain;

import com.ealarcon.app.domain.models.Board;
import com.ealarcon.app.domain.models.BoardPage;

import java.util.ArrayList;
import java.util.List;

/**
 * View interface that defines the information display operations
 * Created by ealarcon on 6/22/17.
 */

interface FourChanView {
    void showLoading();

    void hideLoading();

    void populateBoards(List<Board> boards);

    void populateBoardThreads(ArrayList<BoardPage> threads);

    void showError();
}
