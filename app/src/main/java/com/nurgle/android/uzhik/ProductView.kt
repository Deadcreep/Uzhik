package com.nurgle.android.uzhik

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.text.TextPaint
import android.view.View
import android.widget.Toast

/**
 * Created by Nurgle on 01.03.2018.
 */
class ProductView(context: Context) : View(context) {

    var text: String = "Hello"

    private val paint = TextPaint().apply {
        color = Color.BLACK
        textSize = 42f
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawText(text, 100f, 100f, paint)
    }

    /*override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        Toast.makeText(context, text, Toast.LENGTH_SHORT)
    }*/
}