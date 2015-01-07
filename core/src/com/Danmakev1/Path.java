package com.Danmakev1;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Path {
	Path(ConcurrentLinkedQueue<Ptype> pt, ConcurrentLinkedQueue<Float[]> nums,
			ConcurrentLinkedQueue<Integer> w) {
		types = pt;
		deltas = nums;
		waits = w;
	}
	
	//initializes a null path
	Path(){
		types = new ConcurrentLinkedQueue<Ptype>();
		deltas = new ConcurrentLinkedQueue<Float[]>();
		waits = new ConcurrentLinkedQueue<Integer>();
	}
	
	ConcurrentLinkedQueue<Ptype> types;
	ConcurrentLinkedQueue<Float[]> deltas;
	ConcurrentLinkedQueue<Integer> waits;

	public void follow(Dmk_Bullet b) {
		if(types.peek() == null){
			return;
		}
		if (types.peek() == Ptype.WAIT) {
			try {
				b.waitF(waits.peek(), Dmk_Bullet.pathNum);
			} catch (WaitException e) {
				return;
			}
			types.poll();
		} else if (types.peek() == Ptype.LINVEL) {
			types.remove();
			b.setV(deltas.poll());
		} else if (types.peek() == Ptype.LINACC) {
			types.remove();
			b.setA(deltas.poll());
		} else {
			types.poll();
			return;
		}
	}

	public void pop() {
		types.remove();
		waits.remove();
	}

	public void concatenate(Path p, int w){
		if(w > 0){
			types.add(Ptype.WAIT);
			waits.add(w);
		}
		types.addAll(p.types);
		deltas.addAll(p.deltas);
		waits.addAll(p.waits);
	}
}
