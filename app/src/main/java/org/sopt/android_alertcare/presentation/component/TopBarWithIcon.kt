package org.sopt.android_alertcare.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
    iconLeft: Painter,
    iconRight: Painter,
    onIconLeftClick: () -> Unit = {},
    onIconRightClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = iconLeft,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier.clickable(onClick = onIconLeftClick)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = iconRight,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier.clickable(onClick = onIconRightClick)
                )
            }

            Text(
                text = title,
                style = AlertTypography.Bold20,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        HorizontalDivider(
            color = Orange.copy(alpha = 0.4f),
            thickness = 1.dp
        )
    }
}
