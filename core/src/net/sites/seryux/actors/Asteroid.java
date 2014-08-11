package net.sites.seryux.actors;

import java.util.Random;

import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Asteroid extends Actor {

	private Animation rotation;
	private Animation breaking;
	private Animation currentAnimation;
	private float duration;
	private boolean breaked;

	private Random rm;
	private int speed;
	// Ancho y alto del sprite sheet
	private static final int ANCHO = 3840;
	private static final int ALTO = 256;

	public Asteroid() {
		super(new Sprite("asteroid/AsteroidSheet.png", 0, 0, 128, 128));
		createAnim();
		setBreaked(false);
		//setDebug(true, new Color(1, 0, 0, 1));
		speed = 150;
		rm = new Random();
		float scale = rm.nextFloat();
		if (scale < 0.6f)
			scale += 0.6f;
		setSize(128 * scale, 128 * scale);
		setPosition(rm.nextInt((int) (Gdx.graphics.getWidth() - getWidth() + 1)),
				Gdx.graphics.getHeight());

	}

	private void createAnim() {

		TextureRegion asteroidRegion = new TextureRegion(getSprite()
				.getTexture(), 0, 0, ANCHO, ALTO);
		TextureRegion[] rotationFrames = new TextureRegion[30];
		TextureRegion[] breakingFrames = new TextureRegion[4];
		TextureRegion[][] temp = asteroidRegion.split(ANCHO / 30, ALTO / 2);
		for (int i = 0; i < 30; i++) {
			rotationFrames[i] = temp[0][i];
		}
		for (int i = 0; i < 4; i++) {
			breakingFrames[i] = temp[1][i];
		}
		rotation = new Animation(0.1f, rotationFrames);
		breaking = new Animation(0.08f, breakingFrames);
		rotation.setPlayMode(Animation.PlayMode.LOOP);
		breaking.setPlayMode(Animation.PlayMode.NORMAL);
		currentAnimation = rotation;
	}

	protected void updateBounds() {
		getSprite()
				.getBounds()
				.get(0)
				.set(getX() + getScaledValueX(20),
						getY() + getScaledValueY(25),
						getWidth() - getScaledValueX(40),
						getHeight() - getScaledValueY(55));

	}

	private void play() {
		duration += Gdx.graphics.getDeltaTime();
		getSprite().setRegion(currentAnimation.getKeyFrame(duration));
	}

	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		if (!(getCurrentAnimation().isAnimationFinished(getDuration()) && isBreaked())) {
			play();
			batch.draw(getSprite(), getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());

		} else {
			setVisible(false);
		}

	}

	public void act(float delta) {

		setPosition(getX(), getY() - speed * Gdx.graphics.getDeltaTime());
		if (getY() <= (0 - getHeight())) {

			setPosition(rm.nextInt((int) (Gdx.graphics.getWidth() - getWidth() + 1)),
					Gdx.graphics.getHeight());
		}

		if (isVisible() == false) {
			setPosition(rm.nextInt((int) (Gdx.graphics.getWidth() - getWidth() + 1)),
					Gdx.graphics.getHeight());
			playRotation();
			setVisible(true);
		}

		super.act(delta);
	}

	private void playRotation() {
		duration = 0;
		breaked = false;
		currentAnimation = rotation;
	}

	private void playBreaking() {
		duration = 0;
		currentAnimation = breaking;
	}

	private void setBreaked(boolean breaked) {
		this.breaked = breaked;
	}

	private Animation getCurrentAnimation() {
		return currentAnimation;
	}

	private float getDuration() {
		return duration;
	}

	public void rotationAnim() {
		if (breaked == true) {
			setBreaked(false);

			playRotation();

		}
	}

	public void breakAnim() {
		if (breaked == false) {
			setBreaked(true);

			playBreaking();

		}
	}

	public boolean isBreaked() {
		return this.breaked;
	}

	public void setCurrentAnimation(Animation currentAnimation) {
		this.currentAnimation = currentAnimation;
	}

}
