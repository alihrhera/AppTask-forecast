package hrhera.task.forecast.utils

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("bind:showErrorWithAnimation")
fun TextView.showErrorWithAnimation(errorMessage: String?) {
    this.error = errorMessage
    if (errorMessage != null) this.startAnimating()

}

@BindingAdapter("showErrorWithAnimation")
fun TextInputLayout.showErrorWithAnimation(errorMessage: String?) {
    this.error = errorMessage
    if (errorMessage != null)
        this.startAnimating() // Add any animation logic

}

/**
 * Extension function to start an animation on a View.
 * This function animates the translationX property of the View in a cyclic manner.
 * vibrate the view with error
 */
fun View.startAnimating() {
    val animator = ObjectAnimator.ofFloat(this, "translationX", 0f, 10f)
    animator.duration = 1000
    animator.interpolator = CycleLinearInterpolator(5)
    animator.start()
}
