package com.news.newsapp.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.news.newsapp.R
import com.news.newsapp.api.response.Response
import com.news.newsapp.databinding.RowNewsListBinding
import java.util.*

class NewsListAdapter(private val context: Context, private val arrayList: ArrayList<Response.Article>) : RecyclerView
.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<RowNewsListBinding>(LayoutInflater.from(context),
                R.layout.row_news_list, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.model = arrayList.get(position)
        holder.binding.adapter = this
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(binding: RowNewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: RowNewsListBinding = binding;
    }

    public fun onItemClick(item: Response.Article) {
        // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
// Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
// and launch the desired Url with CustomTabsIntent.launchUrl()


        val url = item.url;
        if (url.isNotBlank()) {
            val builder = CustomTabsIntent.Builder();
            val customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(url));
        }
    }
}