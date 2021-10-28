package com.example.notally.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notally.databinding.*
import com.example.notally.repos.model.NavModel

sealed class NavigationViewHolder<T : NavModel>(
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun bind(navItem: T)

    class NavMenuItemViewHolder(
        private val binding: LayoutMenuNavItemBinding,
        private val listener: NavigationAdapter.NavigationAdapterListener
    ) : NavigationViewHolder<NavModel.NavMenuItem>(binding.root) {

        override fun bind(navItem: NavModel.NavMenuItem) {
            binding.run {
                navMenuItem = navItem
                navListener = listener
                executePendingBindings()
            }
        }
    }

    class NavDividerViewHolder(
        private val binding: LayoutDividerNavItemBinding
    ) : NavigationViewHolder<NavModel.NavDivider>(binding.root) {

        override fun bind(navItem: NavModel.NavDivider) {
            binding.run {
                navDivider = navItem
                executePendingBindings()
            }
        }
    }

    class NavLabelViewHolder(
        private val binding: LayoutLabelNavItemBinding,
        private val listener: NavigationAdapter.NavigationAdapterListener
    ) : NavigationViewHolder<NavModel.NavLabelItem>(binding.root) {

        override fun bind(navItem: NavModel.NavLabelItem) {
            binding.run {
                navLabel = navItem
                navListener = listener
                executePendingBindings()
            }
        }
    }
}
