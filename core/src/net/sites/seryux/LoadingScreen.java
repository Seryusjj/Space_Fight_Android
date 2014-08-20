package net.sites.seryux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import net.sites.seryux.actors.multiComponentActors.Background;
import net.sites.seryux.utils.AssetsManager;
import net.sites.seryux.utils.GameScreen;
import net.sites.seryux.utils.GameState;

public class LoadingScreen extends GameScreen {

	private Table table;
	private Skin skin;
	private Label startGame;
	private ProgressBar bar;
	private Background bg;

	private String text;

	// ImageButton

	public LoadingScreen(MyGdxGame game) {
		super(game);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		table = new Table();
		table.setFillParent(true);
		text = "Loading ... ";
		// Label label = new Label("Sergio", skin);
		startGame = new Label(text, skin);
		bar = new ProgressBar(0f, 1f, 0.01f, false, skin);

		table.add(startGame);
		table.row();
		table.add(bar);

		bg = new Background(escenario);
		// input proccessor ahora es el escenario
		// si no los botones no se actulizan como funcionalidad
		escenario.addActor(table);
		if (!GameState.getGameState().pause) {
			Gdx.input.setInputProcessor(escenario);
		}

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 0f, 0f, 1);
		escenario.draw();
		escenario.act();
		bg.moveBackground();
		startGame.setText(text);
		bar.setValue(AssetsManager.getManager().getPercentage());

		if (AssetsManager.getManager().update()
				&& !GameState.getGameState().pause) {
			game.mainScreen = new MainGameScreen(game);
			game.setScreen(game.mainScreen);
		}
	}

}
