package com.Danmakev1;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class Dmk_Bullet extends Dmk_Entity {

	static short category = 0x2;
	static short mask = 0x18; // bomb || player
	static int pathNum = 0;

	// raw
	Dmk_Bullet(float x, float y, Dmk_Session session) {
		super(x, y, category, mask, session);
		sprites = new ArrayList<Sprite>();
		Sprite s;
		Texture img;
		img = new Texture("Sprites/shot_all.png");
		s = new Sprite(img, 1, 51, 27, 27);
		sprites.add(s);
		vx = 2;
		vy = 10;
		ax = 0;
		ay = 0;
		path = new Path();
	}

	// From data
	Dmk_Bullet(float x, float y, Dmk_Session session, Sprite spr, Shape s) {

		super(x, y, category, mask, session, s);
		sprites = new ArrayList<Sprite>();
		sprites.add(spr);
		vx = 0;
		vy = 0;
		ax = 0;
		ay = 0;
		path = new Path();
	}

	// For use in reading bullets from file
	int startx;
	int starty;
	int rx;
	int ry;
	float ax;
	float ay;
	public Path path;

	@Override
	public void render() {
		sprites.get(0).setPosition(posX, posY);
		batch.draw(sprites.get(0), posX, posY);
	}

	@Override
	public Shape shapify() {
		// For now
		CircleShape shape = new CircleShape();
		shape.setRadius((float) 13.5);
		return shape;
	}

	@Override
	public void collideWith(Dmk_Entity e) {
		if (e instanceof Dmk_Player) {
			// System.out.println("Cast necessary");
			collideWith((Dmk_Player) e);
		}
	}

	public void collideWith(Dmk_Player p) {
		// System.out.println("Hit a player");
	}

	public void setV(Float vel[]) {
		if (vel.length < 2)
			return;
		vx = vel[0];
		vy = vel[1];
	}

	public void setA(Float vec[]) {
		if (vec.length < 4)
			return;
		vx = vec[0];
		vy = vec[1];
		ax = vec[2];
		ay = vec[3];
	}

	@Override
	public void update() {
		path.follow(this);
		//body.applyForceToCenter(new Vector2(10*ax,10*ay), true);
		vx += ax/60;
		vy += ay/60;
		super.update();
	}

	public void pcat(Path p) {
		path.concatenate(p, 0);
	}

}
