package com.example.notally.repos.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.DiffUtil
import com.example.notally.extension.sameAndEqual

enum class NoteBox { NOTE, CALENDAR, REMINDER, TRASH, ARCHIVED }

sealed class NavModel {

    data class NavMenuItem(
        val id: Long,
        @DrawableRes val icon: Int,
        @StringRes val titleRes: Int,
        var checked: Boolean,
        val noteBox: NoteBox
    ) : NavModel()

    data class NavDivider(val title: String) : NavModel()

    data class NavLabelItem(
        val id: Long,
        val name: String
    ) : NavModel()
}

object ModelItemDiff : DiffUtil.ItemCallback<NavModel>() {

    override fun areItemsTheSame(
        old: NavModel,
        aNew: NavModel
    ) = old !is NavModel.NavDivider && old === aNew

    override fun areContentsTheSame(
        old: NavModel,
        aNew: NavModel
    ) = old.sameAndEqual(aNew)
}
