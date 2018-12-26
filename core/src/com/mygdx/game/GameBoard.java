package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


import java.awt.font.NumericShaper;

/*
This class creates the space in which the game takes place, in addition to all its visible components
*/
public class GameBoard {
    private Player player;

    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;
    private Input input;
    private String filedir = "tiles/tile.tmx";
    private Enemy enemy;
    private Texture img;
    private Sprite sprite;
    private Texture floor = "";
    
    public enum TileType
    {
        Wall, Floor,
    }


    public int columns = 100;                                 // The number of columns on the board (how wide it will be).
    public int rows = 100;                                    // The number of rows on the board (how tall it will be).
    public IntRange numRooms = new IntRange (15, 20);         // The range of the number of rooms there can be.
    public IntRange roomWidth = new IntRange (3, 10);         // The range of widths rooms can have.
    public IntRange roomHeight = new IntRange (3, 10);        // The range of heights rooms can have.
    public IntRange corridorLength = new IntRange (6, 10);    // The range of lengths corridors between rooms can have.


    /*
    This method renders the scene
    */
    public void render() {
    	
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        player.render();
        //enemy.move();

    }

    /*
    This method initializes the variables of this class and sets our preferences
    */
    public void create() {

        // width of screen
        float w = Gdx.graphics.getWidth();
        // height of screen
        float h = Gdx.graphics.getHeight();
        // initalizes new player

        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);
        // initalize camera
        camera = new OrthographicCamera();
        // centers the width and and height
        camera.setToOrtho(false, w, h);
        // does what you think it does
        camera.zoom = .75f;
        player = new Player(10, camera, w, h);
        player.setScale(16f / 23f);
        camera.position.set(w/2, h/2, 0);
        enemy = new Enemy(64, 32, player);
        enemy.setScale(16f/28f);
        input = new Input(camera, player);
        Gdx.input.setInputProcessor(input);

    }

    /*
    Disposes of the Scene
    */
    public void dispose() {

        renderer.dispose();
        player.dispose();
    }

    /*

     */
    private void displayEntities() {

    }
}
