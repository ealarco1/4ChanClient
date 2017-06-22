package com.ealarcon.app.domain;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ealarcon.app.R;
import com.ealarcon.app.domain.adapters.BoardsListAdapter;
import com.ealarcon.app.domain.models.Board;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment that shows the boards list
 * Created by ealarcon on 6/22/17.
 */

public class BoardsFragment extends Fragment {

    private BoardsFragmentListener mListener;
    private ViewHolder mViewHolder;
    private List<Board> mBoards;
    private BoardsListAdapter mAdapter;

    public static BoardsFragment newInstance() {
        return new BoardsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_boards_list, container, false);
        mViewHolder = new ViewHolder(rootView);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mViewHolder.recyclerView.setLayoutManager(layoutManager);

        mBoards = new ArrayList<>();

        mAdapter = new BoardsListAdapter(getActivity(), mBoards);
        mViewHolder.recyclerView.setAdapter(mAdapter);

        //Hides the back arrow because is the first fragment
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (BoardsFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement LoadMorBoardsFragmentListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mListener.onFragmentReady();
    }

    public void addBoards(List<Board> boards) {
        mBoards.clear();
        mBoards.addAll(boards);
        mAdapter.notifyDataSetChanged();
    }

    public interface BoardsFragmentListener {

        void onFragmentReady();
    }

    static class ViewHolder {
        @BindView(R.id.board_list)
        RecyclerView recyclerView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
