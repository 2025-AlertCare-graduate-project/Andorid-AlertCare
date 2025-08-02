package org.sopt.android_alertcare.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.android_alertcare.presentation.LoginCompleteScreen
import org.sopt.android_alertcare.presentation.LoginScreen
import org.sopt.android_alertcare.presentation.MainScreen

@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.LOGIN_SCREEN,
        modifier = modifier
    ) {

        composable(ScreenRoute.MAIN_SCREEN) { MainScreen(navController = navController) }
        composable(ScreenRoute.LOGIN_COMPLETE_SCREEN) { LoginCompleteScreen(navController = navController) }
        composable(ScreenRoute.LOGIN_SCREEN) { LoginScreen(navController = navController) }

    }
}
