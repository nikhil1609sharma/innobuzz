package com.example.taskapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.model.PostModel


private lateinit var callback: AdapterCallback

class PostAdapter(private var postsList: List<PostModel>, private val callback: AdapterCallback) : RecyclerView.Adapter<PostViewModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewModel {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_layout, parent, false)

        return PostViewModel(itemView)
    }

    override fun getItemCount(): Int {
      return postsList.size
    }

    override fun onBindViewHolder(holder: PostViewModel, position: Int) {
        holder.title.text = postsList[position].title
        val fragment = PostDetailFrag()
        holder.titleLayout.setOnClickListener {
            callback.onItemClicked(position)
        }
    }
}

class PostViewModel(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title:TextView = itemView.findViewById(R.id.txtTitle)
    val titleLayout:LinearLayout = itemView.findViewById(R.id.ll1)
}

interface AdapterCallback {
    fun onItemClicked(position: Int)
}


