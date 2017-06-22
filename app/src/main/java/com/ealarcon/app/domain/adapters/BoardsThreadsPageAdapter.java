package com.ealarcon.app.domain.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ealarcon.app.R;
import com.ealarcon.app.domain.models.BoardThread;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter that renders the item representing a board thread
 * Created by ealarcon on 6/22/17.
 */

public class BoardsThreadsPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BoardThread> mThreads;

    public BoardsThreadsPageAdapter(List<BoardThread> threads) {
        mThreads = threads;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.thread_item_view, parent, false);

        return new ThreadViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ThreadViewHolder itemHolder = (ThreadViewHolder) holder;
        final BoardThread thread = mThreads.get(position);

        itemHolder.nameLabel.setText(String.format("Started by: %s", thread.getUser()));
        itemHolder.titleLabel.setText(thread.getTitle() != null ? Html.fromHtml(thread.getTitle()) : "");
        itemHolder.descriptionLabel.setText(Html.fromHtml(thread.getDescription() != null ? thread.getDescription() : ""));
    }

    @Override
    public int getItemCount() {
        return mThreads.size();
    }


    public static class ThreadViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thread_user)
        TextView nameLabel;

        @BindView(R.id.thread_title)
        TextView titleLabel;

        @BindView(R.id.thread_description)
        TextView descriptionLabel;

        public ThreadViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
