package net.sites.seryux.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class AssetsManagerLvl1 {
	private AssetManager assets;
	private static AssetsManagerLvl1 manager = new AssetsManagerLvl1();

	private AssetsManagerLvl1() {
		assets = new AssetManager();
		initialice();

	}

	public boolean update() {
		return assets.update();
	}

	private void initialice() {
		assets.load("levels/SkyFirstLvl.png", Texture.class);
		assets.load("asteroid/AsteroidSheet.png", Texture.class);
		assets.load("effects/laserRed.png", Texture.class);
		assets.load("effects/laserGreen.png", Texture.class);
		assets.load("effects/laserRedShot.png", Texture.class);
		assets.load("effects/laserGreenShot.png", Texture.class);
		assets.load("effects/explosionSpriteSheet.png", Texture.class);
		assets.load("effects/shield.png", Texture.class);
		assets.load("spaceship/ship.png", Texture.class);
		assets.load("effects/laserUpgradeRed.png", Texture.class);
		assets.load("effects/laserUpgradeGreen.png", Texture.class);
		
	}
	
	
	
	
	
	
	

	public float getPercentage() {
		return assets.getProgress();
	}

	public static AssetsManagerLvl1 getManager() {
		return manager == null ? new AssetsManagerLvl1() : manager;
	}

	public Sprite getAsteroidSprite() {
		return new Sprite(assets.get("asteroid/AsteroidSheet.png",
				Texture.class), 0, 0, 128, 128);

	}

	/**
	 * special case because it has to be load at moment 0
	 * 
	 * @return
	 */
	public Sprite getBackgroundLvl1Sprite() {
		if (assets.isLoaded("levels/SkyFirstLvl.png")) {
			return new Sprite(assets.get("levels/SkyFirstLvl.png",
					Texture.class), 0, 0, 512, 1024);

		} else {
			return new Sprite(new Texture("levels/SkyFirstLvl.png"), 0, 0, 512,
					1024);
		}
	}

	public Sprite getRedLaserSprite() {

		return new Sprite(assets.get("effects/laserRed.png", Texture.class), 0,
				0, 9, 32);
	}

	public Sprite getGreenLaserSprite() {
		return new Sprite(assets.get("effects/laserGreen.png", Texture.class),
				0, 0, 9, 32);

	}

	public Sprite getRedLaserLightSprite() {
		return new Sprite(
				assets.get("effects/laserRedShot.png", Texture.class), 0, 0,
				66, 64);
	}

	public Sprite getGreenLaserLightSprite() {
		return new Sprite(assets.get("effects/laserGreenShot.png",
				Texture.class), 0, 0, 66, 64);
	}

	public Sprite getExplosionSprite() {
		return new Sprite(assets.get("effects/explosionSpriteSheet.png",
				Texture.class), 0, 0, 64, 64);
	}

	public Sprite getShieldSprite() {
		return new Sprite(assets.get("effects/shield.png", Texture.class), 0,
				0, 128, 128);

	}

	public Sprite getShipTexture() {
		return new Sprite(assets.get("spaceship/ship.png", Texture.class), 0,
				0, 128, 128);
	}
	
	public Sprite getLaserUpdateRed(){
		return new Sprite(assets.get("effects/laserUpgradeRed.png", Texture.class), 0,
				0, 128, 128);
	}
	
	public Sprite getLaserUpdateGreen(){
		return new Sprite(assets.get("effects/laserUpgradeGreen.png", Texture.class), 0,
				0, 128, 128);
	}

}
