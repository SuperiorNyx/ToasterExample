package com.example.toasterlibrary

import android.content.Context
import android.widget.Toast

object ToastMessage {
    fun s(c: Context?, text: String?) {
        Toast.makeText(c, text, Toast.LENGTH_SHORT).show()
    }
}