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
		entities = new ArrayList<Dmk_Entity>();
		entities.add(new Dmk_Bullet(world, x, y, batch, entities));
		entities.add(new Dmk_Player(world, 50, 50, batch, entities));

	}

	@Override
	public void render() {

		for (Dmk_Entity e : entities) {
			e.update();
		}
		world.step(1f / 60f, 6, 2);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Dmk_Entity e : entities) {
			e.render();
		}
		batch.end();

		// Contact Listener: Determines what happens for all collisions

		world.setContactListener(new ContactListener() {
			@Override
			// Huh, I didn't know about this syntax. It's kinda weird.
			public void beginContact(Contact contact) {
				if ((contact.getFixtureA().getFilterData().categoryBits == 0x2)
						&& (contact.getFixtureB().getFilterData().categoryBits == 0x8)) {
					((Dmk_Player) contact.getFixtureB().getUserData()).die();
					System.out.println("bullet collided with player");
				}
				else if ((contact.getFixtureB().getFilterData().categoryBits == 0x2)
						&& (contact.getFixtureA().getFilterData().categoryBits == 0x8)) {
					((Dmk_Player) contact.getFixtureA().getUserData()).die();
					System.out.println("player collided with bullet");
				}
				else{
					System.out.println("Some other collision occurred");
				}
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
