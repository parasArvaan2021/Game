package com.app.game

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), TextViewGetPositionInterf {
    lateinit var rvShowGame: RecyclerView
    lateinit var btnSubmit: Button
    lateinit var etCount: EditText
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSubmit = findViewById(R.id.btnMainSubmit)
        etCount = findViewById(R.id.etMainRowCreated)
        rvShowGame = findViewById(R.id.rvMainCreateView)


        btnSubmit.setOnClickListener {
            val list= arrayListOf<Int>()
            if (etCount.text.toString().isNotEmpty()) {
                count = etCount.text.toString().toInt()
                val row = count * count

                for (i in 0 until row) {
                    list.add(i)
                }
                val adapter= ListOfTextViewAdapter(this, list, this)

                rvShowGame.layoutManager = GridLayoutManager(this, count, LinearLayoutManager.HORIZONTAL, false)
                rvShowGame.adapter =adapter
                etCount.setText("")
            }else
                Toast.makeText(this, "please enter value", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getPosition(pos: Int,view: View) {
        Toast.makeText(this, "${pos + 1}", Toast.LENGTH_SHORT).show()
        view.setBackgroundColor(Color.CYAN)

    }
}