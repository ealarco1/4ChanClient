package com.ealarcon.app.domain;

import com.ealarcon.app.domain.models.Board;
import com.ealarcon.app.domain.models.BoardPage;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Interactor interface that defines the data fetching operations
 * Created by ealarcon on 6/22/17.
 */

interface FourChanInteractor {
    Observable<List<Board>> fetchBoards();

    Observable<ArrayList<BoardPage>> fetchBoardThreads(String id);
}
