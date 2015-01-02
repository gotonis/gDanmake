package com.Danmakev1;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Dmk_Entity {

	Dmk_Entity(World w, float x, float y) {
		posX = x;
		posY = y;

		// Body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		body = w.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		Shape shape = shapify();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f; // default density
		fixture = body.createFixture(fixtureDef);
		shape.dispose();

	}

	Sprite[] sprites;
	Body body;
	float posX;
	float posY;
	Fixture fixture;
	float vx;
	float vy;
	Shape shape;

	public abstract Sprite getSprite();

	public void update() {
		posX = body.getPosition().x;
		posY = body.getPosition().y;
		body.setLinearVelocity(vx, vy);
	}

	// Gives the object's shape in the constructor
	public abstract Shape shapify();

}
