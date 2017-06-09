package com.hzq.roomsample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.find

/**
 * @author: hezhiqiang
 * @date: 2017/6/8
 * @version:
 * @description:
 */

open class BaseActivity : AppCompatActivity(){
    open protected fun initToolBar(){
        val toolBar = find<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolBar)
        toolBar.setNavigationOnClickListener {
            onBackPressed()
        }
        if(actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        super.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out)
    }

    override fun finish() {
        super.finish()
        super.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out)
    }

    override fun finishActivity(requestCode: Int) {
        super.finishActivity(requestCode)
        super.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out)
    }
}