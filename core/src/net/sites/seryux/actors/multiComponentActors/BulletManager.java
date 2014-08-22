package net.sites.seryux.actors.multiComponentActors;

import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.AssetsManagerLvl1;
import net.sites.seryux.utils.CountDownTimer;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BulletManager extends Actor {
	private int numBullets = 30;
	@SuppressWarnings("unused")
	private Stage escenario;
	private Bullet[] bullets;
	private BulletLight[] bulletLights;
	private Actor parent;
	private Music laserMusic;

	private CountDownTimer timer;

	public enum ShootType {
		One, Two, Three
	};

	public ShootType currentShootType = ShootType.One;
	private int bulletIndex = 0;

	public BulletManager(Stage escenario, Actor parent) {
		super(null);
		this.escenario = escenario;
		bullets = new Bullet[numBullets];
		bulletLights = new BulletLight[numBullets];
		for (int i = 0; i < numBullets; i++) {
			bullets[i] = new Bullet(parent);
			bulletLights[i] = new BulletLight(parent);
			escenario.addActor(bullets[i]);
			escenario.addActor(bulletLights[i]);
		}
		timer = new CountDownTimer(0.7f);
		laserMusic = AssetsManagerLvl1.getManager().getLaserMusic();
		this.parent = parent;

	}

	public void act(float delta) {

		if (parent.isVisible() && timer.hasFinished(delta)) {
			timer.reset();
			if (currentShootType.equals(ShootType.One)) {
				shootOne();
				playSound();
			} else if (currentShootType.equals(ShootType.Two)) {
				shootTwo();
				playSound();
			} else if (currentShootType.equals(ShootType.Three)) {
				shootThree();
				playSound();
			}
		}
		super.act(delta);
	}

	private void playSound() {
		if (laserMusic.isPlaying()) {
			laserMusic.stop();
		} 
			laserMusic.play();
		
	}

	public void draw(Batch batch, float parentAlpha) {
		// dont't draw anything
	}

	protected void updateBounds() {
		// since it has no sprite, do not update the bounds
	}

	public void setColorRed() {
		for (int i = 0; i < numBullets; i++) {
			bullets[i].setColorRed();
			bulletLights[i].setColorRed();
		}
	}

	public void setColorGreen() {
		for (int i = 0; i < numBullets; i++) {
			bullets[i].setColorGreen();
			bulletLights[i].setColorGreen();
		}
	}

	public int getBulletIndex() {
		bulletIndex = ++bulletIndex % numBullets;

		return bulletIndex;
	}

	private void shootOne() {
		int index = getBulletIndex();
		bullets[index].setPositionCenter();
		bullets[index].setVisible(true);
		bulletLights[index].setPositionCenter();
		bulletLights[index].setVisible(true);
	}

	private void shootTwo() {
		int index = getBulletIndex();
		bullets[index].setPositionLeft();
		bullets[index].setVisible(true);
		bulletLights[index].setPositionLeft();
		bulletLights[index].setVisible(true);

		index = getBulletIndex();
		bullets[index].setPositionRight();
		bullets[index].setVisible(true);
		bulletLights[index].setPositionRight();
		bulletLights[index].setVisible(true);
	}

	private void shootThree() {
		int index = getBulletIndex();
		bullets[index].setPositionLeft();
		bullets[index].setVisible(true);
		bulletLights[index].setPositionLeft();
		bulletLights[index].setVisible(true);

		index = getBulletIndex();
		bullets[index].setPositionRight();
		bullets[index].setVisible(true);
		bulletLights[index].setPositionRight();
		bulletLights[index].setVisible(true);

		index = getBulletIndex();
		bullets[index].setPositionCenter();
		bullets[index].setVisible(true);
		bulletLights[index].setPositionCenter();
		bulletLights[index].setVisible(true);
	}

	public Actor[] getBullets() {
		return bullets;
	}

	public Actor[] getBulletLights() {
		return bulletLights;
	}

	public void reset() {
		currentShootType = ShootType.One;
		setColorRed();

	}

}
