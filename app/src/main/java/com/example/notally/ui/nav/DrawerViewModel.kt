package com.example.notally.ui.nav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notally.base.BaseViewModel
import com.example.notally.repos.model.NavModel
import com.example.notally.repos.model.store.NavStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor() : BaseViewModel() {

    private val _navModalItems: MutableLiveData<List<NavModel>> = MutableLiveData()
    val navModalItems: LiveData<List<NavModel>>
        get() = _navModalItems

    init {
        postListUpdate()
    }

    private fun postListUpdate() {
        val newList = NavStore.navMenuItems +
                NavStore.labelsDivider +
                NavStore.navLabelItems
        _navModalItems.value = newList
    }

    fun setNavigationMenuItemChecked(id: Long) {
        val updated = NavStore.setNavigationMenuItemChecked(id)
        if (updated) {
            postListUpdate()
        }
    }
}
