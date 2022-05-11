package com.app.game

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ListOfTextViewAdapter(
    private val context: Context,
    private var listOfRowAndColumn: ArrayList<Int>,
    val mainActivity: TextViewGetPositionInterf
) : RecyclerView.Adapter<ListOfTextViewAdapter.GenerateTextView>() {

    var tempListOfRowAndColumn = arrayListOf<Int>()
    var tempListOfRowAndColumnRemove = arrayListOf<Int>()
    var min = 0
    var max = 0
    var random = 0
    var currentPos = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenerateTextView {
        return GenerateTextView(
            LayoutInflater.from(context).inflate(R.layout.list_item_create_textview, parent, false)
        )
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: GenerateTextView, position: Int) {
        holder.tvWhite.setBackgroundColor(Color.WHITE)
        Log.e("TAG", "onBindViewHolder: $currentPos")

        if (tempListOfRowAndColumn.size > 0)
            for (i in 0 until tempListOfRowAndColumn.size) {
                if (tempListOfRowAndColumn[i] == position) {
                    holder.tvWhite.setBackgroundColor(Color.CYAN)
                }
            }

        if (currentPos == position) {
            tempListOfRowAndColumn.add(currentPos)
            tempListOfRowAndColumnRemove.remove(currentPos)
            holder.tvWhite.setBackgroundColor(Color.RED)
        }

        holder.tvWhite.setOnClickListener {
            if (tempListOfRowAndColumnRemove.size != 0) {
                if (currentPos == position) {

                    min = 0
                    max = tempListOfRowAndColumnRemove.size - 1
                    Log.e("TAG", "${tempListOfRowAndColumnRemove.size}")
                    random = Random().nextInt(max - min + 1) + min
                    currentPos = tempListOfRowAndColumnRemove[random]
                    Log.e("TAG", "$currentPos: ")
                    notifyDataSetChanged()
                }
            } else {
                holder.tvWhite.setBackgroundColor(Color.CYAN)
                Toast.makeText(context, "Game is Over", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfRowAndColumn.size
    }


    inner class GenerateTextView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWhite: TextView = itemView.findViewById(R.id.tvListGame)
    }

    init {
        tempListOfRowAndColumnRemove.addAll(listOfRowAndColumn)
        min = 0
        max = tempListOfRowAndColumnRemove.size - 1
        Log.e("TAG", "${tempListOfRowAndColumnRemove.size}")
        random = Random().nextInt(max - min + 1) + min
        currentPos = tempListOfRowAndColumnRemove[random]
        Log.e("TAG", "$currentPos: ")

    }

}