package com.Danmakev1;

import com.badlogic.gdx.physics.box2d.Shape;

public class Dmk_Enemy extends Dmk_Entity {
	static short category = 0x4;
	static short mask = 0x19; //bomb || player || pbullet
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
	
	//TODO set up bullet spawning; This will have to wait until I've got bullet data storage
	

}
