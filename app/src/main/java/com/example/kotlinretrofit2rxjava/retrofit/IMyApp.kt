package com.example.kotlinretrofit2rxjava.retrofit

import com.example.kotlinretrofit2rxjava.model.Post
import retrofit2.http.GET
import io.reactivex.Observable

interface IMyApp {
    @get:GET("posts")
    val posts:Observable<List<Post>>
}