package com.example.notally.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> :
    Fragment(), IBaseScreen {

    @get: LayoutRes
    protected abstract val layoutId: Int

    protected lateinit var binding: B
    protected abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        initComponents()
        initListeners()
        return binding.root
    }

    protected abstract fun initComponents()
    protected abstract fun initListeners()
}
