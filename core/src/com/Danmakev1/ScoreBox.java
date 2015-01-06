package com.Danmakev1;

public class ScoreBox {
	//Standard numbers of lives and bombs per life. Subclasses can change this.
	
	ScoreBox(Dmk_Player p){
		player = p;
		lives = DEF_LIVES;
		bombs = DEF_BOMBS;
		power = 0;
	}
	
	static int DEF_LIVES = 2; 
	static int DEF_BOMBS = 3;
	
	//Important stuff
	protected int lives;
	protected int bombs;
	protected int power;
	protected Dmk_Player player;
	
	/*Score related numbers  //I'll get to these later
	int score;
	int graze;
	int pItems;
	*/
	
	public int getLives() {
		return lives;
	}
	public int getBombs() {
		return bombs;
	}
	public int getPower() {
		return power;
	}
	
	public boolean hasLives(){
		return lives > 0;
	}
	
	public void die(){
		if(hasLives()){
			lives--;
			bombs = DEF_BOMBS;
		}
		/*else {
			Throw an exception, I guess?
		} */
	}
	
	public void useBomb(){
		if(bombs > 0){
			bombs--;
		}
		/*
		 else {
		 throw exception, probably
		 }
		 */
	}
	
	public float getAngleToPlayer(float x, float y){
		float dx = player.posX - x;
		float dy = player.posY - y;
		return (float)Math.atan(dy/dx);
	}
	
}
