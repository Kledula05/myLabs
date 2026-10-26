package com.Ustenko.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.Ustenko.R
import com.Ustenko.data.Match
import java.text.SimpleDateFormat
import java.util.Locale

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    private val items = mutableListOf<Match>()
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

    fun submitList(matches: List<Match>) {
        items.clear()
        items.addAll(matches)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = items[position]
        val context = holder.itemView.context

        if (match.isSingleMode) {
            holder.namesText.text = match.player1Name
            holder.scoreText.text = context.getString(R.string.match_item_single_score, match.targetScore)
        } else {
            holder.namesText.text = "${match.player1Name} — ${match.player2Name}"
            holder.scoreText.text = context.getString(R.string.match_item_score, match.score1, match.score2)
        }
        holder.dateText.text = dateFormat.format(match.playedAt)
    }

    override fun getItemCount(): Int = items.size

    class MatchViewHolder(view: android.view.View) : RecyclerView.ViewHolder(view) {
        val namesText: TextView = view.findViewById(R.id.matchNamesText)
        val dateText: TextView = view.findViewById(R.id.matchDateText)
        val scoreText: TextView = view.findViewById(R.id.matchScoreText)
    }
}
