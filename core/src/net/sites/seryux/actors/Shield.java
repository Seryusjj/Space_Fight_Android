package net.sites.seryux.actors;

import net.sites.seryux.utils.Actor;
import net.sites.seryux.utils.Sprite;

public class Shield extends Actor {
	Ship parent;

	public Shield(Ship parent) {
		super(new Sprite("effects/shield.png", 0, 0, 128, 128));
		this.parent = parent;
		getSprite().setRegion(0, 0, 256, 128);
		setSize(256, 128);
	}

	public void act(float delta) {
		if (isVisible()) {
			setCenterPosition(parent.getCenterX(), parent.getCenterY());
			if(parent.canDead){
				setVisible(false);
			}
		}

		super.act(delta);
	}

}
