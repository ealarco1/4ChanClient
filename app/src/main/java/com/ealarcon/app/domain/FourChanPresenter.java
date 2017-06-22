package com.ealarcon.app.domain;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Presenter that coordinates vew and interactor
 * Created by ealarcon on 6/22/17.
 */

public class FourChanPresenter {

    private FourChanView mView;
    private FourChanInteractor mInteractor;
    private CompositeSubscription mSubscriptions;

    public FourChanPresenter(FourChanView view, FourChanInteractor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
        this.mSubscriptions = new CompositeSubscription();
    }

    public void loadBoards() {
        mView.showLoading();
        mSubscriptions.add(
                mInteractor.fetchBoards()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(boards -> {
                            mView.hideLoading();
                            mView.populateBoards(boards);
                        }, throwable -> {
                            mView.hideLoading();
                            mView.showError();
                        })
        );
    }

    public void loadBoardThreads(String id) {
        mView.showLoading();
        mSubscriptions.add(
                mInteractor.fetchBoardThreads(id)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(threads -> {
                            mView.populateBoardThreads(threads);
                            mView.hideLoading();
                        }, throwable -> {
                            mView.hideLoading();
                            mView.showError();
                        })
        );
    }
}
