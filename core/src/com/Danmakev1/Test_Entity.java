package com.Danmakev1;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Test_Entity extends Dmk_Entity {
	
	Test_Entity(Sprite s, World w, float x, float y, SpriteBatch b, Collection<Dmk_Entity> ents) {
		super(w, x, y,b,ents);
		sprites = new ArrayList<Sprite>();
		sprites.add(s);

		vx = 10;
		vy = 0;
	}

	@Override
	public void render(){
		sprites.get(0).setPosition(posX-128, posY-128);
		batch.draw(sprites.get(0), posX-128, posY-128);
	}


	@Override
	public Shape shapify() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(128,128);
		return shape;
	}
}
