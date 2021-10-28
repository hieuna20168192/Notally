package com.example.notally.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding>:
    AppCompatActivity(), IBaseScreen {

    @get: LayoutRes
    protected abstract val layoutId: Int

    @get: StyleRes
    protected var themeId: Int? = null

    protected lateinit var binding: B

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        themeId?.let {
            setTheme(it)
        }

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        initComponents()
        initListeners()
    }

    abstract fun initComponents()

    abstract fun initListeners()
}
