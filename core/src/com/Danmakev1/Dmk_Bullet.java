package com.Danmakev1;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Dmk_Bullet extends Dmk_Entity {
	
	static short category = 0x2;
	static short mask = 0x18; //bomb || player
	
	Dmk_Bullet(World w, float x, float y, SpriteBatch b, Collection<Dmk_Entity> ents){
		super(w,x,y,b,ents, category, mask);
		sprites = new ArrayList<Sprite>();
		Sprite s;
		Texture img;
		img = new Texture("Sprites/shot_all.png");
		s = new Sprite(img, 2,52,26,26);
		sprites.add(s);
		vx = 2;
		vy = 10;
	}
	
	@Override
	public void render() {
		sprites.get(0).setPosition(posX, posY);
		batch.draw(sprites.get(0), posX, posY);
	}

	@Override
	public Shape shapify() {
		//For now
		CircleShape shape = new CircleShape();
		shape.setRadius(13);
		return shape;
	}

	@Override
	public void collideWith(Dmk_Entity e){
		if(e instanceof Dmk_Player){
			//System.out.println("Cast necessary");
			collideWith((Dmk_Player)e);
		}
	}
	
	public void collideWith(Dmk_Player p){
		//System.out.println("Hit a player");
	}
	
}
