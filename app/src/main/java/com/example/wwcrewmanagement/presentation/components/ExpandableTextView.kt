package com.example.wwcrewmanagement.presentation.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.wwcrewmanagement.R


class ExpandableTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var imageView: ImageView
    private var textView: TextView
    private var button: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.expandable_text_view, this)
        imageView = findViewById(R.id.image_view)
        textView = findViewById(R.id.text_view)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            if (textView.maxLines == 5) {
                textView.maxLines = Int.MAX_VALUE
                button.text = "Read less..."
            } else {
                textView.maxLines = 5
                button.text = "Read more..."
            }
        }

    }

    fun setImage(drawable: Drawable) {
        imageView.setImageDrawable(drawable)
    }

    fun setText(text: CharSequence) {
        textView.text = text
    }
}