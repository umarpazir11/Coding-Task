package com.sixt.coding.task.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {

    protected var disposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        this.disposable.dispose()
        super.onCleared()
    }
}