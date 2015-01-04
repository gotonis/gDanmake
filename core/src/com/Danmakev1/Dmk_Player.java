package com.Danmakev1;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Dmk_Player extends Dmk_Entity {
	static short category = 0x8;
	static short mask = 0x26;  //item || enemy || bullet
	static float radius = 3; //Hitbox radius. Subclasses can change it.
	Dmk_Player(World w, float x, float y, SpriteBatch b,
			Collection<Dmk_Entity> ents) {
		super(w, x, y, b, ents,category, mask);
		sprites = new ArrayList<Sprite>();
		Sprite s1,s2;
		img = new Texture("Sprites/PCs.png");
		s1 = new Sprite(img, 1, 3, 30, 43);
		s2 = new Sprite(img, 260, 2, 28, 43);
		sprites.add(s1);
		sprites.add(s2);
		dead = false;
		fixture.setSensor(false);
		
		//TODO: set up graze hitbox (low priority)
		
	}

	Texture img;
	Boolean dead;

	@Override
	public void render() {
		sprites.get(0).setPosition(posX, posY);
		if (dead) {
			batch.draw(sprites.get(1), posX, posY);
		} else {
			batch.draw(sprites.get(0), posX, posY);
		}
	}

	@Override
	public void update() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				vx = -72;
				vy = -72;
			} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				vx = -72;
				vy = 72;
			} else {
				vx = -200;
				vy = 0;
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				vx = 141;
				vy = -141;
			} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				vx = 141;
				vy = 141;
			} else {
				vx = 200;
				vy = 0;
			}
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			vx = 0;
			vy = -200;
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			vx = 0;
			vy = 200;
		} else {
			vx = 0;
			vy = 0;
		}
		super.update();
	}

	@Override
	public Shape shapify() {
		CircleShape shap = new CircleShape();
		shap.setRadius(radius);
		return shap;
	}

	public void die() {
		// Right now, just a debug for collisions
		dead = true;
	}

	@Override
	public void collideWith(Dmk_Entity e){
		if(e instanceof Dmk_Bullet){
			//System.out.println("casting was necessary");
			collideWith((Dmk_Bullet)e);
		}
	}
	
	public void collideWith(Dmk_Bullet b){
		die();
	}
	
}
