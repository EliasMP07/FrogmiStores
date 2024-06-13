@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.frogmistores.presentation.storeList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.frogmistores.R
import com.example.frogmistores.core.presentation.designsystem.FrogmiStoresTheme
import com.example.frogmistores.core.presentation.designsystem.component.FrogmiStoreToolbar
import com.example.frogmistores.core.presentation.designsystem.ext.isScrolled
import com.example.frogmistores.presentation.storeList.components.ItemStore
import com.example.frogmistores.presentation.storeList.components.ShimmerListStoreItem

@Composable
fun StoreListScreenRoot(
    //viewModel: StoreListViewModel = hiltViewModel()
) {
    StoreListScreen(
         state = StoreListState(),
         onAction = {}
     )
}

@Composable
private fun StoreListScreen(
    state: StoreListState,
    onAction: (StoreListAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    val lazyColumnState = rememberLazyListState()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            FrogmiStoreToolbar(
                scrollBehavior = scrollBehavior,
                showBackButton = false,
                title = stringResource(id = R.string.app_name),
                startContent = {
                    Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo app")
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                text = {
                    Text(
                        text = stringResource(R.string.myfavorites),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = "Icono de agregar nueva materia",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                onClick = {

                },
                expanded = lazyColumnState.isScrolled()
            )
        }
    ){
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            state = lazyColumnState,
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(10) {
                ShimmerListStoreItem(
                    isLoading = false,
                    contentAfterLoading = {
                        ItemStore(
                            isFavorite = false,
                            onStoreClick = {

                            },
                            onFavoriteClick = {

                            }
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun StoreListScreenPreview() {
    FrogmiStoresTheme {
        StoreListScreen(
            state = StoreListState(),
            onAction = {}
        )
    }
}
