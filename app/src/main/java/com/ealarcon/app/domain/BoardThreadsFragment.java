package com.ealarcon.app.domain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ealarcon.app.R;
import com.ealarcon.app.domain.adapters.BoardsThreadsPageAdapter;
import com.ealarcon.app.domain.models.BoardPage;
import com.ealarcon.app.domain.models.BoardThread;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Fragment that shows the threads catalog of a board
 * Created by ealarcon on 6/22/17.
 */

public class BoardThreadsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    public static final String PAGES = "pages";

    private ViewHolder mViewHolder;
    private List<BoardPage> mPages;
    private List<BoardThread> mThreads;
    private List<Integer> mPagesNumbers;
    private BoardsThreadsPageAdapter mThreadsAdapter;

    public static BoardThreadsFragment newInstance(ArrayList<BoardPage> pages) {
        BoardThreadsFragment fragment = new BoardThreadsFragment();
        Bundle args = new Bundle();
        args.putSerializable(PAGES, pages);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mPages = (List<BoardPage>) args.getSerializable(PAGES);
            //This extracts the pages numbers from the whole object to be passed
            //later to the pages spinner adapter
            Observable.just(mPages)
                    .flatMapIterable(list -> list)
                    .map(item -> item.getPage())
                    .toList()
                    .subscribe(list -> mPagesNumbers = list);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_thread_pages, container, false);
        mViewHolder = new ViewHolder(rootView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mViewHolder.threadsPage.setLayoutManager(layoutManager);

        mThreads = new ArrayList<>();

        mThreadsAdapter = new BoardsThreadsPageAdapter(mThreads);
        mViewHolder.threadsPage.setAdapter(mThreadsAdapter);

        //Spinner adapter is initialized with the page numbers list extracted earlier
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, mPagesNumbers);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mViewHolder.spinner.setAdapter(spinnerAdapter);
        mViewHolder.spinner.setOnItemSelectedListener(this);

        mViewHolder.spinner.setSelection(0);

        //Shows the back arrow because is the second fragment
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mThreads.clear();
        mThreads.addAll(mPages.get(position).getThreads());
        mThreadsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    static class ViewHolder {

        @BindView(R.id.page_spinner)
        Spinner spinner;

        @BindView(R.id.threads_page)
        RecyclerView threadsPage;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
