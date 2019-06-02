package com.ehword.mlbplayersearch.Controller.Utilities

const val BASE_URL = "http://lookup-service-prod.mlb.com/json/"
//const val URL_PLAYER_SEARCH = "${BASE_URL}named.search_player_all.bam?"
//
const val URL_PLAYER_SEARCH ="http://lookup-service-prod.mlb.com/json/named.search_player_all.bam?sport_code='mlb'&active_sw='Y'&search_player_all.col_ex=player_id&name_part='"
//const val URL_PLAYER_SEARCH ="http://lookup-service-prod.mlb.com/json/named.player_info.bam?sport_code='mlb'&player_id="
const val URL_TEAM_SEARCH = "/json/named.team_all_season.bam?sport_code='mlb'&all_star_sw={all_star_sw}&sort_order={sort_order}&season={season}"
