package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;

/*
Class used for Enemies.
*/
public class Enemy extends Entity {
	private enum state {downIdle, leftIdle, upIdle, rightIdle, down, up, left, right}
	//Entity the enemy will be following
	private Entity targetEntity;
	private Texture walkTextureSheet;
	private SpriteBatch batch;
	private TextureRegion[][] walkRegion;
	private Animation<TextureRegion> animationDownWalk, animationLeftWalk, animationRightWalk, animationUpWalk;
	private int currentHealth;
	private float elapsedTime;
	private Timer timer = new Timer();
	private float delay = .2f, intervalSec = .2f;
	private boolean turnTaken = false;
	private SpriteBatch sprite;
	private String fileDir = "spriteSheets/";
	private Timer.Task task;
	private int currentState = 0;
	/*
	Enemy constructor to set current position and target entity
	*/
	public Enemy(int x, int y, Entity e1) {

		targetEntity = e1;
		batch = new SpriteBatch();
		upWalk();
		leftWalk();
		rightWalk();
		downWalk();
	}

	@Override
	public void move()
	{
		//distance between this entity and target entity in the x-direction
		int x = Math.abs((int)(getX())) - Math.abs((int)(targetEntity.getX()));
		//distance between this entity and target entity in the y-direction
		int y = Math.abs((int)(getY())) - Math.abs((int)(targetEntity.getY()));

		// If target entity is further away in the x-direction than the y-direction and the
		// target entity's x-position is less than this entity move in the negative x- direction.
		if( targetEntity.getX() < this.getX())
		{
			setX(getX() - 16);
			currentState = state.leftIdle.ordinal();
			//return;
		}
		// If target entity is further away in the x-direction than the y-direction and the
		// target entity's x-position is greater than this entity move in the positive x-direction.
		else if(targetEntity.getX() > this.getX())
		{
			setX(this.getX() + 16);
			currentState = state.rightIdle.ordinal();
			//return;
		}
		// If target entity is further away in the y-direction than the x-direction and the
		// target entity's y-position is greater than this entity move in the positive y-direction.
		else if(targetEntity.getY() > getY())
		{
			setY(getY() + 16);
			currentState = state.upIdle.ordinal();
			//return;
		}
		// If target entity is further away in the y-direction than the x-direction and the
		// target entity's y-position is less than this entity move in the negative y-direction.
		else if(x < y && targetEntity.getY() < getY())
		{
			setY(getY() - 16);
			currentState = state.downIdle.ordinal();
			//return;
		}
		render();
	}

	private void downWalk() {
		batch = new SpriteBatch();
		walkTextureSheet = new Texture(fileDir + "downMoveSlime.png");

		walkRegion = TextureRegion.split(walkTextureSheet, walkTextureSheet.getWidth() / 4, walkTextureSheet.getHeight());
		TextureRegion[] walkFrames = new TextureRegion[4];
		int index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 4; j++) {
				walkFrames[index++] = walkRegion[i][j];
			}
		}
		animationDownWalk = new Animation<TextureRegion>(.25f, walkFrames);
	}

	//left walk animation
	private void leftWalk() {
		batch = new SpriteBatch();
		walkTextureSheet = new Texture(fileDir + "leftMoveSlime.png");

		walkRegion = TextureRegion.split(walkTextureSheet, walkTextureSheet.getWidth() / 4, walkTextureSheet.getHeight());
		TextureRegion[] walkFrames = new TextureRegion[4];
		int index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 4; j++) {
				walkFrames[index++] = walkRegion[i][j];
			}
		}
		animationLeftWalk = new Animation<TextureRegion>(.25f, walkFrames);
	}

	//right walk animation
	private void rightWalk() {
		batch = new SpriteBatch();
		walkTextureSheet = new Texture(fileDir + "rightMoveSlime.png");

		walkRegion = TextureRegion.split(walkTextureSheet, walkTextureSheet.getWidth() / 4, walkTextureSheet.getHeight());
		TextureRegion[] walkFrames = new TextureRegion[4];
		int index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 4; j++) {
				walkFrames[index++] = walkRegion[i][j];
			}
		}
		animationRightWalk = new Animation<TextureRegion>(.25f, walkFrames);
	}

	//up walk animation
	private void upWalk() {
		batch = new SpriteBatch();
		walkTextureSheet = new Texture(fileDir + "upMoveSlime.png");

		walkRegion = TextureRegion.split(walkTextureSheet, walkTextureSheet.getWidth() / 4, walkTextureSheet.getHeight());
		TextureRegion[] walkFrames = new TextureRegion[4];
		int index = 0;
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 4; j++) {
				walkFrames[index++] = walkRegion[i][j];
			}
		}
		animationUpWalk = new Animation<TextureRegion>(.25f, walkFrames);
	}

	public void render() {
		// sets the idle texture
		Texture downIdle = new Texture(fileDir + "downIdleSlime.png");
		Texture leftIdle = new Texture(fileDir + "leftIdleSlime.png");
		Texture rightIdle = new Texture(fileDir + "rightIdleSlime.png");
		Texture upIdle = new Texture(fileDir + "upIdleSlime.png");

		// default is for starting is the downIdle
		Sprite s = new Sprite(downIdle);
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.begin();
		// draws the animation
		if (currentState == state.down.ordinal())
			batch.draw(animationDownWalk.getKeyFrame(elapsedTime, true), this.getX(), this.getY());
		else if (currentState == state.left.ordinal())
			batch.draw(animationLeftWalk.getKeyFrame(elapsedTime, true), this.getX(), this.getY());
		else if (currentState == state.right.ordinal())
			batch.draw(animationRightWalk.getKeyFrame(elapsedTime, true), this.getX(), this.getY());
		else if (currentState == state.up.ordinal())
			batch.draw(animationUpWalk.getKeyFrame(elapsedTime, true), this.getX(), this.getY());
			// draws the idle states
		else if (currentState == state.upIdle.ordinal())
			batch.draw(upIdle, this.getX(), this.getY());
		else if (currentState == state.leftIdle.ordinal())
			batch.draw(leftIdle, this.getX(), this.getY());
		else if (currentState == state.rightIdle.ordinal())
			batch.draw(rightIdle, this.getX(), this.getY());
		else
			batch.draw(downIdle, this.getX(), this.getY());
		batch.end();
	}
}
