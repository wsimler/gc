package com.zxc.roomkotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

class GridVM(app: Application) : AndroidViewModel(app) {

    private val items = mutableListOf(
            GridItem(1, "Hello", "hello"),
            GridItem(2, "Hello", "hello"),
            GridItem(3, "Hello", "hello"),
            GridItem(4, "Hello", "hello"),
            GridItem(5, "Hello", "hello")
    )
    val adapter = MutableLiveData<GridAdapter>()

    init {
        adapter.value = GridAdapter(items)
    }
}