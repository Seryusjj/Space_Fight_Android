package net.sites.seryux.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Actor extends com.badlogic.gdx.scenes.scene2d.Actor {
	private Sprite sprite;
	private ShapeRenderer shapeRender;
	private boolean debug;

	public Actor(Sprite sprite) {
		this.sprite = sprite;
		this.shapeRender = new ShapeRenderer();
		debug = false;
	}

	public void act(float delta) {
		updateBounds();
		if (isDebug()) {
			for (Rectangle rect : sprite.getBounds()) {
				shapeRender.begin(ShapeType.Line);
				shapeRender.rect(rect.x, rect.y, rect.getWidth(),
						rect.getHeight());
				shapeRender.end();
			}
		}

		super.act(delta);

	}

	public void draw(Batch batch, float parentAlpha) {
		Color col = getColor();

		batch.setColor(col.r, col.g, col.b, col.a * parentAlpha);
		if (isDebug()) {
			this.shapeRender.setProjectionMatrix(batch.getProjectionMatrix());
			this.shapeRender.setTransformMatrix(batch.getTransformMatrix());
		}
		batch.draw(getSprite(), getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(),
				getRotation());

	}

	protected void updateBounds() {
		for (Rectangle rect : sprite.getBounds()) {
			rect.set(getX(), getY(), getWidth(), getHeight());
		}
	}

	protected float getScaledValueX(float value) {
		return getScaleX() * value;
	}

	protected float getScaledValueY(float value) {
		return getScaleY() * value;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public boolean isDebug() {
		return debug;
	}

	/**
	 * Turn it into true in order to draw a rectangle marking the boundingbox of
	 * the sprite in the color you specifie
	 * 
	 * @param debug
	 * @param col
	 */
	public void setDebug(boolean debug, Color col) {
		this.debug = debug;

		shapeRender.setColor(col.r, col.g, col.b, col.a);

	}

}
