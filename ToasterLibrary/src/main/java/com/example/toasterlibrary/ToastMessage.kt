package com.example.toasterlibrary

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

object ToastMessage {
    fun s(c: Context?, text: String) : String {
        val queue = Volley.newRequestQueue(c)
        val url = "https://swapi.dev/api/$text"
        var result : String = ""
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                result = response.getString("eye_color")
            },
            {
                Log.d("error", it.localizedMessage)
                Toast.makeText(c,"That didn't work!", Toast.LENGTH_LONG).show()
            })


        queue.add(jsonObjectRequest)
        Toast.makeText(c, result, Toast.LENGTH_LONG).show()
        return result
    }
}