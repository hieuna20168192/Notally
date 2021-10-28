package com.example.notally.ui.note

import androidx.fragment.app.viewModels
import com.example.notally.R
import com.example.notally.base.BaseFragment
import com.example.notally.databinding.FragmentNoteBinding

class NoteFragment : BaseFragment<NoteViewModel, FragmentNoteBinding>() {

    override val layoutId = R.layout.fragment_note

    override val viewModel: NoteViewModel by viewModels()

    override fun initComponents() {
        
    }

    override fun initListeners() {

    }
}
