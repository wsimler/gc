package com.zxc.roomkotlin

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zxc.roomkotlin.databinding.ActivityGridBinding

class GridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(GridVM::class.java)
        val binding: ActivityGridBinding = DataBindingUtil.setContentView(this, R.layout.activity_grid)
        binding.setLifecycleOwner(this)
        binding.vm = viewModel
    }
}
