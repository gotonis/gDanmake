package com.Danmakev1;

import com.badlogic.gdx.physics.box2d.*;

//Danmakufu/Danmake Script
//Not sure whether to make this a class or an interface

public interface DScript {

	public void init(World w);
	public void update(World w);
	public void draw(World w);
	
}