package com.example.voyager.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.voyager.data.local.TourismEntity
import com.example.voyager.utils.UiState
import com.example.voyager.ui.components.AvailableContent

@Composable
fun HomeScreen(navController: NavController) {
    val homeViewModel = hiltViewModel<HomeViewModel>()

    homeViewModel.allTourism.collectAsState(UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Success -> {
                HomeContent(
                    listTourism = uiState.data,
                    navController = navController
                )
            }

            else -> {}
        }
    }
}

@Composable
fun HomeContent(
    listTourism: List<TourismEntity>,
    navController: NavController
) {
    Column {
        when (listTourism.isEmpty()) {
            false -> AvailableContent(listTourism, navController)
            else -> {}
        }
    }
}

