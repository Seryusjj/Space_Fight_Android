package net.sites.seryux;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;




import net.sites.seryux.actors.Background;
import net.sites.seryux.utils.AssetsManager;
import net.sites.seryux.utils.GameScreen;

public class LoadingScreen extends GameScreen {

	private Table table;
	private Skin skin;
	private Label startGame;
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
		startGame = new Label(
				text + AssetsManager.getManager().getPercentage(), skin);

		table.add(startGame).width(100).height(80);
		// table.row();
		// table.add(label);

		bg = new Background(escenario);
		// input proccessor ahora es el escenario
		// si no los botones no se actulizan como funcionalidad
		escenario.addActor(table);
		Gdx.input.setInputProcessor(escenario);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1f, 0f, 0f, 1);
		escenario.draw();
		escenario.act();
		bg.moveBackground();
		startGame.setText("text " + AssetsManager.getManager().getPercentage()
				* 100 + "%");

		if (AssetsManager.getManager().update()) {
			game.mainScreen = new MainGameScreen(game);
			game.setScreen(game.mainScreen);
		}
	}

}
