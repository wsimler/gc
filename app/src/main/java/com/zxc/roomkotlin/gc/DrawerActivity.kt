package com.zxc.roomkotlin.gc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.ansh.extensions.toast
import com.zxc.roomkotlin.GcApp
import com.zxc.roomkotlin.R
import com.zxc.roomkotlin.swth.CustomSwitch

class DrawerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        val s = CustomSwitch(this)

        "Hello Snippets".toast
        Toast.makeText(GcApp.appCtx, "Hello GC", Toast.LENGTH_SHORT).show()

    }
}
