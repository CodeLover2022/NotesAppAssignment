package com.example.notesappassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappassignment.Database.TheProject
import kotlin.random.Random

class NoteAdapter(val context: Context,val arr:List<TheProject>):RecyclerView.Adapter<NoteAdapter.NoteView>() {
    inner class NoteView(itemView: View):RecyclerView.ViewHolder(itemView) {
        val NoteTv=itemView.findViewById<TextView>(R.id.Textview)
        val card=itemView.findViewById<CardView>(R.id.cardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteView {
       return NoteView(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout,parent,false))
    }

    override fun getItemCount(): Int {
       return arr.size
    }

    override fun onBindViewHolder(holder: NoteView, position: Int) {
        holder.NoteTv.text=arr[position].code

        //set the background color of note
      holder.card.setCardBackgroundColor(holder.itemView.resources.getColor(NoteColor(),null))

    }
    fun NoteColor():Int
    {
        val color_arr=ArrayList<Int>()
        color_arr.add(R.color.note_color1)
        color_arr.add(R.color.note_color2)
        color_arr.add(R.color.note_color3)
        color_arr.add(R.color.note_color4)
        color_arr.add(R.color.note_color5)
        color_arr.add(R.color.note_color6)
        val seed=System.currentTimeMillis().toInt()
        val color= Random(seed).nextInt(color_arr.size)
        return color_arr[color]

    }
}