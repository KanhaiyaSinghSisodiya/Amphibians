package com.example.amphibians.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibiansRepository
import com.example.amphibians.data.NetworkAmphibiansRepository
import com.example.amphibians.model.AmphibianModelClass
import kotlinx.coroutines.launch

sealed interface Amphibians{
    object Error: Amphibians
    object Loading: Amphibians
    data class Success(val list: List<AmphibianModelClass>): Amphibians
}

class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {

    var uiState: Amphibians by mutableStateOf(Amphibians.Loading)
    private set

    init {
        getAmphibiansData()
    }


     fun getAmphibiansData(){
        viewModelScope.launch {
            uiState = Amphibians.Loading
            try{
                uiState = Amphibians.Success(amphibiansRepository.getAmphibiansData())
            }
            catch (e: Exception){
                uiState = Amphibians.Error
            }
        }
    }
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}