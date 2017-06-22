package com.ealarcon.app.domain.services;

import com.ealarcon.app.domain.models.BoardPage;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 4Chan API services interface
 * Created by ealarcon on 6/22/17.
 */

public interface FourChanService {

    /**
     * Returns the boards on 4Chan
     * @return boards list wrapped in a json with a single element
     */
    @GET("boards.json")
    Observable<JsonObject> fetchBoards();

    /**
     * Returns the threads catalog for a given board
     * @param boardId of the board which catalog is being requested
     * @return board threads catalog
     */
    @GET("{boardId}/catalog.json")
    Observable<ArrayList<BoardPage>> fetchBoardItems(@Path("boardId") String boardId);
}
