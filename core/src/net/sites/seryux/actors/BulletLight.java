package net.sites.seryux.actors;

import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.AssetsManager;
import net.sites.seryux.utils.CountDownTimer;

class BulletLight extends Actor {

	private Actor parent;
	private boolean toggle;
	boolean shoot;
	private CountDownTimer timer;

	public BulletLight(Actor parent) {
		super(AssetsManager.getManager().getRedLaserLightSprite());

		setSize(64, 64);
		this.parent = parent;
		setVisible(false);
		timer = new CountDownTimer(0.2f);
	}

	public void act(float delta) {
		if (timer.hasFinished(delta)) {
			timer.reset();
			setVisible(false);
		}
		super.act(delta);
	}

	public void toggleColor() {
		toggle = !toggle;
		if (toggle) {
			setSprite(AssetsManager.getManager().getGreenLaserLightSprite());
		} else {
			setSprite(AssetsManager.getManager().getRedLaserLightSprite());

		}
	}

	public void setPositionCenter() {
		float centerXBulletLight = (getSprite().getX() + getSprite().getWidth() / 2);
		float centerX = parent.getX() + (parent.getWidth() / 2)
				- centerXBulletLight;

		this.setX(centerX);
		this.setY(parent.getSprite().getBounds().get(0).y
				+ parent.getSprite().getBounds().get(0).height);
	}

	public void setPositionRight() {
		float centerXBulletLight = (getSprite().getX() + getSprite().getWidth() / 2);
		float centerX = parent.getSprite().getBounds().get(1).getX()
				+ parent.getSprite().getBounds().get(1).getWidth()
				- centerXBulletLight;

		this.setX(centerX);
		this.setY(parent.getSprite().getBounds().get(1).y
				+ parent.getSprite().getBounds().get(1).height);
	}

	public void setPositionLeft() {
		float centerXBulletLight = (getSprite().getX() + getSprite().getWidth() / 2);
		float centerX = parent.getSprite().getBounds().get(1).getX()
				- centerXBulletLight;

		this.setX(centerX);
		this.setY(parent.getSprite().getBounds().get(1).y
				+ parent.getSprite().getBounds().get(1).height);
	}

}
