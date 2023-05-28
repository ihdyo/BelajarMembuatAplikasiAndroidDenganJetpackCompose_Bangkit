package com.example.voyager.ui.screen.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.voyager.data.local.TourismEntity
import com.example.voyager.utils.UiState
import com.example.voyager.ui.components.AvailableContent

@Composable
fun FavoriteScreen(navController: NavController) {
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

    favoriteViewModel.allFavoriteTourism.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Success -> {
                FavoriteContent(
                    listFavoriteTourism = uiState.data,
                    navController = navController
                )
            }

            else -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    listFavoriteTourism: List<TourismEntity>,
    navController: NavController
) {
    when (listFavoriteTourism.isEmpty()) {
        false -> AvailableContent(listFavoriteTourism, navController)
        else -> {}
    }
}