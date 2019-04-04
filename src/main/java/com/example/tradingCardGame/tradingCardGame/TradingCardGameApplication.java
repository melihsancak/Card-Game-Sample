package com.example.tradingCardGame.tradingCardGame;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TradingCardGameApplication {

	public static void main(String[] args) {


	new Game(new Player("Player1", new TradingCardLogic()), new Player("Player2", new TradingCardLogic())).startGame();
		SpringApplication.run(TradingCardGameApplication.class, args);
	}

}
