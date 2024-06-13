@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.frogmistores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.frogmistores.core.presentation.designsystem.FrogmiStoresTheme
import com.example.frogmistores.navigation.NavigationRoot
import com.example.frogmistores.presentation.storeDetail.StoreDetailScreenRoot
import com.example.frogmistores.presentation.storeList.StoreListScreenRoot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen().setKeepOnScreenCondition{
            viewModel.state.value.themeValue != null
        }
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()
            FrogmiStoresTheme(
                darkTheme = state.themeValue ?: false
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (state.themeValue != null) {
                        val navController = rememberNavController()
                        NavigationRoot(
                            isDarkTheme = state.themeValue ?: false,
                            onThemeUpdate = {
                                viewModel.onAction(MainAction.OnUpdateTheme)
                            },
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
