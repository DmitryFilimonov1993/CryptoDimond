package com.cryptodimond.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S : UiStateMVI, in E : UiEventMVI> : ViewModel() {

    abstract val state: Flow<S>

}