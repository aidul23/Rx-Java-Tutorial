package com.aidul23.rxjavatutorial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.aidul23.rxjavatutorial.api.RetrofitInstance
import com.aidul23.rxjavatutorial.api.RetrofitService
import com.aidul23.rxjavatutorial.model.BookList
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import retrofit2.http.Query

class MainActivityViewModel : ViewModel() {
    lateinit var bookList: MutableLiveData<BookList>
    init {
        bookList = MutableLiveData()
    }

    fun getBookListObserver() : MutableLiveData<BookList> {
        return bookList
    }

    fun makeApiCall(query: String) {
        val retrofitInstance = RetrofitInstance.getInstance().create(RetrofitService::class.java)
        retrofitInstance.getBookList(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getBookListObserverRx())
    }

    private fun getBookListObserverRx():io.reactivex.Observer<BookList> {
        return object :io.reactivex.Observer<BookList>{
            override fun onSubscribe(d: Disposable) {
                //start showing progress indicator.
            }

            override fun onNext(t: BookList) {
                bookList.postValue(t)
            }

            override fun onError(e: Throwable) {
                bookList.postValue(null)
            }

            override fun onComplete() {
                //hide progress indicator.
            }


        }
    }
}






