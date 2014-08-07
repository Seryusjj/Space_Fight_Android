package net.sites.seryux.utils;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Sprite extends com.badlogic.gdx.graphics.g2d.Sprite {

	private static final int ALPHA = 0;
	private Pixmap spriteMap;
	private ArrayList<Rectangle> bounds = new ArrayList<Rectangle>();

	public Sprite(String texturePath, int x, int y, int width, int height) {
		super(new Texture(texturePath), x, y, width, height);
		setSpriteMap(new Pixmap(Gdx.files.internal(texturePath)));
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

	/**
	 * Not working ....
	 * 
	 * @param other
	 * @return
	 */
	public boolean isCollidingPixelLevel(Sprite other) {
		try {
			int x = 0;
			int y = 0;
			if (getBoundingRectangle().overlaps(other.getBoundingRectangle())) {
				// alto = filas
				int limitI = (int) (getBoundingRectangle().getY() + getBoundingRectangle()
						.getHeight());

				// ancho = columnas
				int limitJ = (int) (getBoundingRectangle().getX() + getBoundingRectangle()
						.getWidth());

				for (int i = (int) getBoundingRectangle().getY(); i < limitI; i++) {
					for (int j = (int) getBoundingRectangle().getX(); j < limitJ; j++) {
						if (other.getBoundingRectangle().contains(j, i)) {

							x = j - (int) getBoundingRectangle().getX();// columnas
							y = i - (int) getBoundingRectangle().getY();// filas

							boolean isThisColored = spriteMap.getPixel(x
									+ getRegionX(), y + getRegionY()) != ALPHA;
							// System.out.print(x + " " + y + ", ");

							x = j - (int) other.getBoundingRectangle().getX();// columnas
							y = i - (int) other.getBoundingRectangle().getY();// filas
							// System.out.println(x + " " + y);
							boolean isOtherColored = other.getSpriteMap()
									.getPixel(x + other.getRegionX(),
											y + other.getRegionY()) != ALPHA;

							if (isThisColored && isOtherColored) {
								return true;
							}

						}
					}
					System.out.println("");
				}

			}

		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public Pixmap getSpriteMap() {
		return spriteMap;
	}

	private void setSpriteMap(Pixmap spriteMap) {
		this.spriteMap = spriteMap;
	}

}
