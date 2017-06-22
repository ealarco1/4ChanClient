package com.ealarcon.app.domain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.ealarcon.app.R;
import com.ealarcon.app.domain.models.Board;
import com.ealarcon.app.domain.models.BoardPage;
import com.ealarcon.app.util.FeedbackHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main activity of the app that implements the view interface
 * Created by ealarcon on 6/22/17.
 */
public class MainActivity extends AppCompatActivity implements FourChanView, BoardsFragment.BoardsFragmentListener {

    @BindView(R.id.root)
    View rootView;

    private ViewHolder mViewHolder;
    private FourChanPresenter mPresenter;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mViewHolder = new ViewHolder(rootView);

        mPresenter = new FourChanPresenter(this, new RetrofitFourChanInteractor());

        mFragmentManager = getSupportFragmentManager();

        setupInitialFragment();
    }

    //Provides back navigation when the threads fragment is open
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupInitialFragment() {
        BoardsFragment fragment = BoardsFragment.newInstance();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showLoading() {
        mViewHolder.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mViewHolder.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void populateBoards(List<Board> boards) {
        BoardsFragment fragment = getFragment(BoardsFragment.class);
        if (fragment != null) fragment.addBoards(boards);
    }

    @Override
    public void populateBoardThreads(ArrayList<BoardPage> threads) {
        BoardThreadsFragment fragment = BoardThreadsFragment.newInstance(threads);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void showError() {
        FeedbackHelper.buildSnackBar(rootView, R.string.error_unexpected).show();
    }

    @Override
    public void onFragmentReady() {
        mPresenter.loadBoards();
    }

    public FourChanPresenter getPresenter() {
        return mPresenter;
    }

    static class ViewHolder {
        @BindView(R.id.progress_bar)
        ProgressBar progressBar;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Nullable
    private <T extends Fragment> T getFragment(Class<T> type) {
        Fragment fragment = mFragmentManager.findFragmentById(R.id.fragment_container);
        try {
            return type.cast(fragment);
        } catch (ClassCastException ex) {
            return null;
        }
    }
}
