package com.anwesh.uiprojects.bouncyrightangleview

/**
 * Created by anweshmishra on 11/12/19.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val scGap : Float = 0.02f
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#3F51B5")
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 30

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawBouncyRightAngle(scale : Float, size : Float, h : Float, paint : Paint) {
    val sf : Float = scale.divideScale(0, 2).sinify()
    val sc : Float = scale.divideScale(1, 2)
    save()
    rotate(-90f * sf)
    drawLine(0f, 0f, size, 0f, paint)
    restore()
    save()
    translate(0f, (h / 2 - size) * sc)
    drawLine(0f, 0f, 0f, size, paint)
    restore()
}

fun Canvas.drawBRANode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(gap * (i + 1), h / 2)
    drawBouncyRightAngle(scale, size, h, paint)
    restore()
}
