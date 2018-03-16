package com.news.newsapp.view.fragment

import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.news.newsapp.R
import com.news.newsapp.adapter.NewsListAdapter
import com.news.newsapp.api.APIClient
import com.news.newsapp.api.Api
import com.news.newsapp.api.response.Response
import com.news.newsapp.databinding.FragmentMainBinding
import com.news.newsapp.util.Utils
import retrofit2.Call
import retrofit2.Callback

import yalantis.com.sidemenu.interfaces.ScreenShotable
import java.util.ArrayList

class ContentFragment : Fragment(), ScreenShotable {

    private var mBinding: FragmentMainBinding? = null
    private var res: String = ""
    private var bitmap: Bitmap? = null
    private var mCountry: String = ""

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNewsList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        res = arguments.getString(String::class.java.name)
        mCountry = Utils.getCurrentLocale(activity).country
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_main, container, false)
        //mBinding!!.recyclerView.setBackgroundColor(res)
        return mBinding!!.root
    }

    override fun takeScreenShot() {
        val thread = object : Thread() {
            override fun run() {
                val bitmap = Bitmap.createBitmap(mBinding!!.container.width,
                        mBinding!!.container.height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                mBinding!!.container.draw(canvas)
                this@ContentFragment.bitmap = bitmap
            }
        }

        thread.start()

    }

    override fun getBitmap(): Bitmap? {
        return bitmap
    }

    private fun getNewsList() {
        val api = APIClient.getClient().create(Api::class.java)
        val responseCall = api.getTopHeadLines(mCountry, res)
        responseCall.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
                handleResponse(response?.body())
            }

            override fun onFailure(call: Call<Response>?, t: Throwable?) {

            }
        })
    }

    fun handleResponse(response: Response?) {
        setRecyclerView(response!!.articleArrayList)
    }

    private fun setRecyclerView(arrayList: ArrayList<Response.Article>) {
        mBinding?.recyclerView?.layoutManager = LinearLayoutManager(activity)
        val newsListAdapter = NewsListAdapter(activity, arrayList)
        mBinding?.recyclerView?.adapter = newsListAdapter
    }

    companion object {

        val CLOSE = "Close"
        val BUSINESS = "business"
        val ENTERTAINMENT = "Entertainment"
        val HEALTH = "Health"
        val SCIENCE = "Science"
        val SPORTS = "Sports"
        val TECHNOLOGY = "Technology"
        val BITCOIN = "Bitcoin"

        fun newInstance(type: String): ContentFragment {
            val contentFragment = ContentFragment()
            val bundle = Bundle()
            bundle.putString(String::class.java.name, type)
            contentFragment.arguments = bundle
            return contentFragment
        }
    }
}