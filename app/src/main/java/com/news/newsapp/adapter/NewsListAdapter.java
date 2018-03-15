package com.news.newsapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.news.newsapp.R;
import com.news.newsapp.databinding.RowNewsListBinding;

/**
 * Created by Deepak Gehlot on 3/15/2018.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private Context context;

    public NewsListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowNewsListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.row_news_list,
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RowNewsListBinding binding;

        public ViewHolder(RowNewsListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}