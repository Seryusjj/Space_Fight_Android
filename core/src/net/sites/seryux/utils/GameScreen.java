package net.sites.seryux.utils;

import net.sites.seryux.MyGdxGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class GameScreen implements Screen{

	protected MyGdxGame game;
	protected Stage escenario;
	
	public GameScreen(MyGdxGame game){
		this.game = game;
		this.escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()));
	}
	@Override
	public void render(float delta) {
		
		
	}
	
	@Override
	public void resize(int width, int height) {
		escenario.getViewport().update(width, height);

	}



	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
