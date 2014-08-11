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
				gameOver = new GameOverGameScreen(this);
				GameState.getGameState().resetGameState();
				setScreen(gameOver);
			}
		} else if (GameState.getGameState().ready) {
			GameState.getGameState().resetGameState();
			if (mainScreen != null) {
				mainScreen.dispose();
			} else {

				mainMenu.dispose();
			}
			mainScreen = new MainGameScreen(this);
			setScreen(mainScreen);
		}
	}

}
