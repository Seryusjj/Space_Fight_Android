package net.sites.seryux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import net.sites.seryux.actors.multiComponentActors.Background;
import net.sites.seryux.utils.GameScreen;
import net.sites.seryux.utils.GameState;

public class GameOverGameScreen extends GameScreen {

	private Table table;
	private Skin skin;
	private TextButton startGame;
	private Background bg;
	private Label score;

	// ImageButton

	public GameOverGameScreen(MyGdxGame game) {
		super(game);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		table = new Table();
		table.setFillParent(true);
		// Label label = new Label("Sergio", skin);
		startGame = new TextButton("Restart", skin);

		startGame.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				GameState.getGameState().ready = true;

				return true;
			}
		});

		table.add(startGame).width(100).height(80);
		// table.row();
		// table.add(label);

		bg = new Background(escenario);
		// input proccessor ahora es el escenario
		// si no los botones no se actulizan como funcionalidad
		escenario.addActor(table);

		score = new Label("Score: " + GameState.getGameState().score, skin);
		score.setPosition(50, Gdx.graphics.getHeight() - 50);
		escenario.addActor(score);

		Gdx.input.setInputProcessor(escenario);

	}

	@Override
	public void render(float delta) {
		if (!GameState.getGameState().pause) {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			Gdx.gl.glClearColor(0f, 0f, 0f, 1);
			score.setText("Score: " + GameState.getGameState().score);
			escenario.draw();
			escenario.act();
			bg.moveBackground();
		}
	}

}
