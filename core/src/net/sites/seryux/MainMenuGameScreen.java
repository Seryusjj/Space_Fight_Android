package net.sites.seryux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import net.sites.seryux.utils.GameScreen;
import net.sites.seryux.utils.GameState;

public class MainMenuGameScreen extends GameScreen {

	private Table table;
	private Skin skin;
	TextButton startGame;

	// ImageButton

	public MainMenuGameScreen(MyGdxGame game) {
		super(game);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		table = new Table();
		table.setFillParent(true);
		Label label = new Label("Sergio", skin);
		startGame = new TextButton("Start", skin);
		startGame.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameState.getGameState().ready = true;
				return true;
			}
		});

		table.add(startGame);
		table.row();
		table.add(label);

		escenario.addActor(table);
		// input proccessor ahora es el escenario
		// si no los botones no se actulizan como funcionalidad

		Gdx.input.setInputProcessor(escenario);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 0f, 0f, 1);
		escenario.draw();
		escenario.act();

	}

}
