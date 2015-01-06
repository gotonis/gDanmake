package com.Danmakev1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Shape;

public class BulletSpawner {

	BulletSpawner(String fn, Dmk_Session sesh) {
		data = fn;
		session = sesh;
	}

	// Data file
	String data;
	Dmk_Session session;

	// color: ROYGCBVW <-> 1-8
	Dmk_Bullet spawnBullet(int x, int y, String ID, int color) {
		
		
		FileReader r;
		try {
			r = new FileReader("D:/gDanmake/gDanmake/core/assets/bulletData.dat");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found");
			e.printStackTrace();
			return null;
		}
		BufferedReader reader = new BufferedReader(r);
		String inBuf = null;
		try {
			Shape shape;
			while (inBuf == null || !inBuf.equals(ID)) {
				inBuf = reader.readLine();
				System.out.println(inBuf);
			}
			
			inBuf = reader.readLine();
			System.out.println(inBuf);
			if (inBuf.equals("circle")) {
				inBuf = reader.readLine();
				System.out.println(inBuf);
				float rad = Float.parseFloat(inBuf);
				shape = new CircleShape();
				shape.setRadius(rad);
			} 
			else {
				shape = null;
				return null;
			}

			// Make sure to add conditions for other shapes
			inBuf = reader.readLine();
			System.out.println(inBuf);
			int rx = Integer.parseInt(inBuf);
			inBuf = reader.readLine();
			int ry = Integer.parseInt(inBuf);
			int startx = 0;
			int starty = 0;
			for (int i = 0; i < color; i++) {
				startx = Integer.parseInt(reader.readLine());
				starty = Integer.parseInt(reader.readLine());
			}
			reader.close();
			
			Texture img;
			img = new Texture("Sprites/shot_all.png");
			Sprite spr = new Sprite(img, startx, starty, rx, ry);
			System.out.println("gothere");
			return new Dmk_Bullet(x, y, session, spr, shape);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
