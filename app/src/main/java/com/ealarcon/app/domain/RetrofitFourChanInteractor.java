package com.ealarcon.app.domain;

import com.ealarcon.app.domain.models.Board;
import com.ealarcon.app.domain.models.BoardPage;
import com.ealarcon.app.domain.services.FourChanService;
import com.ealarcon.app.util.ServiceFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Interactor implementation for retrofit client
 * Created by ealarcon on 6/22/17.
 */

class RetrofitFourChanInteractor implements FourChanInteractor {

    private final FourChanService service;
    private Gson gson;

    public RetrofitFourChanInteractor() {
        this.service = new ServiceFactory.Builder()
                .withBaseUrl("https://a.4cdn.org/")
                .buildService(FourChanService.class);
        gson = new Gson();
    }

    @Override
    public Observable<List<Board>> fetchBoards() {
        return service.fetchBoards().map(response -> gson.fromJson(response.get("boards"), new TypeToken<List<Board>>() {}.getType()));
    }

    @Override
    public Observable<ArrayList<BoardPage>> fetchBoardThreads(String id) {
        return service.fetchBoardItems(id);
    }
}
