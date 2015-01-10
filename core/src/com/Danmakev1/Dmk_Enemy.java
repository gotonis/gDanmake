package com.Danmakev1;

import com.badlogic.gdx.physics.box2d.Shape;

public abstract class Dmk_Enemy extends Dmk_Entity {
	static short category = 0x4;
	static short mask = 0x19; //bomb || player || pbullet

	//TODO: Set up constructors
	
	abstract public void differentiate(); //Loads info specific to the given subclass
	
	BulletSpawner spawner;
	
	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public Shape shapify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void collideWith(Dmk_Entity e) {
		// TODO Auto-generated method stub
		
	}

	
	public void shootRelative(float x, float y, String ID, int color) {
		spawner.spawnBullet(x+posX, y+posY, ID, color);
		
	}


}
