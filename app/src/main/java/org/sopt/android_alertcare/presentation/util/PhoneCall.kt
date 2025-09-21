package org.sopt.android_alertcare.presentation.util

import android.content.Context
import android.net.Uri
import android.widget.Toast
import android.content.Intent

fun PhoneCall(context: Context, rawPhone: String) {
    val phone = rawPhone.filter { it.isDigit() || it == '+' } 
    val uri = Uri.fromParts("tel", phone, null)
    val intent = Intent(Intent.ACTION_DIAL, uri)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "전화 앱을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
    }
}
