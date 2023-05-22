package com.cryptodimond.presentation.ui.aboutscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptodimond.domain.repository.ICryptoRepository
import com.cryptodimond.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutApiUsageViewModel @Inject constructor(
    private val repository: ICryptoRepository
): ViewModel() {

    var state by mutableStateOf(AboutApiUsageState())
        private set

    fun loadApiUsageInfo(){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            when(val result = repository.getApiUsageInfo()){
                is Resource.Success -> {
                    state = state.copy(
                        apiUsageInfo = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        apiUsageInfo = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}