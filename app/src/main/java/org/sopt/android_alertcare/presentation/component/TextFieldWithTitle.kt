package org.sopt.android_alertcare.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.android_alertcare.ui.theme.AlertTypography
import org.sopt.android_alertcare.ui.theme.Orange

@Composable
fun TextFieldWithTitle(
    titleText: String,
    hint: String,
    maxLength: Int,
    textState: MutableState<String>,
    subText: String? = null,
    isError: Boolean = false,
    isValid: Boolean = false // ✅ 추가
) {
    Column {
        Text(
            text = titleText,
            style = AlertTypography.Bold24,
        )
        subText?.let {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = it,
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = when {
                        isError -> Color.Red
                        isValid -> Orange
                        else -> Color(0xFFBDBDBD)
                    },
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BasicTextField(
                    value = textState.value,
                    onValueChange = {
                        if (it.length <= maxLength) {
                            textState.value = it
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    decorationBox = { innerTextField ->
                        if (textState.value.isEmpty()) {
                            Text(
                                text = hint,
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                )

                Text(
                    text = "${textState.value.length}/$maxLength",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}
