package com.Danmakev1;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class sAnim {
	protected Sprite[][] anims;
	Sprite prev;
	int prevState;
	int max[];
	int loop[];
	int iter;
	int nStates;
	
	sAnim(Sprite[][]a, int m[], int l[], int ns){
		anims = a;
		max = m;
		loop = l;
		nStates = ns;
		prev = anims[0][0];
		iter = -1;
	}
	
	
	public Sprite getNext(int dir){
		if(dir >= nStates){
			return prev;
		}
		try {
		if(dir == prevState){
			iter++;
			if(iter >= max[dir]){
				iter = loop[dir];
			}
			if(iter < 0)
				iter = 0;
			
		}
		return anims[dir][iter];
		} catch (ArrayIndexOutOfBoundsException e){
			return prev;
		}
	}
	
}
