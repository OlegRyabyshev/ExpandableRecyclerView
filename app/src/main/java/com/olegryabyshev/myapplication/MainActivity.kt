package com.olegryabyshev.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.olegryabyshev.myapplication.data.InnerClass
import com.olegryabyshev.myapplication.data.OuterClass
import com.olegryabyshev.myapplication.recyclerview.OuterAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = OuterAdapter()
        recyclerView.adapter = adapter

        adapter.submitList(createList())


        fab = findViewById(R.id.remove_first)
        fab.setOnClickListener {
            val currentList = adapter.currentList.toMutableList()
            currentList.removeAt(0)
            adapter.submitList(currentList)
        }
    }

    private fun createList(): MutableList<OuterClass> {
        return mutableListOf(
            OuterClass("Outer#1", listOf(InnerClass("inner1"), InnerClass("inner2"), InnerClass("inner3"))),
            OuterClass("Outer#2", listOf(InnerClass("inner1"), InnerClass("inner2"), InnerClass("inner3"))),
            OuterClass("Outer#3", listOf(InnerClass("inner1"), InnerClass("inner2"), InnerClass("inner3")), false),
            OuterClass("Outer#4", listOf(InnerClass("inner1"), InnerClass("inner2"), InnerClass("inner3")), false),
            OuterClass("Outer#5", listOf(InnerClass("inner1"), InnerClass("inner2"), InnerClass("inner3")), false)
        )
    }
}