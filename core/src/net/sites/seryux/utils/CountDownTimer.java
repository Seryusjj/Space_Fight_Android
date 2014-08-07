package net.sites.seryux.utils;

public class CountDownTimer {
	private float timeRatio, currentTime;

	public CountDownTimer(float maxTime) {
		timeRatio = maxTime;
		currentTime = 0;
	}

	/**
	 * return true if the count down has finish Subtracting the delta of the
	 * game, you can pass as delta: Gdx.graphics.getDeltaTime()
	 * 
	 * @param delta
	 * @return true if finished the count down
	 */
	public boolean start(float delta) {
		if (timeRatio > currentTime) {
			currentTime += delta;
		} else {

			currentTime = 0;
			return true;
		}
		return false;
	}
}
