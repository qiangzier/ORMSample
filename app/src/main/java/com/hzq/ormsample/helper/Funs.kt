package com.hzq.ormsample.helper

import android.content.Context
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI

/**
 * @author: hezhiqiang
 * @date: 2017/6/9
 * @version:
 * @description:
 */

object DB{
    private var instance: AppDatabaseHelper? = null
    @Synchronized
    fun getDB(): AppDatabaseHelper{
        if(instance == null){
            instance = AppDatabaseHelper()
        }
        return instance!!
    }
}

val Context.dbHelper: AppDatabaseHelper
        get() = DB.getDB()

fun <T> coroutine(block: suspend CoroutineScope.() -> T,
                  uiBlock: suspend (T) -> Unit): Deferred<T> {
    val deferred = async(CommonPool,start = CoroutineStart.LAZY,block = block)
    launch(UI){
        uiBlock(deferred.await())
    }
    return deferred
}