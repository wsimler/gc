package com.zxc.roomkotlin.old

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

class GridVM(app: Application) : AndroidViewModel(app) {

    private val items = mutableListOf(
            GridItem(1, "Hello", "hello", false),
            GridItem(2, "Hello", "hello", false),
            GridItem(3, "Hello", "hello", false),
            GridItem(4, "Hello", "hello", false),
            GridItem(5, "Hello", "hello", false)
    )
    val adapter = MutableLiveData<GridAdapter>()

    init {
        adapter.value = GridAdapter(items)
    }
}