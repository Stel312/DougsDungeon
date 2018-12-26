package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

/*
This class accepts input from the user and sends the information to other classes.
*/
public class Input extends InputAdapter implements InputProcessor {
    private OrthographicCamera camera;
    private Player player;

    //    GameBoard gameBoard = new GameBoard();
    public Input(OrthographicCamera camera, Player player) {
        this.camera = camera;
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
            player.move(keycode);

        return true;
    }

    /*
	Accepts input from the user to be used in the player class to move around the dungeon
	*/
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
