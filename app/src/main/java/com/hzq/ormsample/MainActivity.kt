package com.hzq.ormsample

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.hzq.ormsample.adapter.MyAdapter
import com.hzq.ormsample.helper.DB
import com.hzq.ormsample.helper.coroutine
import com.hzq.ormsample.helper.dbHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    var adapter: MyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()
        title = "RoomSample"
        EventBus.getDefault().register(this)
        fab.setOnClickListener {
            startActivity<CreateProductActivity>()
        }

        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        adapter = MyAdapter(this)
        recyclerView.adapter = adapter
        loadData()
    }

    fun loadData(){
        coroutine({
            dbHelper.allProducts
        }){
            adapter?.setData(it)
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onMessageEvent(eventChenge: OnEventChenge){
        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.orm_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_room -> {
                DB.type = 0
                title = "RoomSample"
            }
            R.id.action_greendao -> {
                DB.type = 1
                title = "GreenDaoSample"
            }
            R.id.action_ormlite -> {
                DB.type = 2
                title = "ORMLiteSample"
            }
        }
        loadData()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
