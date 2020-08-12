package com.course.mindorks.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.course.mindorks.todoapp.R
import com.course.mindorks.todoapp.clicklisteners.ItemClickListeners
import com.course.mindorks.todoapp.model.Notes

class NotesAdapter(val list: List<Notes>,
                   val itemClickListeners: ItemClickListeners)
    : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        val notes = list[position]
        val title = notes.title
        val discreption = notes.description
        holder.textViewTitle.text = title
        holder.textViewDescription.text = discreption
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                itemClickListeners.onClick(notes)
            }

        })


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
    }
}