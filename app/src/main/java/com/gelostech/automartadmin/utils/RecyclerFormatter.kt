package com.gelostech.automartadmin.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.gelostech.automartadmin.R

class RecyclerFormatter {

    class SimpleDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
        private val mDivider: Drawable? = ContextCompat.getDrawable(context, R.drawable.simple_recycler_divider)

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight

            val childCount = parent.childCount
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)

                val params = child.layoutParams as RecyclerView.LayoutParams

                val top = child.bottom + params.bottomMargin
                val bottom = top + mDivider!!.intrinsicHeight

                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(c)
            }
        }
    }

    class DoubleDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
        private val mDivider: Drawable? = ContextCompat.getDrawable(context, R.drawable.recycler_divider)

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight

            val childCount = parent.childCount
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)

                val params = child.layoutParams as RecyclerView.LayoutParams

                val top = child.bottom + params.bottomMargin
                val bottom = top + mDivider!!.intrinsicHeight

                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(c)
            }
        }
    }

    class GridItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

        constructor(context: Context, space: Int) : this(context.resources.getDimensionPixelSize(space)) {}

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.set(space, space, space, space)
        }
    }
}
