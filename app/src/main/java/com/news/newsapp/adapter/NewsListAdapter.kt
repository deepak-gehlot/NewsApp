package com.news.newsapp.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.news.newsapp.R
import com.news.newsapp.api.response.Response
import com.news.newsapp.databinding.RowNewsListBinding
import java.util.ArrayList

class NewsListAdapter(private val context: Context,private val arrayList: ArrayList<Response.Article>) : RecyclerView
.Adapter<NewsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<RowNewsListBinding>(LayoutInflater.from(context), R.layout.row_news_list,
                parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(private val binding: RowNewsListBinding) : RecyclerView.ViewHolder(binding.root)
}