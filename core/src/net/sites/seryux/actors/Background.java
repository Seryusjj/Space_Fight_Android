package net.sites.seryux.actors;

import net.sites.seryux.utils.Sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;



public class Background {
	private BackgroundPiece background1, background2;
	private Stage escenario;
	
	public Background(Stage escenario) {
		this.escenario  =  escenario;
		background1 = new BackgroundPiece();
		background1.setPosition(0, Gdx.graphics.getHeight());
		background2 = new BackgroundPiece();
		background2.setPosition(0, 0);
		this.escenario.addActor(background1);
		this.escenario.addActor(background2);
	}

	public void moveBackground(){
		background2.updateInitToDown();
		background1.updateTopToInit();
	}
	
	public void setBackgroundTexture(Sprite sprite){
		background1.getSprite().set(sprite);
		background2.getSprite().set(sprite);
		
	}
}
