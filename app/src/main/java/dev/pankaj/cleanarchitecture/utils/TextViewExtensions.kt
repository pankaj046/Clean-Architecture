package dev.pankaj.cleanarchitecture.utils

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.Typeface
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView

fun TextView.setNonEmptyText(s: String?) {
    if (!s.isNullOrEmpty()) {
        visible()
        text = s
    } else {
        gone()
    }
}

fun TextView.setCount(count: Int, duration: Long = 1000) {
    val animator = ValueAnimator.ofInt(0, count).apply {
        this.duration = duration
        addUpdateListener { animation -> text = animation.animatedValue.toString() }
    }
    animator.start()
}

fun TextView.span(queryText: String) {
    try {
        val startPos = text.toString().lowercase().indexOf(queryText.lowercase())
        val endPos = startPos + queryText.length
        val spannable = SpannableStringBuilder(text)
        spannable.setSpan(ForegroundColorSpan(Color.RED), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setText(spannable, TextView.BufferType.SPANNABLE)
    } catch (e: Exception) {
        // Handle the exception
    }
}

fun TextView.setNonNullText(text: String?) {
    setText(text ?: "")
}

fun TextView.normal() {
    setTypeface(typeface, Typeface.NORMAL)
}

fun TextView.bold() {
    setTypeface(typeface, Typeface.BOLD)
}

fun TextView.italic() {
    setTypeface(typeface, Typeface.ITALIC)
}

fun TextView.boldItalic() {
    setTypeface(typeface, Typeface.BOLD_ITALIC)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.queryListener(bindClear: View? = null, onQueryChanged: (String) -> Unit) {
    bindClear?.gone()
    bindClear?.setOnClickListener { setText("") }

    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onQueryChanged(s.toString())
            if (s?.isNotEmpty() == true) bindClear?.visible() else bindClear?.gone()
        }
    })
}

fun EditText.trimText() = text.toString().trim()

fun EditText.clearText() = setText("")
