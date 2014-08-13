package net.sites.seryux.utils;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Sprite extends com.badlogic.gdx.graphics.g2d.Sprite {



	private ArrayList<Rectangle> bounds = new ArrayList<Rectangle>();

	public Sprite(Texture texture, int x, int y, int width, int height) {
		super(texture, x, y, width, height);
		addBound();
	}

	public void addBound() {
		bounds.add(new Rectangle(getX(), getY(), getWidth(), getHeight()));
	}

	public ArrayList<Rectangle> getBounds() {
		return bounds;
	}

	public boolean isCollidingBoxLevel(Sprite other) {
		for (Rectangle rec : bounds) {
			for (Rectangle otherRec : other.bounds) {
				if (rec.overlaps(otherRec)) {
					return true;
				}
			}
		}
		return false;
	}

	

}
