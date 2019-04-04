package com.example.tradingCardGame.tradingCardGame;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GameIT {

    private Game game;

    @Before
    public void init() {
        Player player1 = new Player("1", new TradingCardLogic());
        Player player2 = new Player("2", new TradingCardLogic());
        game = new Game(player1, player2);

    }

    @Test
    public void should_return_game_winner_when_game_is_over() {

        game.startGame();
        assertThat(game.getWinner(), isA(Player.class));
    }
}
