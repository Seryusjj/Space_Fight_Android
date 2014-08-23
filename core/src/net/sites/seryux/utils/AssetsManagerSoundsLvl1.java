package net.sites.seryux.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

public class AssetsManagerSoundsLvl1 {
	private AssetManager assets;
	private static AssetsManagerSoundsLvl1 manager = new AssetsManagerSoundsLvl1();

	private Music loop;
	private Music laser;
	private Music rockBreaking;

	private boolean mute;

	private AssetsManagerSoundsLvl1() {
		assets = new AssetManager();
		initialice();
		mute = false;

	}

	public boolean update() {
		return assets.update();
	}

	private void initialice() {
		assets.load("sounds/Brave Solders.mp3", Music.class);
		assets.load("sounds/laser7.wav", Music.class);
		assets.load("sounds/rock_breaking.wav", Music.class);

	}

	private Music getLaserMusic() {
		Music loop = assets.get("sounds/laser7.wav", Music.class);
		loop.setLooping(false);
		loop.setVolume(1);
		return loop;
	}

	private Music getRockBreakingSound() {
		Music loop = assets.get("sounds/rock_breaking.wav", Music.class);
		loop.setLooping(false);
		loop.setVolume(1);
		return loop;
	}

	private Music getGameLoopMusic() {
		Music loop = assets.get("sounds/Brave Solders.mp3", Music.class);
		loop.setLooping(true);
		loop.setVolume(1);
		return loop;
	}

	public void playRockBreakibngSound() {
		if (rockBreaking == null) {
			rockBreaking = getRockBreakingSound();
		}
		if (rockBreaking.isPlaying()) {
			rockBreaking.stop();
		}
		if (!mute) {
			rockBreaking.play();
		}

	}
	
	public void mute(){
		if(loop!=null && laser!=null && rockBreaking!=null){
			loop.stop();
			laser.stop();
			rockBreaking.stop();
		}
		mute = true;
	}
	
	public void loose(){
		mute = true;
	}
	
	public void resetGame(){
		mute = false;
	}
	
	public void unmute(){
		if(mute){
			mute = false;
			loop.play();
		}
	}

	public void playLaserSound() {
		if (laser == null) {
			laser = getLaserMusic();
		}
		if (laser.isPlaying()) {
			laser.stop();
		}
		if (!mute) {
			laser.play();
		}

	}

	public void playGameLoopLvl1Soun() {
		if (loop == null) {
			loop = getGameLoopMusic();
		}
		if (!mute) {
			loop.play();
		}

	}

	public float getPercentage() {
		return assets.getProgress();
	}

	public static AssetsManagerSoundsLvl1 getManager() {
		return manager == null ? new AssetsManagerSoundsLvl1() : manager;
	}

}
