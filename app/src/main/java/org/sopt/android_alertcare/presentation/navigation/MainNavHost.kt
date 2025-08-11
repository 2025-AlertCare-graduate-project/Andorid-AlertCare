package org.sopt.android_alertcare.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.android_alertcare.presentation.SplashScreen
import org.sopt.android_alertcare.presentation.login.SignUpCompleteScreen
import org.sopt.android_alertcare.presentation.login.SignUpScreen
import org.sopt.android_alertcare.presentation.main.MainScreen
import org.sopt.android_alertcare.presentation.navigation.ScreenRoute.VIDEO_SCREEN
import org.sopt.android_alertcare.presentation.signup.LoginCompleteScreen
import org.sopt.android_alertcare.presentation.signup.LoginScreen
import org.sopt.android_alertcare.presentation.video.VideoScreen

@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.SPLASH_SCREEN,
        modifier = modifier
    ) {

        composable(ScreenRoute.SPLASH_SCREEN) {
            SplashScreen(
                onLoginClick = { navController.navigate(ScreenRoute.LOGIN_SCREEN) },
                onSignUpClick = { navController.navigate(ScreenRoute.SIGN_UP_SCREEN) }
            )
        }

        composable(ScreenRoute.LOGIN_SCREEN) {
            LoginScreen(navController = navController)
        }

        composable(
            "${ScreenRoute.MAIN_SCREEN}/{phoneNumber}/{careReceiverName}"
        ) { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            val careReceiverName = backStackEntry.arguments?.getString("careReceiverName") ?: ""

            MainScreen(
                navController = navController,
                phoneNumber = phoneNumber,
                careReceiverName = careReceiverName
            )
        }


        composable(
            route = ScreenRoute.SIGN_UP_COMPLETE_SCREEN
        ) { backStackEntry ->
            SignUpCompleteScreen(
                navController = navController,
            )
        }

        composable(
            route = "${ScreenRoute.LOGIN_COMPLETE_SCREEN}/{phoneNumber}/{careReceiverName}"
        ) { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            val careReceiverName = backStackEntry.arguments?.getString("careReceiverName") ?: ""

            LoginCompleteScreen(
                navController = navController,
                phoneNumber = phoneNumber,
                careReceiverName = careReceiverName
            )
        }
        composable(ScreenRoute.SIGN_UP_SCREEN) { SignUpScreen(navController = navController) }

        composable(
            route = VIDEO_SCREEN,
            arguments = listOf(navArgument("videoId") { type = NavType.LongType })
        ) { backStackEntry ->
            val videoId = backStackEntry.arguments?.getLong("videoId") ?: return@composable
            VideoScreen(videoId = videoId, navController = navController)
        }


    }
}
