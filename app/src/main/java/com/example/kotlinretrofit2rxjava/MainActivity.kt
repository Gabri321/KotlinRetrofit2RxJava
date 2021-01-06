package com.example.kotlinretrofit2rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinretrofit2rxjava.adapter.PostAdapter
import com.example.kotlinretrofit2rxjava.model.Post
import com.example.kotlinretrofit2rxjava.retrofit.IMyApp
import com.example.kotlinretrofit2rxjava.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal lateinit var jsonApi:IMyApp
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClient.instance
        jsonApi = retrofit.create(IMyApp::class.java)

        recycler_posts.setHasFixedSize(true)
        recycler_posts.layoutManager = LinearLayoutManager(this)
        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(jsonApi.posts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{posts->displayData(posts)})
    }

    private fun displayData(posts: List<Post>?) {

        val adapter = PostAdapter(this,posts!!)
        recycler_posts.adapter = adapter

    }
}