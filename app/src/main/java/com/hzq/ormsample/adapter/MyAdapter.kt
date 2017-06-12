package com.hzq.ormsample.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hzq.db.room.entity.ProductEntity
import com.hzq.ormsample.CreateProductActivity
import com.hzq.ormsample.R
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

/**
 * @author: hezhiqiang
 * @date: 2017/6/9
 * @version:
 * @description:
 */

class MyAdapter(val mContext: Context) : RecyclerView.Adapter<MyViewHolder>(){
    var mData: List<ProductEntity> = arrayListOf()

    fun setData(list: List<ProductEntity>?){
        if(list != null) {
            mData = list
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int): Unit {
        with(mData[position]) {
            holder?.name?.text = name
            holder?.desc?.text = description
            holder?.price?.text = "${price} 元"

            holder?.itemView?.setOnClickListener {
                mContext.startActivity<CreateProductActivity>("id" to id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_item,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
    var name: TextView? = null
    var desc: TextView? = null
    var price: TextView? = null

    init {
        name = view.find<TextView>(R.id.name)
        desc = view.find<TextView>(R.id.desc)
        price = view.find<TextView>(R.id.price)
    }
}