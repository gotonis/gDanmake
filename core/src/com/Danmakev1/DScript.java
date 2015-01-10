package com.Danmakev1;

import com.badlogic.gdx.math.Vector2;

//Danmakufu/Danmake Script
//Not sure whether to make this a class or an interface

public class DScript {
	
	public Dmk_Session session;
	
	public void setGravity(Vector2 v){
		session.world.setGravity(v);
	}
	
	public void spawnEnemyFromClass(String className, float x, float y) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class classTemp = Class.forName(className);
		Dmk_Enemy enemy = (Dmk_Enemy) classTemp.newInstance();
		enemy.posX = x;
		enemy.posY = y;
		
	}
}