package com.example.notally.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.notally.R
import com.example.notally.adapter.NavigationAdapter
import com.example.notally.base.BaseActivity
import com.example.notally.databinding.ActivityMainBinding
import com.example.notally.repos.model.NavModel
import com.example.notally.ui.nav.*
import dagger.hilt.android.AndroidEntryPoint
import kotlin.LazyThreadSafetyMode.NONE

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(),
    Toolbar.OnMenuItemClickListener,
    NavController.OnDestinationChangedListener,
    NavigationAdapter.NavigationAdapterListener {

    override val layoutId = R.layout.activity_main

    override val viewModel: MainViewModel by viewModels()

    private val bottomNavDrawer: BottomNavDrawerFragment by lazy(NONE) {
        supportFragmentManager.findFragmentById(R.id.fragmentNavBottom) as BottomNavDrawerFragment
    }

    // Keep track of the current Note being viewed, if any, in order to pass the correct note id to
    // EditNoteFragment when this Activity's FAB is clicked.
    private var currentNoteId = -1L

    private lateinit var navHostFragment: NavHostFragment

    val currentNavigationFragment: Fragment?
        get() = navHostFragment
            .childFragmentManager
            .fragments
            .first()

    override fun initComponents() {
        setUpBottomNavigationAndFab()
    }

    private fun setUpBottomNavigationAndFab() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentNavHost) as NavHostFragment

        binding.run {
            navHostFragment.navController.addOnDestinationChangedListener(this@MainActivity)
        }

        // Set a custom animation for showing and hiding the FAB
        binding.fabTakeNote.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            setOnClickListener {
                navigateToEditFragment()
            }
        }

        bottomNavDrawer.apply {
            addOnSlideAction(HalfClockwiseRotateSlideAction(binding.imgAppbarChevron))
            addOnSlideAction(AlphaSlideAction(binding.textViewAppbarTitle, true))
            addOnStateChangedAction(ShowHideFabStateAction(binding.fabTakeNote))
            addOnStateChangedAction(ChangeSettingsMenuStateAction { showSettings ->
                // Toggle between the current destination's BAB menu and the menu which should
                // be displayed when the BottomNavigationDrawer is open.
                binding.bottomAppBar.replaceMenu(
                    if (showSettings)
                        R.menu.menu_bottom_app_bar_settings
                    else getBottomAppBarMenuForDestination()
                )
            })

            addNavigationListener(this@MainActivity)
        }

        // Set up the BottomAppBar menu
        binding.bottomAppBar.apply {
            setNavigationOnClickListener {
                bottomNavDrawer.toggle()
            }
            setOnMenuItemClickListener(this@MainActivity)
        }

        // Set up the BottomNavigationDrawer's open/close affordance.
        binding.layoutAppbarContent.setOnClickListener {
            bottomNavDrawer.toggle()
        }
    }

    private fun getBottomAppBarMenuForDestination(destination: NavDestination? = null): Int {
        val dest = destination ?: findNavController(R.id.fragmentNavHost).currentDestination
        return when (dest?.id) {
            R.id.noteFragment -> R.menu.menu_bottom_app_bar_home_menu
            else -> R.menu.menu_bottom_app_bar_home_menu
        }
    }

    private fun navigateToEditFragment() {

    }

    override fun initListeners() {

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return true
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

    }

    override fun onNavMenuItemClicked(menu: NavModel.NavMenuItem) {

    }

    override fun onNavLabelClicked(label: NavModel.NavLabelItem) {

    }
}
