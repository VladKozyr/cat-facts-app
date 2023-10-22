package com.vlad.kozyr.catfacts.core

import android.content.res.Resources
import android.view.View
import kotlin.math.roundToInt

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).roundToInt()

fun View.visibleOrInvisible(flag: Boolean) {
    visibility = if (flag)
        View.VISIBLE
    else
        View.INVISIBLE

}