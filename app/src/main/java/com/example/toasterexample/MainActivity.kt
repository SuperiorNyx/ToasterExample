package com.example.toasterexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toasterlibrary.Person
import com.example.toasterlibrary.ToastMessage
import com.example.toasterlibrary.VCallback

class MainActivity : AppCompatActivity(), RVAdapter.ItemClickListener {
    lateinit var rvAdapter : RVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //var textView: TextView = this.findViewById(R.id.textView0);
        val example = arrayListOf<String>("name", "eye_color")
        ToastMessage.loadInfo(this, "people/", object : VCallback {
            override fun onSuccess(result: ArrayList<Person>) {
                //textView.text = result.toString();
                makeRecyclerList(result)
                Toast.makeText(baseContext, result[0].getPersonName(), Toast.LENGTH_LONG).show()
            }
        })
        //Log.d("RESULTS MAIN", resultsList[0].getPersonName())
    }

    override fun onItemClick(view: View?, position: Int) {
        Toast.makeText(this, "Yay we did it!", Toast.LENGTH_SHORT).show()
    }

    fun makeRecyclerList(people: ArrayList<Person>){
        Log.d("RESULTS MAIN", people[0].getPersonName())
        val recyclerView : RecyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val names = arrayListOf<String>()
        people.forEach {
            names.add(it.getPersonName())
        }
        rvAdapter = RVAdapter(this, names)
        rvAdapter.setClickListener(this)
        recyclerView.adapter = rvAdapter
    }
}