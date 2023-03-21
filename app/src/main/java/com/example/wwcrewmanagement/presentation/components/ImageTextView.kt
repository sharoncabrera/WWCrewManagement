package com.example.wwcrewmanagement.presentation.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.wwcrewmanagement.R


class ImageTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView
    private val textView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.image_text_view, this, true)
        imageView = findViewById(R.id.image_view)
        textView = findViewById(R.id.text_view)
    }

    fun setImage(drawable: Drawable) {
        imageView.setImageDrawable(drawable)
    }

    fun setText(text: CharSequence) {
        textView.text = text
    }
}