package com.Danmakev1;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Dmk_Entity {
	static short category = 0x1;
	static short mask = -1;
	Dmk_Entity(World w, float x, float y, SpriteBatch b, Collection<Dmk_Entity> ents, short category, short mask) {
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
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.maskBits = mask;
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData(this);
		fixture.setSensor(true);
		shape.dispose();
		batch = b;       //Allows drawing to be done entity-side
		entities = ents; //Allows creation of new entities
		vx = 0;
		vy = 0;
	}
	
	ArrayList<Sprite> sprites;
	Body body;
	float posX;
	float posY;
	Fixture fixture;
	float vx;
	float vy;
	Shape shape;
	SpriteBatch batch;
	Collection<Dmk_Entity> entities;

	public abstract void render();

	public void update() {
		posX = body.getPosition().x;
		posY = body.getPosition().y;
		body.setLinearVelocity(vx, vy);
	}

	// Gives the object's shape in the constructor
	public abstract Shape shapify();

}
