package com.example.tradingCardGame.tradingCardGame;
import com.example.tradingCardGame.tradingCardGame.enumarator.Action;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public  class TradingCardLogic {

    public Move nextMove(int availableMana, int currentHealth, List<Card> availableCards) {
        return new Move(highestCard(availableMana, availableCards), Action.DAMAGE);
    }

    protected Optional<Card> highestCard(int availableMana, List<Card> availableCards) {
        return availableCards.stream().filter(card -> card.getValue() <= availableMana).max(Comparator.<Card>naturalOrder());
    }

    protected Optional<Card> lowestCard(int availableMana, List<Card> availableCards){
        return availableCards.stream().filter(card -> card.getValue() <= availableMana).min(Comparator.<Card>naturalOrder());
    }
}
