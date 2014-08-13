package net.sites.seryux;

import net.sites.seryux.utils.CountDownTimer;
import net.sites.seryux.utils.GameState;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

	private MainMenuGameScreen mainMenu;
	private MainGameScreen mainScreen;

	private GameOverGameScreen gameOver;
	private CountDownTimer timer;

	@Override
	public void create() {
		
		mainMenu = new MainMenuGameScreen(this);
		
		setScreen(mainMenu);
		timer = new CountDownTimer(2);
	}

	@Override
	public void render() {
		super.render();
		if (GameState.getGameState().gameOver) {
			if (timer.hasFinished()) {
				timer.reset();
				if(gameOver==null){
					gameOver = new GameOverGameScreen(this);
				}
				mainScreen.reset();
				GameState.getGameState().resetGameState();
				setScreen(gameOver);
			}
		} else if (GameState.getGameState().ready) {
			GameState.getGameState().resetGameState();
			if (mainScreen == null) {
				mainScreen = new MainGameScreen(this);
			} 

			setScreen(mainScreen);
		}
	}

}
