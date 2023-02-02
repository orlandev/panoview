package com.orlandev.panoview.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

suspend fun getCoilBitmap(ctx: Context, url: String): Bitmap? {
    return try {
        val loading = ImageLoader(ctx)
        val request =
            ImageRequest.Builder(ctx).allowConversionToBitmap(true).allowHardware(false).data(url)
                .build()
        val result = (loading.execute(request = request) as SuccessResult).drawable
        (result as BitmapDrawable).bitmap
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        null
    }
}
