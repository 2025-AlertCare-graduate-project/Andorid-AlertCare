package org.sopt.android_alertcare.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.sopt.android_alertcare.ui.theme.AlertTypography
import org.sopt.android_alertcare.ui.theme.Orange

@Composable
fun TopBarWithIcon(
    title: String,
    icon: Painter,
    onIconClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(32.dp))

            Text(
                text = title,
                style = AlertTypography.Bold20,
                color = Color.Black,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )


            Icon(
                painter = icon,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .clickable(onClick = onIconClick)
            )

            Spacer(modifier = Modifier.width(8.dp))


        }

        HorizontalDivider(
            color = Orange.copy(alpha = 0.4f), thickness = 1.dp
        )
    }
}
