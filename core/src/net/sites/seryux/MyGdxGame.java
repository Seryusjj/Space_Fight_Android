package net.sites.seryux;


import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {


	private MainGameScreen mainScreen;

	@Override
	public void create() {
		setMainScreen(new MainGameScreen(this));
		
		setScreen(mainScreen);
	}


	public MainGameScreen getMainScreen() {
		return mainScreen;
	}

	public void setMainScreen(MainGameScreen mainScreen) {
		this.mainScreen = mainScreen;
	}

}
