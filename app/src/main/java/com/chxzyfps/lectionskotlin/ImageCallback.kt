package com.chxzyfps.lectionskotlin

import android.graphics.Bitmap

interface ImageCallback {
    fun success(bitmap: Bitmap)

    fun failed()
}