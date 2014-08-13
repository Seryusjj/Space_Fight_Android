package net.sites.seryux.actors;

import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.CountDownTimer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BulletManager extends Actor {
	private int numBullets = 40;
	@SuppressWarnings("unused")
	private Stage escenario;
	private Bullet[] bullets;
	private BulletLight[] bulletLights;
	private Actor parent;

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
		this.parent =parent;

	}

	public void act(float delta) {

		if(parent.isVisible() && timer.hasFinished(delta)){
			timer.reset();
			if (currentShootType.equals(ShootType.One)) {
				shootOne();
			} else if (currentShootType.equals(ShootType.Two)) {
				shootTwo();
			} else if (currentShootType.equals(ShootType.Three)) {
				shootThree();
			}
		}
		super.act(delta);
	}

	public void draw(Batch batch, float parentAlpha) {
		// no se dibuja nada, dibuja los hijos
	}

	protected void updateBounds() {
		//
	}

	public void toggleColor() {
		for (int i = 0; i < numBullets; i++) {
			bullets[i].toggleColor();
			bulletLights[i].toggleColor();
		}
	}

	public int getBulletIndex() {
		bulletIndex = ++bulletIndex%numBullets;
	
		return bulletIndex;
	}

	private void shootOne() {
		int index = getBulletIndex();
		bullets[index].setVisible(true);
		bullets[index].setPositionCenter();
		bulletLights[index].setVisible(true);
		bulletLights[index].setPositionCenter();
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

}
