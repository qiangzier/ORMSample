package com.hzq.componets.ui

import com.hzq.ormsample.model.Product

/**
 * @author: hezhiqiang
 * *
 * @date: 2017/6/14
 * *
 * @version:
 * *
 * @description:
 */

interface ProductClickCallback {
    fun onClick(product: Product)
}
