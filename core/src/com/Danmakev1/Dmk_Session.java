package com.Danmakev1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Dmk_Session extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite sprite;
	Texture img;
	World world;
	DScript script;
	Dmk_Entity[] enemies;

	@Override
	public void create() {
		// Initialize the sprite for the sole entity
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		sprite = new Sprite(img);
		// Initialize the World
		world = new World(new Vector2(0, 0), true); // no gravity
		float x;
		float y;
		x = Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2;
		y = Gdx.graphics.getHeight() / 2;
		enemies = new Dmk_Entity[1];
		enemies[0] = new Test_Entity(sprite, world, x, y);

	}

	@Override
	public void render() {
		for (Dmk_Entity e : enemies) {
			e.update();
		}
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		System.out.println(1 / Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Dmk_Entity e : enemies) {
			batch.draw(e.getSprite(), e.posX, e.posY);
		}
		batch.end();
	}
}
