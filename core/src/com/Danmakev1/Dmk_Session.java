package com.Danmakev1;

import java.util.ArrayList; 

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

public class Dmk_Session extends ApplicationAdapter {
	SpriteBatch batch;
	Sprite sprite;
	Texture img;
	World world;
	DScript script;
	ArrayList<Dmk_Entity> entities;
	Dmk_Player player;
	ScoreBox score;
	
	@Override
	public void create() {
		// Initialize the sprite for the sole entity
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		sprite = new Sprite(img);
		// Initialize the World
		world = new World(new Vector2(0, 0), true); // no gravity
		//Note: 2hu is generally 640x480. The game itself is in the window {(32,16),(415,463)}
		//Standard game window has a 6x7 
		float x;
		float y;
		
		x = Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2;
		y = Gdx.graphics.getHeight() / 2;
		entities = new ArrayList<Dmk_Entity>();
		entities.add(new Dmk_Bullet(x, y, this));
		player = new Dmk_Player(this);
		
	}

	@Override
	public void render() {

		for (Dmk_Entity e : entities) {
			e.update();
		}
		player.update();
		world.step(1f / 60f, 6, 2);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Dmk_Entity e : entities) {
			e.render();
		}
		player.render();
		batch.end();

		// Contact Listener: Determines what happens for all collisions

		world.setContactListener(new ContactListener() {
			@Override
			// Huh, I didn't know about this syntax. It's kinda weird.
			public void beginContact(Contact contact) {
				((Dmk_Entity) contact.getFixtureA().getUserData()).collideWith((Dmk_Entity) contact.getFixtureB().getUserData());
				((Dmk_Entity) contact.getFixtureB().getUserData()).collideWith((Dmk_Entity) contact.getFixtureA().getUserData());
			}

			@Override
			public void endContact(Contact contact) {
			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
			}

		});

	}
}
