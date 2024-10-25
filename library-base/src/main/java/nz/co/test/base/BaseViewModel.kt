package nz.co.test.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }
}