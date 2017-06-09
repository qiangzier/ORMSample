package com.hzq.roomsample

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hzq.roomsample.adapter.MyAdapter
import com.hzq.roomsample.db.entity.ProductEntity
import com.hzq.roomsample.helper.AppDatabaseHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class MainActivity : BaseActivity() {

    var databaseHelper: AppDatabaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()

        databaseHelper = AppDatabaseHelper(this)
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        val adapter = MyAdapter(this)
        recyclerView.adapter = adapter

        var i = async(CommonPool,CoroutineStart.LAZY){
            databaseHelper?.insertAll(getData())
        }
        var d = async(CommonPool,CoroutineStart.LAZY){
            databaseHelper?.allProducts!!
        }
        launch(UI){
            i.await()
            adapter.setData(d.await())
        }
    }

    fun getData(): List<ProductEntity>{
        var list = arrayListOf<ProductEntity>()
        var i = 0
        while (i< 10){
            list.add(newProduct("item${i}","the item${i} is desc${i}",10.0*i))
            i++
        }
        return list
    }

    fun newProduct(name: String,desc: String,price: Double): ProductEntity{
        val o = ProductEntity()
        o.name = name
        o.description = desc
        o.price = price
        return o
    }
}
