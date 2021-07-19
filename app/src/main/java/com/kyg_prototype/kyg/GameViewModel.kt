package com.kyg_prototype.kyg

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// When the Start Game button at the bottom of the screen is pressed,
// a game chip should be added to the screen,
// and the text should change to End Game.

class GameViewModel : ViewModel() {
    private var _games = MutableLiveData(listOf<Game>())
    val games: LiveData<List<Game>> = _games

    fun addGame(game: Game) {
        _games.value = _games.value!! + listOf(game)
    }
}