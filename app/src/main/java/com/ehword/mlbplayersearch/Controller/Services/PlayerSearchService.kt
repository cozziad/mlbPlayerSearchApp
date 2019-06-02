package com.ehword.mlbplayersearch.Controller.Services

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.ehword.mlbplayersearch.Controller.Model.Player
import com.ehword.mlbplayersearch.Controller.Utilities.URL_PLAYER_SEARCH
import org.json.JSONException
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ehword.mlbplayersearch.Controller.MainActivity
import com.ehword.mlbplayersearch.R
import kotlinx.android.synthetic.main.activity_main.playerSearchText
import kotlinx.android.synthetic.main.activity_main.playerSearchText


object PlayerSearchService {
    val players = ArrayList<Player>()

    fun getPlayers(playerId:String, context: Context, complete: (Boolean) -> Unit){
        //val playerId =  <TextView>(R.id.playerSearchText)
        val url = URL_PLAYER_SEARCH + playerId
        println(playerId)

        val playersRequest = object : JsonObjectRequest(Method.GET,url ,null, Response.Listener{ response ->
            players.clear()
            try {
                //println(response.length())
                //println(response)
                for (x in 0 until response.length()){
                  val player = response.getJSONObject("player_info")
                    val QR = player.getJSONObject("queryResults")
                    val row = QR.getJSONObject("row")
                    println(row)
                       val firstName = row.getString("name_first")
                       val lastName = row.getString("name_last")
                       val teamName = row.getString("team_name")
                       val position = row.getString("primary_position_txt")
                       val DOB = row.getString("birth_date")
                       val newPlayer = Player(firstName,lastName,teamName,position,DOB)
                       this.players.add(newPlayer)
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
//            override fun getBodyContentType(): String {
//                return "application/json; charset=utf-8"
//            }
//
//            override fun getHeaders(): MutableMap<String, String> {
//                val headers = HashMap<String, String>()
//                headers.put("sport_code", "mlb")
//                headers.put("active_sw", "Y")
//                headers.put("name_part", "mill%")
//                headers.put("search_player_all.col_ex", "player_id")
//                return headers
//            }
        }


        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(playersRequest)
    }
}