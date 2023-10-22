package com.vlad.kozyr.catfacts.presentation.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class MarginItemDecoration(
    private val verticalSize: Int,
    private val horizontalSize: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            top = verticalSize
            bottom = verticalSize

            left = horizontalSize
            right = horizontalSize
        }
    }
}