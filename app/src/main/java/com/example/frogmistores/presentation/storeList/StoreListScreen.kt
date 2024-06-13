@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.frogmistores.presentation.storeList

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.frogmistores.R
import com.example.frogmistores.core.presentation.designsystem.component.FrogmiStoreToolbar
import com.example.frogmistores.core.presentation.designsystem.ext.isScrolled
import com.example.frogmistores.data.utils.Const
import com.example.frogmistores.domain.model.Store
import com.example.frogmistores.presentation.storeList.components.ItemStore
import com.example.frogmistores.presentation.storeList.components.ShimmerListStoreItem

@Composable
fun StoreListScreenRoot(
    onStoreClickDetail: () -> Unit,
    onMyFavoriteClick: () -> Unit,
    viewModel: StoreListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val stores = viewModel.storePaggingFlow.collectAsLazyPagingItems()

    val context = LocalContext.current

    LaunchedEffect(key1 = stores.loadState) {
        if (stores.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (stores.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    StoreListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                StoreListAction.OnStoreClick -> onStoreClickDetail()
                StoreListAction.OnMyFavoritesClick -> onMyFavoriteClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },
        stores
    )
}

@Composable
private fun StoreListScreen(
    state: StoreListState,
    onAction: (StoreListAction) -> Unit,
    stores: LazyPagingItems<Store>
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
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo app"
                    )
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
                    onAction(StoreListAction.OnMyFavoritesClick)
                },
                expanded = lazyColumnState.isScrolled()
            )
        }
    ) {
        if (stores.loadState.refresh is LoadState.Loading) {
            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                state = lazyColumnState,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(Const.ITEMS_PER_PAGE) {
                    ShimmerListStoreItem()
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                state = lazyColumnState,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = stores) { stores ->
                    if (stores != null) {
                        ItemStore(
                            isFavorite = false,
                            store = stores,
                            onStoreClick = {
                                onAction(StoreListAction.OnStoreClick)
                            },
                            onFavoriteClick = {
                                onAction(StoreListAction.OnFavoriteClick)
                            }
                        )
                    }
                }
                item {
                    if (stores.loadState.append is LoadState.Loading) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

