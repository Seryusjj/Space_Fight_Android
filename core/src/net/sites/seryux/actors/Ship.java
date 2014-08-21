package net.sites.seryux.actors;

import net.sites.seryux.input.VirtualController;
import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.AssetsManagerLvl1;
import net.sites.seryux.utils.CountDownTimer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Ship extends Actor {

	private int speed = 300;

	private VirtualController controlador;
	public boolean canDead;
	private CountDownTimer timer;

	private static final int LEFT = 0;
	private static final int IDLE = 128;
	private static final int RIGHT = 256;

	private boolean breaked;

	public Ship(VirtualController controlador) {
		super(AssetsManagerLvl1.getManager().getShipTexture());
		getSprite().setRegion(IDLE, 0, 128, 128);
		setSize(128, 128);

		setPosition((Gdx.graphics.getWidth() - getSprite().getWidth()) / 2,
				(Gdx.graphics.getHeight() - getSprite().getHeight()) / 2);

		this.controlador = controlador;
		// queremos que la nave tenga dos huesos
		getSprite().addBound();// añadimos uno adicional
		// setDebug(true, new Color(1f, 0, 0, 1));

		breaked = false;
		canDead = false;
		timer = new CountDownTimer(5f);

	}

	public boolean isBreaked() {
		return breaked;
	}

	public void setBreaked(boolean breaked) {
		this.breaked = breaked;
	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		if (!canDead && timer.hasFinished()) {
			canDead = true;

		}
		if (!(breaked)) {

			batch.draw(getSprite(), getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());
		} else {
			setVisible(false);
		}

	}

	public void act(float delta) {
		if (!isBreaked()) {
			upadateMovement();
		}
		super.act(delta);

	}

	protected void updateBounds() {
		// collider alas
		getSprite()
				.getBounds()
				.get(1)
				.set(getX() + 20, getY() + 45, getWidth() - 35,
						getHeight() - 100);
		// collider body
		getSprite()
				.getBounds()
				.get(0)
				.set(getX() + 50, getY() + 30, getWidth() - 100,
						getHeight() - 55);
	}

	private void moveLeft() {
		getSprite().setRegion(LEFT, 0, 128, 128);
		addAction(Actions.moveBy(-speed * Gdx.graphics.getDeltaTime(), 0));

	}

	private void moveRight() {
		getSprite().setRegion(RIGHT, 0, 128, 128);
		addAction(Actions.moveBy(speed * Gdx.graphics.getDeltaTime(), 0));
	}

	private void moveDown() {
		getSprite().setRegion(IDLE, 0, 128, 128);
		addAction(Actions.moveBy(0, -speed * Gdx.graphics.getDeltaTime()));
	}

	private void moveDownRight() {
		getSprite().setRegion(RIGHT, 0, 128, 128);
		setPosition(getX() + speed * Gdx.graphics.getDeltaTime(), getY()
				- speed * Gdx.graphics.getDeltaTime());
	}

	private void moveDownLeft() {
		getSprite().setRegion(LEFT, 0, 128, 128);
		setPosition(getX() - speed * Gdx.graphics.getDeltaTime(), getY()
				- speed * Gdx.graphics.getDeltaTime());
	}

	private void moveUp() {
		getSprite().setRegion(IDLE, 0, 128, 128);
		setPosition(getX(), getY() + speed * Gdx.graphics.getDeltaTime());
	}

	private void moveUpRight() {
		getSprite().setRegion(RIGHT, 0, 128, 128);
		setPosition(getX() + speed * Gdx.graphics.getDeltaTime(), getY()
				+ speed * Gdx.graphics.getDeltaTime());
	}

	private void moveUpLeft() {
		getSprite().setRegion(LEFT, 0, 128, 128);
		setPosition(getX() - speed * Gdx.graphics.getDeltaTime(), getY()
				+ speed * Gdx.graphics.getDeltaTime());

	}

	private void upadateMovement() {
		if (controlador.moverAbajo) {
			moveDown();
		} else if (controlador.moverAbajoDerecha) {
			moveDownRight();
		} else if (controlador.moverAbajoIzquierda) {
			moveDownLeft();
		} else if (controlador.moverArriba) {
			moveUp();
		} else if (controlador.moverArribaIzquierda) {
			moveUpLeft();
		} else if (controlador.moverArribaDerecha) {
			moveUpRight();
		} else if (controlador.moverDerecha) {
			moveRight();
		} else if (controlador.moverIzquierda) {
			moveLeft();
		} else {
			getSprite().setRegion(IDLE, 0, 128, 128);
		}
	}

	public void reset() {
		setPosition((Gdx.graphics.getWidth() - getSprite().getWidth()) / 2,
				(Gdx.graphics.getHeight() - getSprite().getHeight()) / 2);
		breaked = false;
		setVisible(true);
		canDead = false;
		timer.reset();

	}

}
