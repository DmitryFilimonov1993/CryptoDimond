package com.cryptodimond.presentation.ui.categorydetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.cryptodimond.base.BaseViewModel
import com.cryptodimond.domain.repository.ICryptoRepository
import com.cryptodimond.domain.model.Resource
import com.cryptodimond.presentation.ui.bottomnav.DestinationCategoryDetailsListArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    private val repository: ICryptoRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CategoryDetailsState, CategoryDetailsUiEvent>() {

    private val reducer = CategoryDetailsReducer(CategoryDetailsState.init())

    override val state: StateFlow<CategoryDetailsState>
        get() = reducer.state


    val retriveID = savedStateHandle.get<String>(DestinationCategoryDetailsListArg).orEmpty()

    init {
        Log.i("TEEEEEST", "category id = $retriveID")
        loadCategoryDetails(retriveID)
    }
    fun load(id: String) {
        loadCategoryDetails(retriveID)
    }

//    fun loadEvent(id: String){
//        reducer.sendEvent()
//    }

    private fun sendEvent(event: CategoryDetailsUiEvent) {
        reducer.sendEvent(event)
    }

    private fun loadCategoryDetails(id: String) {
        viewModelScope.launch {
            sendEvent(CategoryDetailsUiEvent.LoadData)
            when (val result = repository.getCoinCategoryDetails(id)) {
                is Resource.Success -> {
                    sendEvent(CategoryDetailsUiEvent.ShowData(result.data!!))
                }
                is Resource.Error -> {
                    sendEvent(CategoryDetailsUiEvent.ShowError(result.message!!))
                }
            }
        }
    }
}