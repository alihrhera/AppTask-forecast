package hrhera.task.forecast.core

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import hrhera.task.forecast.R

class GradientTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var startColor: Int = 0
    private var endColor: Int = 0
    private var setGradient = false

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.GradientTextView,
            0, 0
        ).apply {

            try {
                startColor =
                    getColor(R.styleable.GradientTextView_startColor, ContextCompat.getColor(context, R.color.transparent))
                endColor = getColor(R.styleable.GradientTextView_endColor, ContextCompat.getColor(context, R.color.transparent))
                setGradient =
                    startColor != ContextCompat.getColor(context, R.color.transparent) && startColor != ContextCompat.getColor(
                        context,
                        R.color.transparent
                    )
            } finally {
                recycle()
            }

            applyGradient()
        }
    }

    private fun applyGradient() {
        if (!setGradient) return
        val paint = paint
        val width = width.toFloat()

        val shader = LinearGradient(
            0f, 0f, width, 0f,
            intArrayOf(startColor, endColor),
            null,
            Shader.TileMode.CLAMP
        )
        paint.shader = shader
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) {
            applyGradient()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        applyGradient()
    }
}

