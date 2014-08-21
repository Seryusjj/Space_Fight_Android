package net.sites.seryux.actors.multiComponentActors;

import com.badlogic.gdx.Gdx;
import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.AssetsManagerLvl1;

class BackgroundPiece extends Actor {
	

	private int  speed;
	


	protected BackgroundPiece() {
		super(AssetsManagerLvl1.getManager().getBackgroundLvl1Sprite());
		setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		speed =250;
	}
	
	protected void updateInitToDown(){
		setPosition(getX(), getY()-speed*Gdx.graphics.getDeltaTime());
		if(getY()<=-Gdx.graphics.getHeight()){
			setY(0);
		}
	}
	
	protected void updateTopToInit(){
		setPosition(getX(), getY()-speed*Gdx.graphics.getDeltaTime());
		if(getY()<=0){
			setY(Gdx.graphics.getHeight());
		}
	}
	




}
