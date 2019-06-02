package com.ehword.mlbplayersearch.Controller.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ehword.mlbplayersearch.Controller.Model.Player
import com.ehword.mlbplayersearch.R

class PlayerAdapter (val context: Context, val players: ArrayList <Player> ): RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.player_list_view, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return players.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPlayer(context,players[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val playerName = itemView.findViewById<TextView>(R.id.playerName)
    val playerTeam = itemView.findViewById<TextView>(R.id.playerTeam)
    val playerPosition = itemView.findViewById<TextView>(R.id.playerPosition)
    val playerDOB = itemView.findViewById<TextView>(R.id.playerDOB)

        fun bindPlayer(context: Context,player:Player){
//            val resourceId = context.resources.getIdentifier(message.userAvatar,"drawable", context.packageName)
//            userImage.setImageResource(resourceId)
//            userImage.setBackgroundColor(UserDataService.returnAvatarColor(message.userAvatarColor))
           playerName.text = player.firstName + " " + player.lastName
            playerTeam.text = player.team
            playerPosition.text = player.position
            playerDOB.text = player.DOB


        }
    }
}
