package com.ehword.mlbplayersearch.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.ehword.mlbplayersearch.Controller.Adapters.PlayerAdapter
import com.ehword.mlbplayersearch.Controller.Model.Player
import com.ehword.mlbplayersearch.Controller.Services.PlayerSearchService
import com.ehword.mlbplayersearch.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var playerAdapter: PlayerAdapter

    private fun setupAdaptors (){
        playerAdapter = PlayerAdapter(this,PlayerSearchService.players)
        playerListView.adapter = playerAdapter
        val layoutManager = LinearLayoutManager(this)
        playerListView.layoutManager = layoutManager
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdaptors()
    }

    fun searchButtonClicked (view: View){
        PlayerSearchService.getPlayers(playerSearchText.text.toString(),this) { searchSuccess ->
            if (searchSuccess) {
                playerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this,"Could not search for player",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
