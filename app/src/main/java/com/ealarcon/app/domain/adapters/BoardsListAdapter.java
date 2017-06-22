package com.ealarcon.app.domain.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ealarcon.app.R;
import com.ealarcon.app.domain.MainActivity;
import com.ealarcon.app.domain.models.Board;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter that renders the item representing a board
 * Created by ealarcon on 6/22/17.
 */

public class BoardsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Board> mBoards;
    private Context mContext;

    public BoardsListAdapter(Context context, List<Board> boards) {
        mBoards = boards;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.board_item_view, parent, false);

        return new BoardViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BoardViewHolder itemHolder = (BoardViewHolder) holder;
        final Board board = mBoards.get(position);

        itemHolder.itemRoot.setOnClickListener(v -> ((MainActivity) mContext).getPresenter().
                loadBoardThreads(board.getId()));

        itemHolder.nameLabel.setText(board.getTitle());
        itemHolder.pagesLabel.setText(String.format("%d pages", board.getPages()));
        itemHolder.descriptionLabel.setText(Html.fromHtml(board.getDescription()));
    }

    @Override
    public int getItemCount() {
        return mBoards.size();
    }

    public static class BoardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_root)
        View itemRoot;

        @BindView(R.id.board_name)
        TextView nameLabel;

        @BindView(R.id.board_pages)
        TextView pagesLabel;

        @BindView(R.id.board_description)
        TextView descriptionLabel;

        public BoardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
