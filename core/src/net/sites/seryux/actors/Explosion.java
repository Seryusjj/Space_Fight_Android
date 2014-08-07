package net.sites.seryux.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.Sprite;

public class Explosion extends Actor {

	private float duration;
	private Animation explosion;

	public Explosion() {
		super(new Sprite("effects/explosionSpriteSheet.png", 0, 0, 64, 64));
		setSize(128, 128);
		createExplosionAnim();
	}

	private void createExplosionAnim() {
		Sprite explosion = new Sprite("effects/explosionSpriteSheet.png", 0, 0,
				64, 64);
		TextureRegion asteroidRegion = new TextureRegion(explosion, 0, 0, 256,
				256);
		TextureRegion[] explosionFrames = new TextureRegion[16];

		TextureRegion[][] temp = asteroidRegion.split(256 / 4, 256 / 4);
		int k = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				explosionFrames[k] = temp[i][j];
				k++;
			}

		}

		this.explosion = new Animation(0.1f, explosionFrames);
		this.explosion.setPlayMode(Animation.PlayMode.NORMAL);

	}
	
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
		
		if (!( this.explosion.isAnimationFinished(duration))) {
			play();
			batch.draw(getSprite(), getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(),
					getRotation());
		} else {
			setVisible(false);
		}

	}

	private void play() {
		duration += Gdx.graphics.getDeltaTime();
		getSprite().setRegion(explosion.getKeyFrame(duration));

	}

}
