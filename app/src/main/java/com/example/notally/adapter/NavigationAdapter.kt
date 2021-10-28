package com.example.notally.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.notally.databinding.LayoutDividerNavItemBinding
import com.example.notally.databinding.LayoutLabelNavItemBinding
import com.example.notally.databinding.LayoutMenuNavItemBinding
import com.example.notally.repos.model.ModelItemDiff
import com.example.notally.repos.model.NavModel
import java.lang.Exception
import java.lang.RuntimeException

private const val VIEW_TYPE_NAV_MENU_ITEM = 4
private const val VIEW_TYPE_NAV_DIVIDER = 6
private const val VIEW_TYPE_NAV_LABEL_ITEM = 5

class NavigationAdapter(
    private val listener: NavigationAdapterListener
) : ListAdapter<NavModel, NavigationViewHolder<NavModel>>(ModelItemDiff) {

    interface NavigationAdapterListener {
        fun onNavMenuItemClicked(menu: NavModel.NavMenuItem)
        fun onNavLabelClicked(label: NavModel.NavLabelItem)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is NavModel.NavMenuItem -> VIEW_TYPE_NAV_MENU_ITEM
            is NavModel.NavLabelItem -> VIEW_TYPE_NAV_LABEL_ITEM
            is NavModel.NavDivider -> VIEW_TYPE_NAV_DIVIDER
            else -> throw RuntimeException("Unsupported ItemViewType for obj ${getItem(position)}")
        }
    }

    @Suppress("unchecked_cast")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NavigationViewHolder<NavModel> {
        return when (viewType) {
            VIEW_TYPE_NAV_MENU_ITEM -> NavigationViewHolder.NavMenuItemViewHolder(
                LayoutMenuNavItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
            VIEW_TYPE_NAV_DIVIDER -> NavigationViewHolder.NavDividerViewHolder(
                LayoutDividerNavItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_NAV_LABEL_ITEM -> NavigationViewHolder.NavLabelViewHolder(
                LayoutLabelNavItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
            else -> throw Exception("Unsupported view holder type")
        } as NavigationViewHolder<NavModel>
    }

    override fun onBindViewHolder(
        holder: NavigationViewHolder<NavModel>,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}
