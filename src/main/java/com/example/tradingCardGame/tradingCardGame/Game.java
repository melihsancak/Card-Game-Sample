package com.example.tradingCardGame.tradingCardGame;

import java.util.Random;
import java.util.logging.Logger;

public class Game {

    private static final Logger logger = Logger.getLogger(Game.class.getName());

    private Player activePlayer;
    private Player opponentPlayer;

    public Game(Player player1, Player player2) {

        this(player1, player2, new InıtıalTurnChooser());
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public Game(Player player1, Player player2, InıtıalTurnChooser inıtıalTurnChooser) {
        activePlayer = inıtıalTurnChooser.decideInitialTurn(player1, player2);
        if (activePlayer == player1) {
            opponentPlayer = player2;
        } else {
            opponentPlayer = player1;
        }
        activePlayer.drawStartingHand();
        opponentPlayer.drawStartingHand();
        opponentPlayer.drawCard();
    }


    public void beginTurn() {
        activePlayer.giveManaSlot();
        activePlayer.refillMana();
        activePlayer.drawCard();
        logger.info(activePlayer + "turn is started");
    }


    public void endTurn() {
        logger.info(activePlayer + "turn  is ended");
        switchPlayer();
    }

    public void switchPlayer() {
        Player switchToActivePlayer = activePlayer;
        switchToActivePlayer = opponentPlayer;
        opponentPlayer = switchToActivePlayer;
    }

    public Player getWinner() {

        if (activePlayer.getHealth() > 1 && opponentPlayer.getHealth() > 1)
            return null;
        if (activePlayer.getHealth() < 1) {
            return opponentPlayer;
        } else if (opponentPlayer.getHealth() < 1){
            return activePlayer;
        }
        return null;
    }



    public void startGame() {
        while (true) {
            if (getWinner() == null) {
                beginTurn();
                while (activePlayer.canPlayCards()) {
                    activePlayer.playCardValidation(opponentPlayer);
                }
                endTurn();
            } else {
                logger.info(getWinner() + "won the game");
                break;
            }
        }
    }


    static class InıtıalTurnChooser {

        private Random random = new Random();

        public Player decideInitialTurn(Player player1, Player player2) {

            Boolean randomBoolean = random.nextBoolean();
            if (randomBoolean)
                return player1;
            return player2;
        }
    }
}
