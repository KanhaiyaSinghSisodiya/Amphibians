package com.example.amphibians.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.data.Api
import com.example.amphibians.model.AmphibianModelClass
import kotlinx.coroutines.launch
import java.util.Objects

sealed interface Amphibians{
    object Error: Amphibians
    object Loading: Amphibians
    data class Success(val list: List<AmphibianModelClass>): Amphibians
}

class AmphibiansViewModel : ViewModel() {

    var uiState: Amphibians by mutableStateOf(Amphibians.Loading)
    private set

    init {
        getAmphibiansData()
    }


     fun getAmphibiansData(){
        viewModelScope.launch {
            try{
                uiState = Amphibians.Success(Api.retrofitService.getAmphibiansData())
            }
            catch (e: Exception){
                uiState = Amphibians.Error
            }
        }
    }
}