package org.sopt.android_alertcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import org.sopt.android_alertcare.presentation.navigation.MainNavHost
import org.sopt.android_alertcare.ui.theme.AndroidAlertCareTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        enableEdgeToEdge()
        setContent {
            AndroidAlertCareTheme {
                val navController = rememberNavController()
                MainNavHost(
                    navController = navController,
                )

            }
        }
    }
}
