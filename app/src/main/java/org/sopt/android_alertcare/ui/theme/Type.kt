package org.sopt.android_alertcare.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sopt.android_alertcare.R

private val NanumSquareRound = FontFamily(
    Font(R.font.nanum_square_round_regular, FontWeight.Normal),
    Font(R.font.nanum_square_round_bold, FontWeight.Bold),
    Font(R.font.nanum_square_round_extra_bold, FontWeight.ExtraBold),
    Font(R.font.nanum_square_round_large, FontWeight.SemiBold)
)

object AlertTypography {

    val ExtraBold24 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    )

    val Bold24 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )

    val ExtraBold20 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    )

    val Bold20 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )

    val Large20 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    )

    val ExtraBold18 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp
    )

    val Bold18 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )

    val Bold16 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )

    val ExtraBold14 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp
    )

    val Bold14 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )

    val Large14 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )

    val Regular14 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )

    val Bold10 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp
    )

    val Large10 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp
    )

    val Regular10 = TextStyle(
        fontFamily = NanumSquareRound,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
}
