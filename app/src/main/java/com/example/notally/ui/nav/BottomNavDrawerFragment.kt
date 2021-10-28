package com.example.notally.ui.nav

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.example.notally.R
import com.example.notally.adapter.NavigationAdapter
import com.example.notally.base.BaseFragment
import com.example.notally.databinding.FragmentBottomNavDrawerBinding
import com.example.notally.extension.themeColor
import com.example.notally.repos.model.NavModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.google.android.material.shape.MaterialShapeDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlin.LazyThreadSafetyMode.NONE

@AndroidEntryPoint
class BottomNavDrawerFragment :
    BaseFragment<DrawerViewModel, FragmentBottomNavDrawerBinding>(),
    NavigationAdapter.NavigationAdapterListener {

    override val layoutId = R.layout.fragment_bottom_nav_drawer

    override val viewModel: DrawerViewModel by viewModels()

    private val behaviour: BottomSheetBehavior<LinearLayout> by lazy(NONE) {
        from(binding.layoutMenuSheet)
    }

    private val bottomSheetCallback = BottomNavigationDrawerCallback()

    private val navigationListeners: MutableList<NavigationAdapter.NavigationAdapterListener> =
        mutableListOf()

    private val foregroundShapeDrawable: MaterialShapeDrawable by lazy(NONE) {
        val foregroundContext = binding.layoutMenuSheet.context
        MaterialShapeDrawable(
            foregroundContext,
            null,
            R.attr.bottomSheetStyle,
            0
        ).apply {
            fillColor = ColorStateList.valueOf(
                foregroundContext.themeColor(R.attr.colorPrimarySurface)
            )
            elevation = resources.getDimension(R.dimen.dp_8)
            shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_NEVER
            initializeElevationOverlay(requireContext())
        }
    }

    private val closeDrawerOnBackPressed = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            close()
        }
    }

    fun close() {
        behaviour.state = STATE_HIDDEN
    }

    fun open() {
        behaviour.state = STATE_HALF_EXPANDED
    }

    fun toggle() {
        if (behaviour.state == STATE_HIDDEN) open()
        else if (behaviour.state == STATE_HIDDEN
            || behaviour.state == STATE_HALF_EXPANDED
            || behaviour.state == STATE_EXPANDED
            || behaviour.state == STATE_COLLAPSED
        ) close()
    }

    override fun initComponents() {
        requireActivity().onBackPressedDispatcher.addCallback(this, closeDrawerOnBackPressed)
    }

    override fun initListeners() {
        binding.layoutMenuSheet.setOnApplyWindowInsetsListener { view, windowInsets ->
            // Record the window's top inset so it can be applied when the bottom sheet is slide up
            // to meet the top edge of the screen.
            view.setTag(
                R.id.tag_system_window_inset_top,
                windowInsets.systemWindowInsetTop
            )
            windowInsets
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            layoutMenuSheet.background = foregroundShapeDrawable
            scrimView.setOnClickListener { close() }
            bottomSheetCallback.apply {
                // Scrim view transforms
                addOnSlideAction(AlphaSlideAction(scrimView))
                addOnStateChangedAction(VisibilityStateAction(scrimView))

                // Foreground transforms
                addOnSlideAction(
                    ForegroundSheetTransformSlideAction(
                        binding.layoutMenuSheet,
                        foregroundShapeDrawable
                    )
                )

                // Recycler transforms
                addOnStateChangedAction(ScrollToTopStateAction(rclMenuItems))

                // If the drawer is open, pressing the system back button should close the drawer.
                addOnStateChangedAction(object : OnStateChangedAction {
                    override fun onStateChanged(sheet: View, newState: Int) {
                        closeDrawerOnBackPressed.isEnabled = newState != STATE_HIDDEN
                    }
                })
            }

            behaviour.addBottomSheetCallback(bottomSheetCallback)
            behaviour.state = STATE_HIDDEN

            val adapter = NavigationAdapter(this@BottomNavDrawerFragment)

            rclMenuItems.adapter = adapter

            viewModel.navModalItems.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
            viewModel.setNavigationMenuItemChecked(1L)
        }
    }

    fun addOnSlideAction(action: OnSlideAction) {
        bottomSheetCallback.addOnSlideAction(action)
    }

    fun addOnStateChangedAction(action: OnStateChangedAction) {
        bottomSheetCallback.addOnStateChangedAction(action)
    }

    fun addNavigationListener(listener: NavigationAdapter.NavigationAdapterListener) {
        navigationListeners.add(listener)
    }

    override fun onNavMenuItemClicked(menu: NavModel.NavMenuItem) {
        viewModel.setNavigationMenuItemChecked(menu.id)
        close()
        navigationListeners.forEach { it.onNavMenuItemClicked(menu) }
    }

    override fun onNavLabelClicked(label: NavModel.NavLabelItem) {
        navigationListeners.forEach { it.onNavLabelClicked(label) }
    }
}
