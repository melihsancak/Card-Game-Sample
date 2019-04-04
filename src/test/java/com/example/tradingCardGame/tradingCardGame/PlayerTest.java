package com.example.tradingCardGame.tradingCardGame;

import com.example.tradingCardGame.tradingCardGame.builder.PlayerBuilder;
import com.example.tradingCardGame.tradingCardGame.enumarator.Action;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.example.tradingCardGame.tradingCardGame.builder.PlayerBuilder.givenPlayer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import com.example.tradingCardGame.tradingCardGame.builder.PlayerBuilder.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {


    private Player player;

    @Mock
    private TradingCardLogic tradingCardLogic;


    @Before
    public void init() {
        player = new Player("Player1", tradingCardLogic);

    }

    @Test
    public void should_player_initially_have_thirty_health() {
        assertThat(player.getHealth(), is(equalTo(30)));
    }

    @Test
    public void should_player_initally_have_zero_mana() {
        assertThat(player.getMana(), is(equalTo(0)));
    }


    @Test
    public void should_player_start_with_empty_hand() {
        assertThat(player.getNumberOfHandCards(), is(equalTo(0)));
    }

    @Test
    public void should_not_health_of_player_cannot_exceed_thirthy_health(){
        //given
        player =  new PlayerBuilder().withCardNoDeck()
                .withHealth(27)
                .withMana(10)
                .withCardsInHand(4)
                .build();
        //when
        player.playCard(new Card(4),player,Action.HEALİNG);
        //then
        assertThat(player.getHealth(),is(30));
  }

    @Test
    public void playingCardAsHealingRestoresHealth() {
        //givenPlayer().withMana(10).withCardsInHand(3, 4).build();
        player =  new PlayerBuilder().withCardNoDeck()
                .withHealth(27)
                .withMana(20)
                .withCardsInHand(4)
                .build();
        //player = new PlayerBuilder().withHealth(10).withMana(10).withCardsInHand(3, 4).build();

        player.playCard(new Card(3), player, Action.HEALİNG);
        assertThat(player.getHealth(), is(17));
    }


}
