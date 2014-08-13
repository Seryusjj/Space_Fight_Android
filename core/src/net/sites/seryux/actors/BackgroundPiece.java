package net.sites.seryux.actors;

import com.badlogic.gdx.Gdx;
import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.AssetsManager;

class BackgroundPiece extends Actor {
	

	private int  speed;
	


	public BackgroundPiece() {
		super(AssetsManager.getManager().getBackgroundLvl1Sprite());
		setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		speed =250;
	}
	
	public void updateInitToDown(){
		setPosition(getX(), getY()-speed*Gdx.graphics.getDeltaTime());
		if(getY()<=-Gdx.graphics.getHeight()){
			setY(0);
		}
	}
	
	public void updateTopToInit(){
		setPosition(getX(), getY()-speed*Gdx.graphics.getDeltaTime());
		if(getY()<=0){
			setY(Gdx.graphics.getHeight());
		}
	}
	




}
