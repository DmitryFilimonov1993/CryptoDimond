package com.cryptodimond.presentation.ui.aboutscreen

import androidx.lifecycle.viewModelScope
import com.cryptodimond.base.BaseViewModel
import com.cryptodimond.domain.repository.ICryptoRepository
import com.cryptodimond.domain.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutApiUsageViewModel @Inject constructor(
    private val repository: ICryptoRepository
): BaseViewModel<AboutApiUsageState, AboutApiUsageUiEvent>() {

    private val reducer = AboutApiUsageReducer(AboutApiUsageState.init())

    override val state: StateFlow<AboutApiUsageState>
        get() = reducer.state

    init {
        loadApiUsageInfo()
    }

    private fun sendEvent(event: AboutApiUsageUiEvent) {
        reducer.sendEvent(event)
    }

    private fun loadApiUsageInfo() {
        viewModelScope.launch {
            sendEvent(AboutApiUsageUiEvent.LoadData)
            when(val result = repository.getApiUsageInfo()){
                is Resource.Success -> {
                 sendEvent(AboutApiUsageUiEvent.ShowData(result.data!!))
                }

                is Resource.Error -> {
                   sendEvent(AboutApiUsageUiEvent.ShowError(result.message!!))
                }
            }
        }
    }
}