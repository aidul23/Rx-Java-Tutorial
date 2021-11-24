package com.aidul23.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.aidul23.rxjavatutorial.adapter.BookListAdapter
import com.aidul23.rxjavatutorial.model.BookList
import com.aidul23.rxjavatutorial.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel : MainActivityViewModel
    lateinit var bookListAdapter : BookListAdapter
    companion object {
        private const val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSearchBox()
        initRecyclerView()
    }

    fun initSearchBox() {
        val inputBookName: EditText = findViewById(R.id.inputBookName)
        inputBookName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                loadApiData(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    fun initRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext,VERTICAL)
            addItemDecoration(decoration)

            bookListAdapter = BookListAdapter()
            adapter = bookListAdapter
        }
    }

    fun loadApiData(input: String) {
       viewModel =  ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getBookListObserver().observe(this, Observer<BookList> {
            if(it != null) {
                //update adapter
                Log.d(TAG, "loadApiData: "+it.items)
                bookListAdapter.bookList = it.items
                bookListAdapter.notifyDataSetChanged()
            } else{
                Toast.makeText(this,"Error in fetching data",Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall(input)
    }
}