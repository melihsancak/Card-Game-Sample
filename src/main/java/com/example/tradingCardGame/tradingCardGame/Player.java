package com.example.tradingCardGame.tradingCardGame;

import com.example.tradingCardGame.tradingCardGame.enumarator.Action;
import com.example.tradingCardGame.tradingCardGame.exception.HandCardException;
import com.example.tradingCardGame.tradingCardGame.exception.IllegalMoveException;
import com.example.tradingCardGame.tradingCardGame.exception.ManaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

public class Player {

    private static final Logger logger = Logger.getLogger(Player.class.getName());

    Random random = new Random();

    private static final int MAXIMUM_HEALTH = 30;
    private static final int MAXIMUM_MANA_SLOTS = 10;
    private static final int STARTING_HAND_SIZE = 3;
    private static final int MAXIMUM_HAND_SIZE = 5;


    public TradingCardLogic getTradingCardLogic() {
        return tradingCardLogic;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> getHand() {
        return hand;
    }

    private int currentHealth = MAXIMUM_HEALTH;
    private int manaSlots = 0;
    private int mana = 0;
    private final String name;
    private final TradingCardLogic tradingCardLogic;
    private List<Card> deck = Card.list(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8);
    private List<Card> hand = new ArrayList<>();


    public Player(String name, TradingCardLogic tradingCardLogic) {

        this.name = name;
        this.tradingCardLogic = tradingCardLogic;
    }

    public  Player(String name, TradingCardLogic tradingCardLogic, int currentHealth, int manaSlots, int mana, List<Card> deck, List<Card> hand) {
        this.name = name;
        this.tradingCardLogic = tradingCardLogic;
        this.currentHealth = currentHealth;
        this.manaSlots = manaSlots;
        this.deck = deck;
        this.hand = hand;
    }

    public int getHealth() {
        return currentHealth;
    }

    public int getMana() {
        return mana;
    }

    public int getNumberOfDeckCards() {
        return deck.size();
    }

    public int getNumberOfHandCards() {
        return hand.size();
    }

    public int getManaSlots() {
        return manaSlots;
    }

    public void refillMana() {
        mana = manaSlots;
    }

    public void giveManaSlot() {
        if (manaSlots < MAXIMUM_MANA_SLOTS) {
            manaSlots++;
        }
    }

    public boolean canPlayCards() {

        if (hand.stream().filter(card -> card.getValue() <= mana).count() > 0) {
            return true;
        }
        return false;
    }

    private void healthAfterHeal(int addedHealth) {
        currentHealth = Math.min(currentHealth + addedHealth, MAXIMUM_HEALTH);
    }

    private void leftHealthAfterDamageTaken(int damage) {
        currentHealth = currentHealth - damage;
    }

    public void playCardValidation(Player opponent) {

        Move move = tradingCardLogic.nextMove(mana, currentHealth, hand);
        Optional<Card> card = move.getCard();
        if (card.isPresent()) {
            playCard(card.get(), opponent, move.getAction());
        } else {
            throw new HandCardException("Cannot play card  from hand +" + hand);
        }
    }

    public void playCard(Card card, Player opponent, Action action) {

        //mana validation
        if (mana < card.getValue()) {
            throw new ManaException("İnsufficient mana for playing cards");
        }

        logger.info(this + "play card" + card + "for" + action);
        mana = card.getValue() - mana;
        hand.remove(card);
        switch (action) {
            case DAMAGE:
                opponent.leftHealthAfterDamageTaken(card.getValue());
                break;
            case HEALİNG:
                this.healthAfterHeal(card.getValue());
                break;
            default:
                throw new IllegalMoveException("Not valid game action: " + action);
        }
    }

    public void drawCard() {
        if (getNumberOfDeckCards() == 0) {
            logger.info(this + "number of cards");
            currentHealth--;
        } else {
            Card card = deck.get(random.nextInt(deck.size()));
            deck.remove(card);
            logger.info(this + "draw card" + card);
            if (getNumberOfDeckCards() < MAXIMUM_HAND_SIZE) {
                hand.add(card);
            } else {
                logger.info(this + "drop card" + card + "from overload!");
            }
        }
    }

    public void drawStartingHand() {
        for (int i = 0; i < STARTING_HAND_SIZE; i++) {
            drawCard();
        }
    }

    public Integer getNumberOfHandCardsWithManaCost(int manaCost) {
        return (int) deck.stream().filter(card -> card.getValue() == manaCost).count();
    }

    public int getNumberOfCardsWithManaCost(int manaCost) {
        return (int) hand.stream().filter(card -> card.getValue() == manaCost).count();
    }


}
