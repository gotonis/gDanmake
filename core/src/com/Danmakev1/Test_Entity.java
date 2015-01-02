package com.Danmakev1;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Test_Entity extends Dmk_Entity {

	Test_Entity(Sprite s, World w, float x, float y) {
		super(w, x, y);
		sprites = new Sprite[1];
		sprites[0] = s;

		vx = 10;
		vy = 0;
	}

	@Override
	public Sprite getSprite() {
		sprites[0].setPosition(posX, posY);
		return sprites[0];
	}


	@Override
	public Shape shapify() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(128, 128);
		return shape;
	}
}
