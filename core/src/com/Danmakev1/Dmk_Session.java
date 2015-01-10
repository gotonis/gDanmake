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
	ArrayList<Dmk_Enemy> enemies;
	ArrayList<Dmk_Bullet> bullets;
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
		// Note: 2hu is generally 640x480. The game itself is in the window
		// {(32,16),(415,463)}
		// Standard game window has a 6x7

		bullets = new ArrayList<Dmk_Bullet>();
		//bullets.add(new Dmk_Bullet(x, y, this));
		player = new Dmk_Player(this);

		BulletSpawner spawner = new BulletSpawner("bulletData.dat", this);
		for (int i = 1; i <= 8; i++) {
			bullets.add(spawner.spawnBullet(40 * i, 40, "Large", i));
		}
		Path p = new Path();
		p.types.add(Ptype.WAIT);
		p.waits.add(60);
		p.types.add(Ptype.LINVEL);
		Float f[] = new Float[2];
		f[0] = (float) 5;
		f[1] = (float) 10;
		p.deltas.add(f);
		p.types.add(Ptype.WAIT);
		p.waits.add(120);
		p.types.add(Ptype.LINACC);
		Float f2[] = new Float[4];
		f2[0] = (float) 50;
		f2[1] = (float) 0;
		f2[2] = (float) -20;
		f2[3] = (float) 10;
		p.deltas.add(f2);
		((Dmk_Bullet) bullets.get(0)).pcat(p);

	}

	@Override
	public void render() {

		for (Dmk_Entity b : bullets) {
			b.update();
		}
		player.update();
		world.step(1f / 60f, 6, 2);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Dmk_Entity b : bullets) {
			b.render();
		}
		player.render();
		batch.end();

		// Contact Listener: Determines what happens for all collisions

		world.setContactListener(new ContactListener() {
			@Override
			// Huh, I didn't know about this syntax. It's kinda weird.
			public void beginContact(Contact contact) {
				((Dmk_Entity) contact.getFixtureA().getUserData())
						.collideWith((Dmk_Entity) contact.getFixtureB()
								.getUserData());
				((Dmk_Entity) contact.getFixtureB().getUserData())
						.collideWith((Dmk_Entity) contact.getFixtureA()
								.getUserData());
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
	
	public Dmk_Enemy getClosestEnemy(){
		double dist = 9999999;
		Dmk_Enemy temp = null;
		double calc;
		for(Dmk_Enemy e: enemies){
			calc = Math.sqrt((player.posX - e.posX)*(player.posX - e.posX) + (player.posY - e.posY)*(player.posY - e.posY));
			if(calc < dist){
				dist = calc;
				temp = e;
			}
		}
		return temp;
	}
}
