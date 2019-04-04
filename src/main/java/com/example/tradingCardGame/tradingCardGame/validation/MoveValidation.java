package com.example.tradingCardGame.tradingCardGame.validation;

import com.example.tradingCardGame.tradingCardGame.Card;
import com.example.tradingCardGame.tradingCardGame.Player;
import com.example.tradingCardGame.tradingCardGame.exception.HandCardException;
import com.example.tradingCardGame.tradingCardGame.exception.IllegalMoveException;
import com.example.tradingCardGame.tradingCardGame.exception.ManaException;

import java.util.Optional;

public class MoveValidation {

    Player player;
    Optional<Card> card;

    public void ManaValidation() {
        if (player.getMana() == 0) {
            throw new ManaException("Not enough mana for move for "+ player.toString());
        }
    }

    public void IllegalMoveException() {
        if (player.getMana() == 0) {
            throw new IllegalMoveException("Illegal move trial for player +" + player.toString());
        }
    }

    public void HandCardMoveException() {
        if (!card.isPresent()) {
            throw new HandCardException("There is no card in given player " + player.toString());
        }
    }
}
