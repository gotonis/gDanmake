package com.Danmakev1;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import java.util.Vector;

public class Dmk_Player extends Dmk_Entity {
	static short category = 0x8;
	static short mask = 0x26;  //item || enemy || bullet
	static float radius = 3; //Hitbox radius. Subclasses can change it.
	static float initX = 100;
	static float initY = 100;
	static int fireRate = 10;
	static int fTimerID = 0;
	static int nClocks = 64;
	Dmk_Player(Dmk_Session session) {
		super(initX, initY, category, mask, session);
		sprites = new ArrayList<Sprite>();
		Sprite s1,s2;
		img = new Texture("Sprites/PCs.png");
		s1 = new Sprite(img, 1, 3, 30, 43);
		s2 = new Sprite(img, 260, 2, 28, 43);
		sprites.add(s1);
		sprites.add(s2);
		dead = false;
		fixture.setSensor(false);
		clocks = new Vector<Float>(64);
		deaths = 0;
		
		//TODO: set up graze hitbox (low priority)
		
	}

	Texture img;
	Boolean dead;
	int deaths;
	
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
		
		if(Gdx.input.isKeyPressed(Input.Keys.Z)){
			fire();
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
	
	@Override
	public float getAngleToPlayer(){
		return 0;
	}
	
	public void fire(){
		try{
			waitF(fireRate, fTimerID);
			}
		catch(WaitException e) {
			return;
		}
		//Just for debug
		//dead = !dead;
		
	}
	
}
