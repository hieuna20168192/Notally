package com.example.notally.repos.model

import androidx.recyclerview.widget.DiffUtil
import com.example.notally.extension.sameAndEqual

data class Note(
    val id: Long = -1L,
    val title: String = "",
    val content: String = "",
    val bulletPoints: MutableList<BulletPoint> = mutableListOf()
)

object NoteDiff : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem.sameAndEqual(newItem)
}
