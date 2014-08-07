package net.sites.seryux;

import net.sites.seryux.actors.Asteroid;
import net.sites.seryux.actors.Background;
import net.sites.seryux.actors.BulletManager;
import net.sites.seryux.actors.Explosion;
import net.sites.seryux.actors.Ship;
import net.sites.seryux.input.ShipControlsInput;
import net.sites.seryux.input.VirtualController;
import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainGameScreen extends GameScreen {

	private Stage escenario;
	private Asteroid[] asteroids;
	private Ship nave;
	private Explosion explosion;
	private Background bg;
	private VirtualController controlador;
	private ShipControlsInput entrada;
	private BulletManager bulletManager;

	public MainGameScreen(MyGdxGame game) {
		super(game);
		escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight()));

		controlador = new VirtualController();
		nave = new Ship(controlador);
		entrada = new ShipControlsInput(controlador, nave);
		explosion = new Explosion();

	}

	private void initializeAsteroids() {
		asteroids = new Asteroid[10];
		for (int i = 0; i < asteroids.length; i++) {
			asteroids[i] = new Asteroid();

			escenario.addActor(asteroids[i]);
		}
	}

	@Override
	public void show() {
		bg = new Background(escenario);
		initializeAsteroids();
		escenario.addActor(nave);
		bulletManager = new BulletManager(escenario, nave);
		escenario.addActor(bulletManager);
		 bulletManager.toggleColor();
		 bulletManager.currentShootType = BulletManager.ShootType.Three;

	}

	@Override
	public void render(float delta) {
		renderizado(delta);

	}

	@Override
	public void resize(int width, int height) {
		escenario.getViewport().update(width, height);

	}

	private void renderizado(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);

		escenario.draw();
		escenario.act();
		bg.moveBackground();
		entrada.upadte();
		collideAsteroidsAndBullets();
		collideShipAndAsteroids();
	}

	private void collideShipAndAsteroids() {
		if (nave.isVisible()) {
			for (int i = 0; i < asteroids.length; i++) {
				if (asteroids[i].isVisible() && !asteroids[i].isBreaked()) {
					if (nave.getSprite().isCollidingBoxLevel(
							asteroids[i].getSprite())) {
						asteroids[i].breakAnim();
						nave.breakShip();
						explosion.setPosition(nave.getX(), nave.getY());
						escenario.addActor(explosion);
					}
				}
			}
		}
	}

	//add parameter delegate para unificar logica de
	//collideShipAndAsteroids()
	//collideAsteroidsAndBullets()
	private void checkCollisionWithAsteroids(Actor bullet) {
		for (int j = 0; j < asteroids.length; j++) {
			if (asteroids[j].isVisible() && !asteroids[j].isBreaked()) {
				if (bullet.getSprite().isCollidingBoxLevel(
						asteroids[j].getSprite())) {
					// inicia la animacion de break rock
					asteroids[j].breakAnim();
					// lanza la bala fuera de la pantalla
					bullet.setPosition(0, Gdx.graphics.getHeight());
					// bullet.setVisible(false) crea un bug, no usar

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

}
