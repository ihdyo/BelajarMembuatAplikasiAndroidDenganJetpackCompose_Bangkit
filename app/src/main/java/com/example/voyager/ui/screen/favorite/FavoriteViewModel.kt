package com.example.voyager.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voyager.data.local.TourismEntity
import com.example.voyager.data.repository.Repository
import com.example.voyager.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _allFavoriteTourism = MutableStateFlow<UiState<List<TourismEntity>>>(UiState.Loading)
    val allFavoriteTourism = _allFavoriteTourism.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavoriteTourism()
                .catch { _allFavoriteTourism.value = UiState.Error(it.message.toString()) }
                .collect { _allFavoriteTourism.value = UiState.Success(it) }
        }
    }

    fun updateFavoriteTourism(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteTourism(id, isFavorite)
        }
    }
}