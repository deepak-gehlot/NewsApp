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
import com.news.newsapp.databinding.FragmentMainBinding

import yalantis.com.sidemenu.interfaces.ScreenShotable

/**
 * Created by Konstantin on 22.12.2014.
 */
class ContentFragment : Fragment(), ScreenShotable {

    private var mBinding: FragmentMainBinding? = null
    protected var res: Int = 0
    private var bitmap: Bitmap? = null


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        res = arguments.getInt(Int::class.java.name)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_main, container, false)
        mBinding!!.recyclerView.setBackgroundColor(res)
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

    private fun setRecyclerView() {
        /* mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NewsListAdapter newsListAdapter = new NewsListAdapter(getActivity());
        mBinding.recyclerView.setAdapter(newsListAdapter);*/
    }

    companion object {

        val CLOSE = "Close"
        val BUILDING = "Building"
        val BOOK = "Book"
        val PAINT = "Paint"
        val CASE = "Case"
        val SHOP = "Shop"
        val PARTY = "Party"
        val MOVIE = "Movie"

        fun newInstance(resId: Int): ContentFragment {
            val contentFragment = ContentFragment()
            val bundle = Bundle()
            bundle.putInt(Int::class.java.name, resId)
            contentFragment.arguments = bundle
            return contentFragment
        }
    }
}