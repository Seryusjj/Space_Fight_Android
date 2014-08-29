package net.sites.seryux;

import net.sites.seryux.actors.*;
import net.sites.seryux.actors.multiComponentActors.Background;
import net.sites.seryux.actors.multiComponentActors.BulletManager;
import net.sites.seryux.input.ShipControlsInput;
import net.sites.seryux.input.VirtualController;
import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.AssetsManagerSoundsLvl1;
import net.sites.seryux.utils.GameScreen;
import net.sites.seryux.utils.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MainGameScreen extends GameScreen {

	// UI elements
	private Skin skin;
	private Label score;

	// Game elements (Actors)
	private Asteroid[] asteroids;
	private Ship nave;
	private LaserUpgrade upgrade;
	private Shield shield;
	private Explosion explosion;
	private Background bg;
	private BulletManager bulletManager;

	// Other non actors game coponets
	private VirtualController controlador;
	private ShipControlsInput entrada;

	public MainGameScreen(MyGdxGame game) {
		super(game);

		initializeGame();
		initializeUI();
		AssetsManagerSoundsLvl1.getManager().playGameLoopLvl1Soun();

	}

	private void initializeUI() {
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		score = new Label("Score: " + GameState.getGameState().score, skin);
		score.setPosition(50, Gdx.graphics.getHeight() - 50);
		escenario.addActor(score);
	}

	private void initShip() {
		controlador = new VirtualController();
		nave = new Ship(controlador);
		entrada = new ShipControlsInput(controlador, nave);
		explosion = new Explosion();
		shield = new Shield(nave);
	}

	private void initializeGame() {

		initShip();
		upgrade = new LaserUpgrade();
		bg = new Background(escenario);
		initializeAsteroids();
		escenario.addActor(nave);
		escenario.addActor(shield);
		bulletManager = new BulletManager(escenario, nave);
		escenario.addActor(bulletManager);
		escenario.addActor(explosion);
		escenario.addActor(upgrade);

	}

	private void initializeAsteroids() {
		asteroids = new Asteroid[10];
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i] = new Asteroid();

			escenario.addActor(asteroids[i]);
		}
	}

	@Override
	public void render(float delta) {
		if (!GameState.getGameState().pause) {
			renderizado(delta);

		}

	}

	private void updateEscenario() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);

		escenario.draw();
		bg.moveBackground();

		escenario.act();
	}

	private void collideWithUpgrade() {
		if (nave.isVisible() && upgrade.isVisible()) {
			if (nave.getSprite().isCollidingBoxLevel(upgrade.getSprite())) {
				if (upgrade.isRed()) {
					bulletManager.setColorRed();
					bulletManager.currentShootType = BulletManager.ShootType.Two;
					upgrade.setVisible(false);
				} else {
					bulletManager.currentShootType = BulletManager.ShootType.Three;
					bulletManager.setColorGreen();
					upgrade.setVisible(false);
				}
			}
		}
	}

	private void activateUpgradeRed() {
		if (GameState.getGameState().score == 300 && !upgrade.isVisible()) {
			upgrade.reset();
			upgrade.setVisible(true);
		}
	}

	private void activateUpgradeGreen() {
		if (GameState.getGameState().score == 1000 && !upgrade.isVisible()) {
			upgrade.reset();
			upgrade.toggle();
			upgrade.setVisible(true);
		}
	}

	private void updateGameState() {
		entrada.upadte();
		collideAsteroidsAndBullets();
		collideShipAndAsteroids();
		activateUpgradeGreen();
		activateUpgradeRed();
		collideWithUpgrade();
		score.setText("Score: " + GameState.getGameState().score);
	}

	private void renderizado(float delta) {
		updateEscenario();
		updateGameState();

	}

	private void collideShipAndAsteroids() {
		if (nave.isVisible() && nave.canDead) {
			for (int i = 0; i < asteroids.length; i++) {
				if (asteroids[i].isVisible() && !asteroids[i].isBreaked()) {
					if (nave.getSprite().isCollidingBoxLevel(
							asteroids[i].getSprite())) {
						asteroids[i].breakAnim();
						AssetsManagerSoundsLvl1.getManager()
								.playRockBreakibngSound();
						nave.setBreaked(true);
						explosion.setPosition(nave.getX(), nave.getY());
						explosion.setVisible(true);
						bulletManager.reset();
						GameState.getGameState().gameOver = true;
						AssetsManagerSoundsLvl1.getManager().loose();

					}
				}
			}
		}
	}

	// add parameter delegate para unificar logica de
	// collideShipAndAsteroids()
	// collideAsteroidsAndBullets()
	private void checkCollisionWithAsteroids(Actor bullet) {
		for (int j = 0; j < asteroids.length; j++) {
			if (asteroids[j].isVisible() && !asteroids[j].isBreaked()) {
				if (bullet.getSprite().isCollidingBoxLevel(
						asteroids[j].getSprite())) {
					// inicia la animacion de break rock
					asteroids[j].breakAnim();
					AssetsManagerSoundsLvl1.getManager()
							.playRockBreakibngSound();
					// lanza la bala fuera de la pantalla
					bullet.setPosition(0, Gdx.graphics.getHeight());
					// bullet.setVisible(false); //crea un bug, no usar
					GameState.getGameState().score += 10;

					break;
				}
			}
		}
	}

	private void collideAsteroidsAndBullets() {
		if (nave.isVisible()) {
			for (int i = 0; i < bulletManager.getBullets().length; i++) {
				if (bulletManager.getBullets()[i].isVisible()) {
					checkCollisionWithAsteroids(bulletManager.getBullets()[i]);
				}
			}
		}

	}

	public void reset() {
		nave.reset();
		explosion.reset();
		shield.setVisible(true);
		GameState.getGameState().resetGameState();
		upgrade.reset();
		bulletManager.reset();
		AssetsManagerSoundsLvl1.getManager().resetGame();

	}

}
