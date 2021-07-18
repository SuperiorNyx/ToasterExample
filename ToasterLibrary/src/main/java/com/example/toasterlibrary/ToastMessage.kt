package com.example.toasterlibrary

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ToastMessage {
    val gson: Gson = Gson()
    fun loadInfo(c: Context?, text: String,  volleyCallback: VCallback) {
        val queue = Volley.newRequestQueue(c)
        val type = object : TypeToken<List<Person>>(){}.type
        val url = "https://swapi.dev/api/$text"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                //for each parameter in the request, add the response string to an array
                //return the array
                var responseList = gson.fromJson<ArrayList<Person>>(response.getJSONArray("results").toString(), type)
                Log.d("RESULTS", responseList[0].getEyeColor())
//                data.forEach {
//                    result.add("$it: " + response.getString(it) + "\n")
//                }
//                responseList.forEach {
//                    result.add("${it.getPersonName()}: ${it.getEyeColor()}")
//                }
                volleyCallback.onSuccess(responseList)
            },
            {
                Log.d("error", it.localizedMessage)
                Toast.makeText(c,"That didn't work!", Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjectRequest)
    }
}