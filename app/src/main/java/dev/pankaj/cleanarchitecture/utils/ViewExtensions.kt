package dev.pankaj.cleanarchitecture.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

fun View.gone() {
    visibility = View.GONE
}

fun View.show(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.pulseAnimate(count: Int = 3) {
    val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
        this,
        PropertyValuesHolder.ofFloat("scaleX", 1.2f),
        PropertyValuesHolder.ofFloat("scaleY", 1.2f)
    ).apply {
        duration = 310
        repeatCount = count
        repeatMode = ObjectAnimator.REVERSE
    }
    scaleDown.start()
}

fun View.goneWithFade(duration: Long = 300) {
    animate().alpha(0f).setDuration(duration).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            visibility = View.GONE
        }
    })
}

fun View.visibleWithFade(duration: Long = 300) {
    alpha = 0f
    visible()
    animate().alpha(1f).setDuration(duration).setListener(null)
}

fun View.slideDown() {
    visibility = View.VISIBLE
    alpha = 0.0f
    animate().translationY(height.toFloat()).alpha(1.0f).setListener(null)
}

fun View.slideUp() {
    animate().translationY(0.0f).alpha(0.0f).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            visibility = View.VISIBLE
        }
    })
}

fun View.changeBackgroundColorWithAnim(fromColor: Int, toColor: Int, duration: Long = 700) {
    ValueAnimator.ofObject(ArgbEvaluator(), fromColor, toColor).apply {
        this.duration = duration
        addUpdateListener { animation -> setBackgroundColor(animation.animatedValue as Int) }
        start()
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun View.disableView(disable: Boolean, disableColor: Int = -1, enableColor: Int = -1) {
    isClickable = !disable
    if (disable) {
        alpha = if (disableColor == -1) 0.5f else {
            backgroundTintList = ColorStateList.valueOf(disableColor)
            1.0f
        }
    } else {
        alpha = if (enableColor == -1) 1.0f else {
            backgroundTintList = ColorStateList.valueOf(enableColor)
            1.0f
        }
    }
}

fun View.showSnack(text: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, duration).show()
}
