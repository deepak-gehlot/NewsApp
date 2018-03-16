package com.news.newsapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.news.newsapp.api.APIClient
import com.news.newsapp.api.Api
import com.news.newsapp.view.activity.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.icu.util.ULocale.getCountry
import android.util.Log
import com.news.newsapp.util.Utils
import java.util.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /* val api = APIClient.getClient().create(Api::class.java)
         val responseCall = api.getTopHeadLines("us", "business")
         responseCall.enqueue(object : Callback<com.news.newsapp.api.response.Response> {
             override fun onResponse(call: Call<com.news.newsapp.api.response.Response>, response: Response<com.news.newsapp.api.response.Response>) {
                 responseCall.cancel()
             }

             override fun onFailure(call: Call<com.news.newsapp.api.response.Response>, t: Throwable) {
                 responseCall.cancel()
             }
         })*/
        val intent = Intent(this@SplashActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
