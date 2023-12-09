package com.example.englishforkids

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScoreAdapter(private val userList: List<User>) : RecyclerView.Adapter<ScoreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.individual_score_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        val playerNameTextView: TextView = holder.itemView.findViewById(R.id.nameOfThePlayer)
        val playerScoreTextView: TextView = holder.itemView.findViewById(R.id.ScoreOfThePlayer)

        playerNameTextView.text = user.name
        playerScoreTextView.text = user.score.toString()

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}