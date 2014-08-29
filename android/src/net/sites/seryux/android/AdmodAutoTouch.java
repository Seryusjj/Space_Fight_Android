package net.sites.seryux.android;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.MotionEvent;

import com.google.android.gms.ads.AdView;

import net.sites.seryux.utils.Toucher;

public class AdmodAutoTouch implements Toucher {

	private AdView adView;
	private Instrumentation instruement;
	
	public AdmodAutoTouch(AdView adView) {
		this.adView = adView;
		instruement = new Instrumentation();
	}

	@Override
	public void execute() {
		float x = adView.getX() + adView.getWidth() / 2;
		float y = adView.getY() + adView.getHeight() / 2;
		instruement.sendPointerSync(MotionEvent.obtain(
				SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
				MotionEvent.ACTION_DOWN, x, y, 0));
		instruement.sendPointerSync(MotionEvent.obtain(
				SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
				MotionEvent.ACTION_UP, x, y, 0));// TODO Auto-generated method
													// stub

	}

}
