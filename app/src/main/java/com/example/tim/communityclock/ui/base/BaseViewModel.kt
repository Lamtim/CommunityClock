package com.example.tim.communityclock.ui.base

import android.arch.lifecycle.ViewModel
import java.lang.ref.WeakReference

open class BaseViewModel<N>: ViewModel(){

    private var mNavigator: WeakReference<N>? = null

    fun getNavigator(): N? {
        return mNavigator!!.get()
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }

}