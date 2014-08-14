package net.sites.seryux.actors;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.AssetsManager;

public class LaserUpgrade extends Actor {
	private boolean isRed;
	private Random rm;
	private float speed;

	public LaserUpgrade() {
		super(AssetsManager.getManager().getLaserUpdateRed());
		isRed = true;
		setVisible(false);
		rm = new Random();
		speed = 150;
		setPosition(
				rm.nextInt((int) (Gdx.graphics.getWidth() - getWidth() + 1)),
				Gdx.graphics.getHeight());
		setSize(128, 128);
	}

	public void toggle() {
		isRed = !isRed;
		if (isRed) {
			setSprite(AssetsManager.getManager().getLaserUpdateRed());
		} else {
			setSprite(AssetsManager.getManager().getLaserUpdateGreen());
		}
	}

	public void act(float delta) {
		if (isVisible()) {
			setPosition(getX(), getY() - speed * delta);
			if (getY() <= -getHeight()) {
				setVisible(false);
			}
		}
		super.act(delta);
	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}

	protected void updateBounds() {
		getSprite()
				.getBounds()
				.get(0)
				.set(getX() + 10, getY() + 20, getWidth() - 25,
						getHeight() - 25);
		// super.updateBounds();
	}

	public boolean isRed() {
		return isRed;
	}

	public void reset() {
		isRed = true;
		setSprite(AssetsManager.getManager().getLaserUpdateRed());
		setPosition(
				rm.nextInt((int) (Gdx.graphics.getWidth() - getWidth() + 1)),
				Gdx.graphics.getHeight());
		setVisible(false);
	}

}
