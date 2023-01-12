package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import java.util.Random;

/**
 * This PowerUp makes the platform of the last Player who touched the ball grow in length
 * @see PowerUp
 */
public class BiggerPlatform extends PowerUp{
    Sprite sprite;
    protected float xPosition, yPosition;
    protected float width = 100;
    protected float height = 100;
    Random rand;
    protected CollisionBox box;
    private boolean active;
    @Override
    public void initialise() {
        active = false;
        box = new CollisionBox(10, 10, width, height);
        waitForPowerUp();
    }
    @Override
    public void update() {
        //check ob Ball berührt in Collision
        box.preUpdate();
    }
    @Override
    public void interpolate(float alpha) {

    }
    @Override
    public void render(Graphics g) {
        if (active) {
            g.drawSprite(sprite);
        }
    }
    public void waitForPowerUp(){
        rand = new Random();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                spawn();
            }
        }, 10 + rand.nextFloat() * (40 - 10));
    }
    public void spawn() {
        active = true; //sichtbar machen
        rand = new Random();
        xPosition = 70 + rand.nextFloat() * (1020 - 70);// xpos zwischen 70 und 1020
        yPosition = 20 + rand.nextFloat() * (500 - 20); // ypos zwischen 20 und 500

        sprite = new Sprite(new Texture(Gdx.files.internal("assets/Grow.png")));
        sprite.setPosition(xPosition, yPosition);
        box = new CollisionBox(xPosition, yPosition, width, height);
    }
    /**
     * applies the PowerUps unique Power then disappears again
     * in this case the platform grows
     * @see Platform
     */
    public void applyPowerUp() {
        //Ball wird schneller
        active = false;
        waitForPowerUp();
    }
    /**
     * @return Collisionbox from PowerUp Flash
     */
    public CollisionBox getCollisionBox() {
        return box;
    }
    /**
     * @return state of the PowerUp, true=active
     */
    public boolean getState(){
        return active;
    }
}
