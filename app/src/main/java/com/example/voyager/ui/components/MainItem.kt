package com.example.voyager.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dicoding.gotrip.R
import com.example.voyager.data.local.TourismEntity
import com.example.voyager.ui.navigation.Screen
import com.example.voyager.ui.theme.Yellow500
import com.example.voyager.utils.countStar

@Composable
fun AvailableContent(
    listTourism: List<TourismEntity>,
    navController: NavController,
) {
    LazyColumn {
        items(listTourism, key = { it.name }) { tourism ->
            TourismItem(tourism, navController)
        }
    }
}

@Composable
fun TourismItem(
    tourism: TourismEntity,
    navController: NavController,
) {
    val (id, name, _, location, photoUrl, rating) = tourism

    Card(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .border(1.dp, Color.LightGray.copy(0.5f), MaterialTheme.shapes.small)
            .clickable { navController.navigate(Screen.Detail.createRoute(id ?: 0)) },
    ) {
        Column {
            Box {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = name,
                    contentScale = ContentScale.FillWidth,
                    placeholder = painterResource(R.drawable.placeholder_image),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row {
                            Text(
                                text = name,
                                style = MaterialTheme.typography.h6
                            )
                        }

                        Row {
                            Text(
                                text = location,
                                style = MaterialTheme.typography.subtitle2
                            )
                        }
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val nStar = countStar(rating)
                        repeat(nStar) {
                            Icon(
                                imageVector = Icons.Rounded.Star,
                                contentDescription = name,
                                tint = Yellow500
                            )
                        }
                    }
                }
            }
        }
    }
}