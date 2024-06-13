package com.example.frogmistores.presentation.storeDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.frogmistores.R
import com.example.frogmistores.core.presentation.designsystem.CodeIcon
import com.example.frogmistores.core.presentation.designsystem.FrogmiStoresTheme
import com.example.frogmistores.core.presentation.designsystem.LocationStore
import com.example.frogmistores.core.presentation.designsystem.StoreIcon
import com.example.frogmistores.core.presentation.designsystem.component.FrogmiStoreToolbar
import com.example.frogmistores.presentation.storeList.components.FieldStore
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@ExperimentalMaterial3Api
@Composable
fun StoreDetailScreenRoot(
    onBackClick: () -> Unit,
    viewModel: StoreDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    StoreDetailScreen(
        state = state,
        onAction = {action ->
            when(action){
                StoreDetailAction.OnBackClick -> onBackClick()
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
private fun StoreDetailScreen(
    state: StoreDetailState,
    onAction: (StoreDetailAction) -> Unit
) {
    val uiSettings by remember { mutableStateOf(MapUiSettings()) }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    val storePosition = LatLng(state.store.latitude, state.store.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(storePosition, 15f)
    }
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            FrogmiStoreToolbar(
                showBackButton = true,
                title = "StoreDetail",
                onBackClick = {
                    onAction(StoreDetailAction.OnBackClick)
                }
            )
        }
    ) {
        Box {
            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-100).dp),
                properties = properties,
                uiSettings = uiSettings,
                cameraPositionState = cameraPositionState
            ) {
                AdvancedMarker(
                    state = MarkerState(position = storePosition)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
                ElevatedCard(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    elevation = CardDefaults.elevatedCardElevation(50.dp),
                    shape = RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background),
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                            text = stringResource(R.string.field_store), style = MaterialTheme.typography.titleMedium
                        )
                        FieldStore(
                            modifier = Modifier.padding(20.dp),
                            icon = StoreIcon, text = state.store.name
                        )
                        Text(
                            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                            text = stringResource(R.string.field_code), style = MaterialTheme.typography.titleMedium
                        )
                        FieldStore(
                            modifier = Modifier.padding(20.dp),
                            icon = CodeIcon, text = state.store.code
                        )
                        Text(
                            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                            text = stringResource(R.string.field_location), style = MaterialTheme.typography.titleMedium
                        )
                        FieldStore(
                            modifier = Modifier.padding(20.dp),
                            icon = LocationStore, text = state.store.fullAddress
                        )
                    }
                }
            }
        }

    }
}


@ExperimentalMaterial3Api
@Preview
@Composable
private fun StoreDetailScreenPreview() {
    FrogmiStoresTheme {
        StoreDetailScreen(
            state = StoreDetailState(),
            onAction = {}
        )
    }
}