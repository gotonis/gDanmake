package com.Danmakev1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class Dmk_Bullet extends Dmk_Entity {

	static short category = 0x2;
	static short mask = 0x18; // bomb || player

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
	}

	// From data
	Dmk_Bullet(float x, float y, Dmk_Session session, Sprite spr, Shape s) {
		
		super(x, y, category, mask, session, s);
		sprites = new ArrayList<Sprite>();
		sprites.add(spr);
		vx = 0;
		vy = 0;
	}

	// For use in reading bullets from file
	int startx;
	int starty;
	int rx;
	int ry;

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


}
