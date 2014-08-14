package net.sites.seryux.actors.multiComponentActors;

import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.AssetsManager;
import net.sites.seryux.utils.CountDownTimer;

class BulletLight extends Actor {

	private Actor parent;
	protected boolean shoot;
	private CountDownTimer timer;

	protected BulletLight(Actor parent) {
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
	
	protected void setColorRed(){
		setSprite(AssetsManager.getManager().getRedLaserLightSprite());
	}
	
	protected void setColorGreen(){
		setSprite(AssetsManager.getManager().getGreenLaserLightSprite());
	}



	protected void setPositionCenter() {
		float centerXBulletLight = (getSprite().getX() + getSprite().getWidth() / 2);
		float centerX = parent.getX() + (parent.getWidth() / 2)
				- centerXBulletLight;

		this.setX(centerX);
		this.setY(parent.getSprite().getBounds().get(0).y
				+ parent.getSprite().getBounds().get(0).height);
	}

	protected void setPositionRight() {
		float centerXBulletLight = (getSprite().getX() + getSprite().getWidth() / 2);
		float centerX = parent.getSprite().getBounds().get(1).getX()
				+ parent.getSprite().getBounds().get(1).getWidth()
				- centerXBulletLight;

		this.setX(centerX);
		this.setY(parent.getSprite().getBounds().get(1).y
				+ parent.getSprite().getBounds().get(1).height);
	}

	protected void setPositionLeft() {
		float centerXBulletLight = (getSprite().getX() + getSprite().getWidth() / 2);
		float centerX = parent.getSprite().getBounds().get(1).getX()
				- centerXBulletLight;

		this.setX(centerX);
		this.setY(parent.getSprite().getBounds().get(1).y
				+ parent.getSprite().getBounds().get(1).height);
	}

}
