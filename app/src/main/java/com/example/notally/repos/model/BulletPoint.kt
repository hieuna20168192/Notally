package com.example.notally.repos.model

import androidx.recyclerview.widget.DiffUtil

data class BulletPoint(
    val name: String = "",
    val checked: Boolean = false
)

object BulletPointDiff : DiffUtil.ItemCallback<BulletPoint>() {
    override fun areItemsTheSame(oldItem: BulletPoint, newItem: BulletPoint) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: BulletPoint, newItem: BulletPoint) =
        oldItem.checked == newItem.checked
}
