package net.sites.seryux;

import net.sites.seryux.utils.GameState;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.async.AsyncResult;

public class MyGdxGame extends Game {

	private MainMenuGameScreen mainMenu;
	private MainGameScreen mainScreen;
	AsyncResult<MainGameScreen> result;

	@Override
	public void create() {
		mainMenu = new MainMenuGameScreen(this);
		mainScreen = new MainGameScreen(this);
		setScreen(mainMenu);

		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				mainScreen.initialize();
			}
		});

	}

	@Override
	public void render() {
		super.render();
		if (GameState.getGameState().gameOver) {
			GameState.getGameState().resetGameState();

		} else if (GameState.getGameState().ready) {
			GameState.getGameState().resetGameState();
			setScreen(mainScreen);
		}
	}

}
