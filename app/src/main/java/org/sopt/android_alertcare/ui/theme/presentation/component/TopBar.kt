package org.sopt.android_alertcare.ui.theme.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
        modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("회원가입", modifier = Modifier.padding(12.dp))
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .width(1.dp)
                .background(color = Color.Gray)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun TopBarComponentPreview() {
    TopBar(
    )
}
