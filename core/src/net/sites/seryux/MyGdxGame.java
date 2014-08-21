package net.sites.seryux;

import net.sites.seryux.utils.CountDownTimer;
import net.sites.seryux.utils.GameState;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

	private LoadingScreen mainMenu;
	
	protected MainGameScreen mainScreen;

	private GameOverGameScreen gameOver;
	private CountDownTimer timer;
	


	@Override
	public void create() {
		mainMenu = new LoadingScreen(this);
		
		setScreen(mainMenu);
		timer = new CountDownTimer(2);
	}

	@Override
	public void render() {
		super.render();
		if (GameState.getGameState().gameOver) {
			if (timer.hasFinished()) {
				timer.reset();
				if (gameOver == null) {
					gameOver = new GameOverGameScreen(this);
				}
				GameState.getGameState().gameOver = false;//reset
				setScreen(gameOver);
			}
		} else if (GameState.getGameState().ready) {
			GameState.getGameState().ready = false; //reset
			mainScreen.reset();
			if (mainScreen == null) {
				mainScreen = new MainGameScreen(this);
			}

			setScreen(mainScreen);
		}

	}
	
	@Override
	public void dispose() {
		getScreen().pause();
		super.dispose();
	}
	@Override
	public void resume() {
		getScreen().resume();
		super.resume();
	}

}
