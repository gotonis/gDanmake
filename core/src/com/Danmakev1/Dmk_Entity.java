package com.Danmakev1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;

public abstract class Dmk_Entity {
	static short category = 0x1;
	static short mask = -1;
	Dmk_Entity(float x, float y, short category, short mask, Dmk_Session session) {
		posX = x;
		posY = y;

		// Body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		body = session.world.createBody(bodyDef);
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
		batch = session.batch;       //Allows drawing to be done entity-side
		enemies = session.enemies; //Allows creation of new entities
		score = session.score;
		vx = 0;
		vy = 0;
		clocks = new Vector<Float>();
		this.session = session;
	}
	
	//For loading from data
	Dmk_Entity(float x, float y, short category, short mask, Dmk_Session session, Shape shape){
		posX = x;
		posY = y;

		// Body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		body = session.world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f; // default density
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.maskBits = mask;
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData(this);
		fixture.setSensor(true);
		shape.dispose();
		batch = session.batch;       //Allows drawing to be done entity-side
		enemies = session.enemies; //Allows creation of new enemies
		bullets = session.bullets;
		score = session.score;
		vx = 0;
		vy = 0;
		clocks = new Vector<Float>();
		this.session = session;
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
	Collection<Dmk_Enemy> enemies;
	Collection<Dmk_Bullet> bullets;
	ScoreBox score;
	Vector<Float> clocks;
	Dmk_Session session;

	public abstract void render();

	public void update() {
		posX = body.getPosition().x;
		posY = body.getPosition().y;
		body.setLinearVelocity(vx, vy);
	}

	// Gives the object's shape in the constructor
	public abstract Shape shapify();
	
	//Set of functions to handle collisions
	public abstract void collideWith(Dmk_Entity e);
	
	public float getAngleToPlayer(){
		return score.getAngleToPlayer(posX, posY);
	}
	
	//Wait a certain number of frames before doing whatever
	//Put this in something that repeats every frame and be careful to catch the exception
	public void waitF(int nFrames, int waitID) throws WaitException{
		if(clocks.size() <= waitID){
			clocks.ensureCapacity(waitID);
			clocks.add(waitID, (float) nFrames);
			throw new WaitException();
		}
		else if(clocks.get(waitID) == null){
			clocks.set(waitID, (float)nFrames);
			throw new WaitException();
		}
		else if(clocks.get(waitID) > 0){
			clocks.set(waitID, clocks.get(waitID) - 1);
			throw new WaitException();
		}
		else {
			clocks.set(waitID, null);
		}
	}
	
	//Same as waitF, but measures time in seconds rather than frames
	public void waitR(float time, int waitID) throws WaitException{
	if(clocks.size() <= waitID || clocks.get(waitID) == null){
		clocks.set(waitID, time);
		throw new WaitException();
	}
	else if(clocks.get(waitID) > 0){
		clocks.set(waitID, clocks.get(waitID) - Gdx.graphics.getDeltaTime());
		throw new WaitException();
	}
	else {
		clocks.set(waitID, null);
	}
}
	
	
}
