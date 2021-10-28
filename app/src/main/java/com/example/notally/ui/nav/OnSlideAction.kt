package com.example.notally.ui.nav

import android.view.View
import androidx.annotation.FloatRange
import androidx.core.view.marginTop
import androidx.core.view.updatePadding
import com.example.notally.R
import com.example.notally.extension.normalize
import com.google.android.material.shape.MaterialShapeDrawable

/**
 * An action to be perform when a bottom sheet's slide offset is changed.
 */
interface OnSlideAction {

    /**
     * Called when the bottom sheet's slideOffset is changed. SlideOffset will always be a value
     * between -1.0 and 1.0. -1.0 is equal to BottomSheetBehaviour.STATE_HIDDEN, 0.0 is equal to BottomSheetBehaviour.STATE_HALF_EXPANDED
     * and 1.0 is equal to BottomSheetBehaviour.STATE_EXPANDED.
     */
    fun onSlide(
        sheet: View,
        @FloatRange(
            from = -1.0,
            fromInclusive = true,
            to = 1.0,
            toInclusive = true
        ) slideOffset: Float
    )
}

class HalfClockwiseRotateSlideAction(
    private val view: View
) : OnSlideAction {

    override fun onSlide(sheet: View, slideOffset: Float) {
        view.rotation = slideOffset.normalize(
            -1F,
            0F,
            0F,
            180F
        )
    }
}

/**
 * A slide action which acts on the nav drawer between the half expanded state and expanded state by:
 * - Translating the foreground sheet
 * - Removing the foreground sheets rounded corners/edge treatment.
 */
class ForegroundSheetTransformSlideAction(
    private val foregroundView: View,
    private val foregroundShapeDrawable: MaterialShapeDrawable
) : OnSlideAction {

    private val foregroundMarginTop = foregroundView.marginTop
    private var systemTopInset: Int = 0

    // This view's tag might not be set immediately as it needs to wait for insets to be apply. Lazily evaluate
    // to ensure we get a value, even if we're already started slide changes.
    private fun getPaddingTop(): Int {
        if (systemTopInset == 0) {
            systemTopInset = foregroundView.getTag(R.id.tag_system_window_inset_top) as? Int? ?: 0
        }
        return systemTopInset
    }

    override fun onSlide(sheet: View, slideOffset: Float) {
        val progress = slideOffset.normalize(0F, 0.25F, 1F, 0F)
        foregroundShapeDrawable.interpolation = progress
        foregroundView.translationY = -(1 - progress) * foregroundMarginTop

        val topPaddingProgress = slideOffset.normalize(0F, 0.9F, 0F, 1F)
        foregroundView.updatePadding(top = (getPaddingTop() * topPaddingProgress).toInt())
    }
}

/**
 * Change the alpha of view when a bottom sheet is slide
 * reverse Setting reverse to true will cause the view's alpha to approach 0.0 as the sheet slides up.
 * The default behaviour, false, causes the view's alpha to approach 1.0 as the sheet slides up.
 */
class AlphaSlideAction(
    private val view: View,
    private val reverse: Boolean = false
) : OnSlideAction {

    override fun onSlide(sheet: View, slideOffset: Float) {
        val alpha = slideOffset.normalize(-1F, 0F, 0F, 1F)
        view.alpha = if (!reverse) alpha else 1F - alpha
    }
}
