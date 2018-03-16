package com.news.newsapp.api.response

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * Created by Deepak Gehlot on 3/15/2018.
 */
class Response {

    var status = ""
    var totalResults = ""
    @SerializedName("articles")
    var articleArrayList: ArrayList<Article> = ArrayList()

    inner class Article {
        var source: Source? = null
        var author = ""
        var title = ""
        var description = ""
        var urlToImage = ""
        var url = ""
        var publishedAt = ""
    }

    inner class Source {
        var id = ""
        var name = ""
    }
}

