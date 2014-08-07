package net.sites.seryux.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ShipControlsInput {

	private VirtualController controlador;
	private Actor actor;
	private float actorCenterX;
	private float actorCenterY;
	float offsetY;
	float offsetX;

	public ShipControlsInput(VirtualController controlador, Actor actor) {
		this.controlador = controlador;
		this.actor = actor;
		actorCenterY = actor.getCenterY();
		actorCenterX = actor.getCenterY();
		offsetY = actor.getHeight() / 2;
		offsetX = actor.getWidth() / 2;
	}

	private boolean entradaTeclado() {
		if (Gdx.input.isKeyPressed(Input.Keys.UP)
				&& Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			controlador.clean();
			controlador.moverArribaIzquierda = true;
			return true;
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)
				&& Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			controlador.clean();
			controlador.moverArribaDerecha = true;
			return true;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)
				&& Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			controlador.clean();
			controlador.moverAbajoDerecha = true;
			return true;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)
				&& Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			controlador.clean();
			controlador.moverAbajoIzquierda = true;
			return true;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			controlador.clean();
			controlador.moverAbajo = true;
			return true;
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			controlador.clean();
			controlador.moverArriba = true;
			return true;
		} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			controlador.clean();
			controlador.moverIzquierda = true;
			return true;
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			controlador.clean();
			controlador.moverDerecha = true;
			return true;
		} else {
			controlador.clean();
			return false;
		}
	}

	private boolean entradaTactil() {
		int x = 0;
		int y = 0;

		if (Gdx.input.isTouched()) {

			x = Gdx.input.getX();
			y = Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight());
			actorCenterY = actor.getCenterY();
			actorCenterX = actor.getCenterX();

			if (x > actorCenterX + offsetX && y > actorCenterY + offsetY) {
				controlador.clean();
				controlador.moverArribaDerecha = true;

			} else if (x < actorCenterX - offsetX && y > actorCenterY + offsetY) {
				controlador.clean();
				controlador.moverArribaIzquierda = true;
			} else if (x < actorCenterX - offsetX && y < actorCenterY - offsetY) {
				controlador.clean();
				controlador.moverAbajoIzquierda = true;
			} else if (x > actorCenterX + offsetX && y < actorCenterY - offsetY) {
				controlador.clean();
				controlador.moverAbajoDerecha = true;
			} else if (x >= actorCenterX
					&& (y >= actorCenterY - offsetY && y <= actorCenterY
							+ offsetY)) {
				controlador.clean();
				controlador.moverDerecha = true;

			} else if (x <= actorCenterX
					&& (y >= actorCenterY - offsetY && y <= actorCenterY
							+ offsetY)) {
				controlador.clean();
				controlador.moverIzquierda = true;

			} else if (y >= actorCenterY
					&& (x >= actorCenterX - offsetX && x <= actorCenterX
							+ offsetX)) {
				controlador.clean();
				controlador.moverArriba = true;

			} else if (y <= actorCenterY
					&& (x >= actorCenterX - offsetX && x <= actorCenterX
							+ offsetX)) {
				controlador.clean();
				controlador.moverAbajo = true;

			} else {
				controlador.clean();
			}
			return true;
		}
		return false;
	}

	/**
	 * Prioridad en entrada tactil
	 */
	public void upadte() {
		if (!entradaTactil()) {
			entradaTeclado();
		}
	}

	public void setActor(Actor actor) {
		this.actor = actor;
		actorCenterY = actor.getCenterY();
		actorCenterX = actor.getCenterY();
		offsetY = actor.getHeight() / 2;
		offsetX = actor.getWidth() / 2;

	}

}
