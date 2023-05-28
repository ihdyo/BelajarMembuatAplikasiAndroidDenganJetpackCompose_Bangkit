package com.example.voyager.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voyager.data.local.TourismEntity
import com.example.voyager.data.repository.Repository
import com.example.voyager.utils.DestinationData
import com.example.voyager.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _allTourism = MutableStateFlow<UiState<List<TourismEntity>>>(UiState.Loading)
    val allTourism = _allTourism.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllTourism().collect { tourism ->
                when (tourism.isEmpty()) {
                    true -> repository.insertAllTourism(DestinationData.sevenWonders)
                    else -> _allTourism.value = UiState.Success(tourism)
                }
            }
        }
    }

    fun updateFavoriteTourism(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteTourism(id, isFavorite)
        }
    }
}