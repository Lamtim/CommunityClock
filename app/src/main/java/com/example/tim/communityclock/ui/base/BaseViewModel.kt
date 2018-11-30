package com.example.tim.communityclock.ui.base

import android.arch.lifecycle.ViewModel
import java.lang.ref.WeakReference

open class BaseViewModel<N> : ViewModel() {

    lateinit var mInteractor: WeakReference<N>

    fun getInteractor(): N? {
        return mInteractor.get()
    }

    fun setInteractor(navigator: N) {
        this.mInteractor = WeakReference(navigator)
    }

}