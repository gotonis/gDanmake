package com.Danmakev1;

import com.badlogic.gdx.physics.box2d.Shape;
//I might switch this to being a subclass of bullet; right now I'm just setting up collision filters.
public class Dmk_PBullet extends Dmk_Entity {
	static short category = 0x1;
	static short mask = 0x4; //enemy
	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public Shape shapify() {
		// TODO Auto-generated method stub
		return null;
	}

}
