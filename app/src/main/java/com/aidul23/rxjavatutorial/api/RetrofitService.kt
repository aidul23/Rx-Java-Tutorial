package com.aidul23.rxjavatutorial.api

import com.aidul23.rxjavatutorial.model.BookList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("volumes")
    fun getBookList(@Query("q") query: String) : Observable<BookList>
}