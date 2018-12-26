package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;


/*
Class used to account for current player. Sets player stats.
*/
public class Player extends Entity {
    private enum state {downIdle, leftIdle, upIdle, rightIdle, down, up, left, right}

    private Texture walkTextureSheet;
    private SpriteBatch batch;
    private TextureRegion[][] walkRegion;
    private Animation<TextureRegion> animationDownWalk, animationLeftWalk, animationRightWalk, animationUpWalk;
    private Texture fullHeart, halfHeart, emptyHeart;
    private int maxHealth, currentHealth, taggedTurn;
    private float elapsedTime, delay = .2f, intervalSec = .2f;
    private Timer timer = new Timer();
    private boolean isPressed = false;
    private boolean isTagged = false;
    private float x, y;
    private boolean turnTaken = false;
    private String fileDir = "spriteSheets/";
    private Task task;
    private int currentState = 0;
    OrthographicCamera camera;



    public Player(int maxHealth, OrthographicCamera camera, float x, float y) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        downWalk();
        leftWalk();
        rightWalk();
        upWalk();
        this.camera = camera;
        this.x = x;
        this.y = y;
    }

    /*
	This class is used to move the current player according to what key they press.
	*/

    public void move(int keycode) {

        turnTaken = false;
        if (task != null)
            if (!task.isScheduled()) {
                if (taggedTurn > 0)
                    taggedTurn--;

                else
                    isTagged = false;
            }
        if ((keycode == Input.Keys.W || keycode == Input.Keys.UP) && !isPressed) {
            isPressed = true;
            currentState = state.up.ordinal();
            timer.scheduleTask(task = new Task() {
                @Override
                public void run() {
                    //setY(getY() + 4);
                    camera.translate(0,4);
                    if (!task.isScheduled()) {
                        currentState = state.upIdle.ordinal();
                        isPressed = false;
                        turnTaken = true;
                        if (!isTagged && currentHealth < maxHealth && task != null && !task.isScheduled())
                            currentHealth++;
                    }
                }
            }, delay, intervalSec, 3);

        } else if ((keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) && !isPressed) {
            isPressed = true;
            currentState = state.right.ordinal();
            timer.scheduleTask(task = new Task() {
                @Override
                public void run() {
                    //setX(getX() + 4);
                    camera.translate(4,0);
                    if (!task.isScheduled()) {
                        currentState = state.rightIdle.ordinal();
                        isPressed = false;
                        turnTaken = true;
                        if (!isTagged&& currentHealth < maxHealth && task != null && !task.isScheduled())
                            currentHealth++;
                    }
                }
            }, delay, intervalSec, 3);

        } else if ((keycode == Input.Keys.S || keycode == Input.Keys.DOWN) && !isPressed) {
            isPressed = true;
            currentState = state.down.ordinal();
            timer.scheduleTask(task = new Task() {
                @Override
                public void run() {
                    //setY(getY() - 4);
                    camera.translate(0,-4);
                    if (!task.isScheduled()) {
                        currentState = state.downIdle.ordinal();
                        isPressed = false;
                        turnTaken = true;
                        if (!isTagged && currentHealth < maxHealth && task != null && !task.isScheduled())
                            currentHealth++;
                    }
                }
            }, delay, intervalSec, 3);
        } else if ((keycode == Input.Keys.A || keycode == Input.Keys.LEFT) && !isPressed) {
            isPressed = true;
            currentState = state.left.ordinal();
            timer.scheduleTask(task = new Task() {
                @Override
                public void run() {
                    //setX(getX() - 4);
                    camera.translate(-4,0);
                    if (!task.isScheduled()) {
                        currentState = state.leftIdle.ordinal();
                        isPressed = false;
                        turnTaken = true;
                        if (isTagged && currentHealth < maxHealth && task != null && !task.isScheduled())
                            currentHealth++;
                    }
                }
            }, delay, intervalSec, 3);
        } else if (task != null) {
            if (keycode == Input.Keys.E) {
                isTagged = true;
                taggedTurn = 10;
            }
        }



    }

    // down walk animation
    private void downWalk() {
        batch = new SpriteBatch();
        walkTextureSheet = new Texture(fileDir + "downWalk.png");

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
        walkTextureSheet = new Texture(fileDir + "leftWalk.png");

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
        walkTextureSheet = new Texture(fileDir + "rightWalk.png");

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
        walkTextureSheet = new Texture(fileDir + "upWalk.png");

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
        Texture downIdle = new Texture(fileDir + "downIdle.png");
        Texture leftIdle = new Texture(fileDir + "leftIdle.png");
        Texture rightIdle = new Texture(fileDir + "rightIdle.png");
        Texture upIdle = new Texture(fileDir + "upIdle.png");

        // default is for starting is the downIdle
        Sprite s = new Sprite(downIdle);
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.begin();
        // draws the animation for movement
        if (currentState == state.down.ordinal())
            batch.draw(animationDownWalk.getKeyFrame(elapsedTime, true), x/2, y/2);
        else if (currentState == state.left.ordinal())
            batch.draw(animationLeftWalk.getKeyFrame(elapsedTime, true), x/2, y/2);
        else if (currentState == state.right.ordinal())
            batch.draw(animationRightWalk.getKeyFrame(elapsedTime, true), x/2, y/2);
        else if (currentState == state.up.ordinal())
            batch.draw(animationUpWalk.getKeyFrame(elapsedTime, true), x/2, y/2);
        // draws the idle states
        else if (currentState == state.upIdle.ordinal())
            batch.draw(upIdle, x/2, y/2);
        else if (currentState == state.leftIdle.ordinal())
            batch.draw(leftIdle, x/2, y/2);
        else if (currentState == state.rightIdle.ordinal())
            batch.draw(rightIdle, x/2, y/2);
        else
            batch.draw(downIdle, x/2, y/2);
        batch.end();
        DisplayHealth();
    }


    // displays the heart
    private void DisplayHealth() {
        emptyHeart = new Texture(fileDir + "emptyHeart.png");

        fullHeart = new Texture(fileDir + "fullHeart.png");
        batch.begin();
        for (int i = 0; i < maxHealth; i++)
            batch.draw(emptyHeart, i * 16, 16);
        for (int i = 0; i < this.currentHealth; i++)
            batch.draw(fullHeart, i * 16, 16);
        batch.end();
    }

    /*
    This class is used to attack an enemy that is within range.
    */
    public void AttackEnemy(Enemy enemy) {

    }

    public void dispose() {

        // disposes of textures
        emptyHeart.dispose();
        //halfHeart.dispose();
        fullHeart.dispose();
        walkTextureSheet.dispose();

    }
}
