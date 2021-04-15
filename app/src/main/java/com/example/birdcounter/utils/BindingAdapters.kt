package com.example.birdcounter.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {


    @BindingAdapter("bind:setBackgroundColor") // you need to add  xmlns:bind="http://schemas.android.com/apk/res-auto" in xml layout
    @JvmStatic fun setBackgroundColor(textView: TextView, color: Int) {
        textView.setBackgroundColor(color)
    }
}
