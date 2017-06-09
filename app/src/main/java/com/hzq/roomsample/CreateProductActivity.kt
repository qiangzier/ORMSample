package com.hzq.roomsample

import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.hzq.roomsample.db.entity.ProductEntity
import com.hzq.roomsample.helper.coroutine
import com.hzq.roomsample.helper.dbHelper
import kotlinx.android.synthetic.main.activity_create_product.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.toast

class CreateProductActivity : BaseActivity() {

    var id: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)
        initToolBar()
        id = intent.getLongExtra("id",0)
        if(id == 0L) setTitle("create product") else setTitle("update product")
        delete.visibility = if(id == 0L) View.GONE else View.VISIBLE
        delete.setOnClickListener {
            coroutine({
                dbHelper.delete(id)
            }){
                toast("delete success!")
                EventBus.getDefault().post(OnEventChenge(1))
                finish()
            }
        }
        if(id != 0L) {
            coroutine({
                dbHelper.getProductById(id)
            }) {
                with(it) {
                    inputName.setText(name)
                    inputDesc.setText(description)
                    inputPrice.setText(price.toString())
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.task_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        save()
        return super.onOptionsItemSelected(item)
    }

    fun save(){
        var p = ProductEntity()
        p.id = id
        p.name = inputName.text.toString()
        p.description = inputDesc.text.toString()
        p.price = if(!TextUtils.isEmpty(inputPrice.text.toString())) inputPrice.text.toString().toDouble() else 0.0

        coroutine({
            dbHelper.insert(p)
        }){
            toast("save success")
            EventBus.getDefault().post(OnEventChenge(1))
            finish()
        }

    }
}
