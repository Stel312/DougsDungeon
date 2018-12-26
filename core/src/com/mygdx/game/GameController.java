package com.mygdx.game;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/*

*/
public class GameController extends TimerTask  {

    DougsDungeon game;
    
	/*

	*/
    public GameController(DougsDungeon game) {
    	this.game = game;
    	startTimer();
	}
    
	/*

	*/
    private void startTimer() {
    	new Timer(true).scheduleAtFixedRate(this, 0, 100);
    }

	/*

	*/
	@Override
	public void run() {
		// TODO Auto-generated method stub
		game.onUpdate();
	}

}
