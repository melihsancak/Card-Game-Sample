package com.example.tradingCardGame.tradingCardGame.builder;

import com.example.tradingCardGame.tradingCardGame.Card;
import com.example.tradingCardGame.tradingCardGame.Player;
import com.example.tradingCardGame.tradingCardGame.TradingCardLogic;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.List;

public class PlayerBuilder {

    private String playerName;
    private int currentHealth=30;
    private int manaSloth=0;
    private int mana=0;
    private TradingCardLogic tradingCardLogic;
    private List<Card> deck = Card.list(0,0,1,1,2,2,2,3,3,3,3,4,4,4,5,5,6,6,7,8);
    private List<Card> hand = new ArrayList<>();

    private static PlayerBuilder createPlayer(){
        return new PlayerBuilder();
    }
    public Player build(){
        return new Player(playerName, tradingCardLogic,currentHealth, manaSloth,mana,deck,hand);
    }
    public static Player givenPlayer(){
        return createPlayer().build();
    }

    public PlayerBuilder withCardNoDeck(){
        this.deck =new ArrayList<>();
        return this;
    }
    public PlayerBuilder withCardsInDeck(Integer... manaCost) {
        this.deck = stream(manaCost).map(Card::new).collect(toCollection(ArrayList::new));
        return this;
    }
    public PlayerBuilder withCardsInHand(Integer... manaCost) {
        this.hand = stream(manaCost).map(Card::new).collect(toCollection(ArrayList::new));
        return this;
    }

    public PlayerBuilder withNoCardsInHand() {
        this.hand = new ArrayList<>();
        return this;
    }

    public PlayerBuilder withManaSlots(int manaSlots) {
        this.manaSloth = manaSlots;
        return this;
    }

    public PlayerBuilder withMana(int mana) {
        this.mana = mana;
        return this;
    }

    public PlayerBuilder withHealth(int health) {
        this.currentHealth= health;
        return this;
    }
}
