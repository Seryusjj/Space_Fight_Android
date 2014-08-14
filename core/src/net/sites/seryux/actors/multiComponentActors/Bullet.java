package net.sites.seryux.actors.multiComponentActors;

import com.badlogic.gdx.Gdx;

import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.AssetsManager;

  class Bullet extends Actor {

	private double speed;
	private Actor parent;
	


	protected Bullet(Actor parent) {
		super(AssetsManager.getManager().getRedLaserSprite());
		//setDebug(parent.isDebug(), new Color(1, 0, 0, 1));
		setSize(9, 32);
		setSpeed(300);
		this.parent = parent;
		setVisible(false);
		setPosition(Gdx.graphics.getWidth()*2,Gdx.graphics.getHeight()*2);
	}

	protected void setColorRed(){
		setSprite(AssetsManager.getManager().getRedLaserSprite());
	}
	
	protected void setColorGreen(){
		setSprite(AssetsManager.getManager().getGreenLaserSprite());
	}


	public void act(float delta) {
		if(isVisible()){
			setPosition(getX(),(float) (getY()+speed*delta));
			if(getY()>Gdx.graphics.getHeight()){
				setVisible(false);
			}
		}
		super.act(delta);
	}
	

	
	protected void setPositionCenter() {
		float centerXBulletLight = (getSprite().getX()+getSprite().getWidth()/2);
		float centerX = parent.getX() + (parent.getWidth() / 2) - centerXBulletLight;

		this.setX(centerX);
		this.setY(parent.getSprite().getBounds().get(0).y
				+ parent.getSprite().getBounds().get(0).height);
	}

	protected void setPositionRight() {
		float centerXBulletLight = (getSprite().getX()+getSprite().getWidth()/2);
		float centerX = parent.getSprite().getBounds().get(1).getX()
				+ parent.getSprite().getBounds().get(1).getWidth()-centerXBulletLight;

		this.setX(centerX);
		this.setY(parent.getSprite().getBounds().get(1).y
				+ parent.getSprite().getBounds().get(1).height);
	}

	protected void setPositionLeft() {
		float centerXBulletLight = (getSprite().getX()+getSprite().getWidth()/2);
		float centerX = parent.getSprite().getBounds().get(1).getX()-centerXBulletLight;

		this.setX(centerX);
		this.setY(parent.getSprite().getBounds().get(1).y
				+ parent.getSprite().getBounds().get(1).height);
	}

	protected double getSpeed() {
		return speed;
	}

	protected void setSpeed(double speed) {
		this.speed = speed;
	}

}
