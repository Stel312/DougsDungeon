package com.mygdx.game;


/*
This class is used to get the position on the screen.
*/
public class Position {

	private int x;
	private int y;
	
	/*
	Constructor used to set current position. 
	*/
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/*
	Getter class used to get x coordinate.
	*/
	public int getX() {
		return x;
	}
	
	/*
	Setter class used to set x coordinate.
	*/
	public void setX(int x) {
		this.x = x;
	}
	
	/*
	Getter class used to get y coordinate.
	*/
	public int getY() {
		return y;
	}
	
	/*
	Setter class used to set y coordinate.
	*/
	public void setY(int y) {
		this.y = y;
	}
	
}
