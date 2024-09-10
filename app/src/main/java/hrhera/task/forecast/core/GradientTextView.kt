package hrhera.task.forecast.core

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import hrhera.task.forecast.R

/**
 * A custom TextView that can display a gradient effect.
 *
 * @author Hrhera
 */
class GradientTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    /**
     * The color of the gradient's start point.
     */
    private var startColor: Int = 0

    /**
     * The color of the gradient's end point.
     */
    private var endColor: Int = 0

    /**
     * Whether the gradient should be applied to the text.
     */
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

    /**
     * Apply the gradient effect to the text.
     */
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

    /**
     * Called when the view gains or loses focus.
     *
     * @param hasWindowFocus whether the view has focus
     */
    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) {
            applyGradient()
        }
    }

    /**
     * Called when the view's size changes.
     *
     * @param w the new width of the view
     * @param h the new height of the view
     * @param oldw the old width of the view
     * @param oldh the old height of the view
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        applyGradient()
    }
}

