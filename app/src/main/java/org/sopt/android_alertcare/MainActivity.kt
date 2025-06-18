package org.sopt.android_alertcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import org.sopt.android_alertcare.ui.theme.AndroidAlertCareTheme
import org.sopt.android_alertcare.ui.theme.presentation.VideoScreen
import org.sopt.android_alertcare.ui.theme.presentation.navigation.MainNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidAlertCareTheme {
                val navController = rememberNavController()
                MainNavHost(
                    navController = navController,
                )

//                VideoScreen(videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
            }
        }
    }
}
