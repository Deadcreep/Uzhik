package com.nurgle.android.uzhik

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.text.TextPaint
import android.view.View
import android.widget.TextView
import com.nurgle.android.uzhik.database.Product

class ProductView(context: Context) : View(context) {

    lateinit var product: Product

    val paint = TextPaint().apply {
        color = Color.BLACK
        textSize = 22f
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawText(product.name, 30f, 30f, paint)
    }
}