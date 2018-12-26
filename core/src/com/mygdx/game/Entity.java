package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.lang.ref.SoftReference;
//import com.sun.xml.internal.ws.dump.LoggingDumpTube;

/*
An abstract class used to set certain attributes to every Entity.
*/
public abstract class Entity extends Sprite {

	private int HP;

	private final int attackRange = 1;
	private final int[] moveRange = {-1, 1};
	
	/*
	Entity constructor to set current position.
	*/
	public Entity(int x, int y) {
		x = x / 16;
		x = x * 16;
		y = y / 16;
		y = y * 16;
		setX(x);
		setY(y);
	}

	public Entity(Sprite sprite)
	{
		super(sprite);
	}

	public Entity (){}
	/*
	Class used to randomly move in an x and y direction.
	*/
	public void move() {
		setX(getX() + (randomMove()*16));
		setY(getY() + (randomMove()*16));
	}
	
	/*
	Class to check whether the current Entity is alive.
	*/
	public boolean isAlive() {
		return HP > 0;
	}
	
	/*
	Class used to check whether another character is withing attacking range.
	*/
	public boolean inAttackRange(Entity e) {
		return Math.abs(e.getX() - this.getX()) <= attackRange ||
				Math.abs(e.getY() - this.getY()) <= attackRange;
	}

	/*
	Getter class to get the current position of the Entity.
	*/
	//public Position getPosition() {
	//	return po;
	//}
	
	/*
	Class used to get a random direction of movement.
	*/
	private int randomMove() {
		return moveRange[0] + (int)(Math.random() * ((moveRange[1] - moveRange[0]) + 1));
	}
}
