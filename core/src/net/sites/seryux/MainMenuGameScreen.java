package net.sites.seryux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.sites.seryux.utils.GameScreen;

public class MainMenuGameScreen extends GameScreen{

	private Table table;
	private Skin skin;
	
	public MainMenuGameScreen(MyGdxGame game) {
		super(game);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		table = new Table();
		table.setFillParent(true);
		Label label = new Label("Sergio",skin);
		table.add(label);
		escenario.addActor(table);
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 0f, 0f, 1);
		escenario.draw();
		escenario.act();

	}

}
