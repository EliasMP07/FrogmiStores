
package com.example.frogmistores.presentation.storeList

import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.frogmistores.core.presentation.ui.connectivityState
import com.example.frogmistores.core.presentation.ui.utils.ConnectionState
import com.example.frogmistores.data.utils.Const
import com.example.frogmistores.domain.model.Store
import com.example.frogmistores.presentation.storeList.components.CustomSnackbar
import com.example.frogmistores.presentation.storeList.components.ItemStore
import com.example.frogmistores.presentation.storeList.components.ShimmerListStoreItem
import com.example.frogmistores.presentation.storeList.components.ThemeSwitcher

@Composable
fun StoreListScreenRoot(
    isDarkTheme: Boolean,
    onStoreClickDetail: (Store) -> Unit,
    onThemeUpdate: () -> Unit,
    viewModel: StoreListViewModel = hiltViewModel()
) {
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
        isDarkTheme = isDarkTheme,
        onAction = { action ->
            when (action) {
                is StoreListAction.OnStoreDetailClick -> {
                    onStoreClickDetail(action.store)
                }
                StoreListAction.OnUpdateTheme -> onThemeUpdate()
            }
        },
        stores = stores
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StoreListScreen(
    isDarkTheme: Boolean,
    onAction: (StoreListAction) -> Unit,
    stores: LazyPagingItems<Store>
) {
    val context = LocalContext.current
    val connection by connectivityState()
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    val snackbarState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = connection) {
        if (connection == ConnectionState.Unavailable) {
            snackbarState.showSnackbar(
                withDismissAction = true,
                message = context.getString(R.string.offine_text),
                duration = SnackbarDuration.Short
            )
        }
    }
    val lazyColumnState = rememberLazyListState()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection).background(MaterialTheme.colorScheme.background),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarState,
                modifier = Modifier.fillMaxWidth()
            ) { data ->
                CustomSnackbar(snackbarData = data)
            }
        },
        topBar = {
            FrogmiStoreToolbar(
                scrollBehavior = scrollBehavior,
                showBackButton = false,
                isSwitchTheme = true,
                switchContent = {
                    ThemeSwitcher(
                        darkTheme = isDarkTheme,
                        size = 40.dp
                    ) {
                        onAction(StoreListAction.OnUpdateTheme)
                    }
                },
                title = stringResource(id = R.string.app_name),
                startContent = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = stringResource(R.string.content_description_icon_app)
                    )
                }
            )
        },
    ) { padding ->
        Crossfade(targetState = stores.loadState.refresh is LoadState.Loading,
            label = ""
        ) { isLoading ->
            if (isLoading) {
                LazyColumn(
                    contentPadding = PaddingValues(10.dp),
                    state = lazyColumnState,
                    modifier = Modifier
                        .padding(padding)
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
                        .padding(padding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(stores) { store ->
                        store?.let {
                            ItemStore(
                                store = it,
                                onStoreClick = {store ->
                                    onAction(StoreListAction.OnStoreDetailClick(store))
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
}

