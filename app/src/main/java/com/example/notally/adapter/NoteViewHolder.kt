package com.example.notally.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notally.R
import com.example.notally.databinding.LayoutCheckedListItemBinding
import com.example.notally.databinding.LayoutNoteItemBinding
import com.example.notally.repos.model.BulletPoint
import com.example.notally.repos.model.BulletPointDiff
import com.example.notally.repos.model.Note

class NoteViewHolder private constructor(
    private val noteListener: NoteAdapter.NoteListener,
    private val binding: LayoutNoteItemBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    private val bulletPointAdapter = BulletPointAdapter()

    init {
        binding.run {
            this.listener = noteListener
            rclCheckList.adapter = bulletPointAdapter
        }
    }

    fun bind(note: Note) {
        binding.note = note
        bulletPointAdapter.submitList(note.bulletPoints)
        binding.executePendingBindings()
    }

    companion object {
        fun from(noteListener: NoteAdapter.NoteListener, parent: ViewGroup): NoteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: LayoutNoteItemBinding =
                DataBindingUtil.inflate(inflater, R.layout.layout_note_item, parent, false)
            return NoteViewHolder(noteListener, binding)
        }
    }

    class BulletPointAdapter :
        ListAdapter<BulletPoint, BulletPointAdapter.BulletPointViewHolder>(BulletPointDiff) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            BulletPointViewHolder.from(parent)

        override fun onBindViewHolder(holder: BulletPointViewHolder, position: Int) {
            val bulletPoint = getItem(position)
            holder.bind(bulletPoint)
        }

        class BulletPointViewHolder private constructor(private val binding: LayoutCheckedListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(bulletPoint: BulletPoint) {
                binding.run {
                    this.bulletPoint = bulletPoint
                    executePendingBindings()
                }
            }

            companion object {
                fun from(parent: ViewGroup): BulletPointViewHolder {
                    val inflater = LayoutInflater.from(parent.context)
                    val binding: LayoutCheckedListItemBinding = DataBindingUtil.inflate(
                        inflater,
                        R.layout.layout_checked_list_item,
                        parent,
                        false
                    )
                    return BulletPointViewHolder(binding)
                }
            }
        }
    }
}
