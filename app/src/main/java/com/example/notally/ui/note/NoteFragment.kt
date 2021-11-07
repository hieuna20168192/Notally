package com.example.notally.ui.note

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notally.R
import com.example.notally.adapter.MAX_SPAN_COUNT
import com.example.notally.adapter.NoteAdapter
import com.example.notally.base.BaseFragment
import com.example.notally.databinding.FragmentNoteBinding
import com.example.notally.repos.model.Note
import com.example.notally.repos.model.store.NoteStore

class NoteFragment : BaseFragment<NoteViewModel, FragmentNoteBinding>(), NoteAdapter.NoteListener {

    override val layoutId = R.layout.fragment_note

    override val viewModel: NoteViewModel by viewModels()

    private val noteAdapter = NoteAdapter(this)

    override fun initComponents() {
        binding.run {
            rclNotes.layoutManager = GridLayoutManager(
                requireContext(),
                MAX_SPAN_COUNT,
                RecyclerView.VERTICAL,
                false
            )
            rclNotes.adapter = noteAdapter
            rclNotes.setHasFixedSize(false)
            noteAdapter.submitList(NoteStore.notes)
        }
    }

    override fun initListeners() {

    }

    override fun onNoteClick(note: Note) {

    }
}
