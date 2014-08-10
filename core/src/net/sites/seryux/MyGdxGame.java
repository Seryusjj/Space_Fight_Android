package net.sites.seryux;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

	private MainMenuGameScreen mainMenu;
	private MainGameScreen mainScreen;

	@Override
	public void create() {
		mainMenu = new MainMenuGameScreen(this);
		mainScreen = new MainGameScreen(this);
		setScreen(mainMenu);


		
		


	}

	@Override
	public void render() {
		super.render();
		if (GameState.getGameState().gameOver) {
			GameState.getGameState().resetGameState();

		}else if(GameState.getGameState().ready){
			GameState.getGameState().resetGameState();
			setScreen(mainScreen);
		}
	}

}
