package br.com.everis.sovamu.feature.home.util

import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

private const val DEFAULT_SWIPE_MIN_DISTANCE = 200

open class OnSwipeTouchListener internal constructor(
    private val listener: SwipeListener,
    private val minDistance: Int = DEFAULT_SWIPE_MIN_DISTANCE
) : View.OnTouchListener {

    private var anchorX = 0F

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                anchorX = event.x
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (abs(event.x - anchorX) > minDistance) {
                    if (event.x > anchorX) {
                        listener.onSwipeRight()
                    } else {
                        listener.onSwipeLeft()
                    }
                }
                return true
            }
        }
        return view.performClick()
    }
}