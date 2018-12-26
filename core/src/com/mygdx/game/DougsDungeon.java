package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
This was used as our base class where the gameBoard and controller was created. 
*/
public class DougsDungeon extends ApplicationAdapter {
	GameController controller;
	GameBoard gameBoard;
	
	/*
	This was used as our base class where the gameBoard and controller was created. 
	*/
	public DougsDungeon() {
		this.controller = new GameController(this);
		this.gameBoard = new GameBoard(); 
	}
	
	/*
	This function created the gameBoard. 
	*/
	@Override
	public void create () {
		gameBoard.create();
	}

	/*
	This function rendered the gameBoard. 
	*/
	@Override
	public void render () {
		gameBoard.render();
	}
	
	/*
	This function got rid of (disposed) the gameBoard. 
	*/
	@Override
	public void dispose () {
		//gameBoard.dispose();
	}
    
	/*
	
	*/
    public void onUpdate() {
//    	controller
    	
    }
	
}
