package com.example.tradingCardGame.tradingCardGame;

import com.example.tradingCardGame.tradingCardGame.enumarator.Action;

import java.util.Optional;

public class Move {

    private final Optional<Card> card;
    private final Action action;

    public Move(Optional<Card> card, Action action) {
        this.card = card;
        this.action = action;
    }

    public Optional<Card> getCard() {
        return card;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Move move = (Move) object;
        if (action != move.action)
            return false;
        if (card != null ? !card.equals(move.card) : move.card != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 37;
        int result = card != null ? card.hashCode() : 0;
        result = prime * result + (action != null ? action.hashCode() : 0);
        return result;

    }
}
