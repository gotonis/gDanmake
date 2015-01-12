package com.Danmakev1;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

public  class Dmk_Enemy extends Dmk_Entity {
	static short category = 0x4;
	static short mask = 0x19; //bomb || player || pbullet

	//TODO: Set up constructors
	
	//abstract public void differentiate(); //Loads info specific to the given subclass
	
	BulletSpawner spawner;
	
	Dmk_Enemy(float x, float y, Dmk_Session session){
		super(x,y,category,mask,session);
		sprites = new ArrayList<Sprite>();
		Texture img = new Texture("Sprites/boss_yukari.png");
		Sprite s = new Sprite(img, 40,33,51,61);
		sprites.add(s);
		
		spawner = session.spawner;
	}
	
	@Override
	public void render() {
		sprites.get(0).setPosition(posX, posY);
		batch.draw(sprites.get(0),posX-25, posY-32);

	}

	@Override
	public Shape shapify() {
		CircleShape shape = new CircleShape();
		shape.setRadius(10);
		return shape;
	}

	@Override
	public void collideWith(Dmk_Entity e) {
		// TODO Auto-generated method stub
		
	}

	
	public Dmk_Bullet shootRelative(float x, float y, String ID, int color) {
		return spawner.spawnBullet(x+posX, y+posY, ID, color);
		
	}
	
	@Override
	public void update(){
		fire();
		super.update();
	}

	
	//Stuff for BoWaP; move it into a script class or something later.
	double angle = 0;
	double angleAcc = 0;
	int ns = 3;
	float radius = 30;
	//int currentID = 2;
	
	protected void fire(){
		try {
		waitF(1,0);
		}
		catch (WaitException e){
			return;
		}
		finally {
			//int id;
			double s = Math.sin(angle);
			double c = Math.cos(angle);
			for(int j = 0; j < ns; j++){
				System.out.println("getting id");
				//currentID++;
				//id = currentID;
				Dmk_Bullet b = shootRelative(posX + (float)(radius*c), posY + (float)(radius*s), "Snow", 7);
				session.bullets.add(b);
				b.vx = 3*(float)c;
				b.vy = 3*(float)s;
				/*
				Path p = new Path();
				Float vel[] = new Float[2];
				vel[0] = 3*(float)c;
				vel[1] = 3*(float)s;
				p.types.add(Ptype.LINVEL);
				p.deltas.add(vel);
				b.path = p;
				*/
				angle += 2*Math.PI/ns;
			}
			angle += angleAcc;
			angleAcc += .15 * Math.PI / 180;
		}
		
	}

}
