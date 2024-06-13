package com.example.frogmistores.navigation

import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.frogmistores.domain.model.Store
import com.example.frogmistores.navigation.utils.NavArgs
import com.example.frogmistores.navigation.utils.Screen
import com.example.frogmistores.navigation.utils.parcelableTypeOf
import com.example.frogmistores.presentation.storeDetail.StoreDetailScreenRoot
import com.example.frogmistores.presentation.storeList.StoreListScreenRoot

@ExperimentalMaterial3Api
@Composable
fun NavigationRoot(
    isDarkTheme: Boolean,
    onThemeUpdate: () -> Unit,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.StoreList.route
    ) {
        composable(
            route = Screen.StoreList.route,
        ) {
            StoreListScreenRoot(
                isDarkTheme = isDarkTheme,
                onStoreClickDetail = { store ->
                    navController.navigate(
                        Screen.StoreDetail.createRoute(store)
                    )
                },
                onThemeUpdate = onThemeUpdate
            )
        }
        composable(
            route = Screen.StoreDetail.route,
            enterTransition = {
                scaleIn()
            },
            exitTransition = {
                scaleOut()
            },
            arguments = listOf(navArgument(NavArgs.StoreID.key) {
                type = parcelableTypeOf<Store>()
            })
        ) {
            StoreDetailScreenRoot(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
