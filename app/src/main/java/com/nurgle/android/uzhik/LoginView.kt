package com.nurgle.android.uzhik

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.text.TextPaint
import android.util.Log
import android.view.View

/**
 * Created by Nurgle on 01.03.2018.
 */
class LoginView(context: Context) : View(context) {

    val text = "Hell is everywhere"

    private val paint = TextPaint().apply {
        color = Color.RED
        textSize = 60f
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawText(text, 100f, 100f, paint)
        Log.i("LoginView", text)
    }

    /*override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        Toast.makeText(context, text, Toast.LENGTH_SHORT)
    }*/
}