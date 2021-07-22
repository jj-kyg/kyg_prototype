package com.kyg_prototype.kyg

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kyg_prototype.kyg.ui.theme.Game


class GameViewModel : ViewModel() {
    private var _games = MutableLiveData(listOf<Game>())
    val games: LiveData<List<Game>> = _games

    fun addGame(game: Game) {
        _games.value = _games.value!! + listOf(game)
    }
}