package com.ehword.mlbplayersearch.Controller.Services

import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.ehword.mlbplayersearch.Controller.Model.Player
import com.ehword.mlbplayersearch.Controller.Utilities.URL_PLAYER_SEARCH
import org.json.JSONException
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


object PlayerSearchService {
    val players = ArrayList<Player>()

    fun getPlayers(playerSearchString:String, context: Context, complete: (Boolean) -> Unit){

        val url = URL_PLAYER_SEARCH + playerSearchString +  "%25'"
        println(url)
        val playersRequest = object : JsonObjectRequest(Method.GET,url ,null, Response.Listener{ response ->
            players.clear()
            try {
                val responseString = response.getJSONObject("search_player_all")
                val queryResults = responseString.getJSONObject("queryResults")
                val playerCount = queryResults.getInt("totalSize")
                if (playerCount < 1){
                    Toast.makeText(context,"Could not find any players", Toast.LENGTH_SHORT).show()
                    return@Listener
                }
                if (playerCount == 1){
                    var curPlayer = queryResults.getJSONObject("row")
                    val firstName = curPlayer.getString("name_first")
                    val lastName = curPlayer.getString("name_last")
                    val teamName = curPlayer.getString("team_full")
                    val position = curPlayer.getString("position")
                    val DOB = curPlayer.getString("birth_date")
                    val newPlayer = Player(firstName, lastName, teamName, position, DOB)
                    this.players.add(newPlayer)
                }
                else {
                    val playerResults = queryResults.getJSONArray("row")

                    for (x in 0 until playerCount) {
                        val curPlayer = playerResults.getJSONObject(x)
                        val firstName = curPlayer.getString("name_first")
                        val lastName = curPlayer.getString("name_last")
                        val teamName = curPlayer.getString("team_full")
                        val position = curPlayer.getString("position")
                        val DOB = curPlayer.getString("birth_date")
                        val newPlayer = Player(firstName, lastName, teamName, position, DOB)
                        this.players.add(newPlayer)
                        println(players.count())
                    }
                }
                complete (true)
            } catch (e: JSONException){
                Log.d("JSON", "EXC: " + e.localizedMessage)
                complete (false)
            }
        }, Response.ErrorListener {
                error -> Log.d("Error","Could not retrieve players: $error")
            complete (false)
        }) {

        }

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(playersRequest)
    }
}