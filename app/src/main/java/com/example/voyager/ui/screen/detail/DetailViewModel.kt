package com.example.voyager.ui.screen.detail

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
class DetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _tourism = MutableStateFlow<UiState<TourismEntity>>(UiState.Loading)
    val tourism = _tourism.asStateFlow()

    fun getTourism(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTourism(id)
                .catch { _tourism.value = UiState.Error(it.message.toString()) }
                .collect { _tourism.value = UiState.Success(it) }
        }
    }

    fun updateFavoriteTourism(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteTourism(id, isFavorite)
        }
    }
}