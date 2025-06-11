package com.example.notes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.NoteModel

class NoteAdapter : ListAdapter<NoteModel, NoteAdapter.MyViewHolder>(DiffCallback()) {

    private var onclicked: ((NoteModel) -> Unit)? = null
    fun setonOnClicked(listner: (NoteModel) -> Unit) {
        onclicked = listner
    }

    private var longclicked: ((NoteModel) -> Unit)? = null
    fun setonLongClicked(listener: (NoteModel) -> Unit) {
        longclicked = listener
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.itemTitle)
        val body = view.findViewById<TextView>(R.id.itemBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = getItem(position)
        holder.title.text = note.title
        holder.body.text = note.content

        holder.itemView.setOnClickListener {
            onclicked?.invoke(note)
        }

        holder.itemView.setOnLongClickListener {
            longclicked?.invoke(note)
            true
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id || oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

    }
}