package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.Random;
/**
 * This PowerUp accelerates the speed of the ball
 */
public class Flash extends PowerUp {

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
        box = new CollisionBox(10, 10, width, height); // wird geupdatet
        waitForPowerUp();// wird mit Anfang Spiel gestartet NICHT MIT GAMESCREEN !!! ÄNDERN
    }
    @Override
    public void update() {//muss random zwischen xLinkePlattform und x rechtePlattform erscheinen
        //check ob Ball berührt in Collision
        box.preUpdate();
    }

    @Override
    public void interpolate(float alpha) {//Animation eines Objekts auf dem Bildschirm

    }

    @Override
    public void render(Graphics g) {// Zeichnen des Objekts auf einem Bildschirm
        if (active) {
            g.drawSprite(sprite);
        }
    }
    /**
     * waits a certain time until the PowerUp appears
     */
    public void waitForPowerUp(){
        rand = new Random();
        int time = 3 + rand.nextInt(4);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                spawn();
            }
        }, 4); // 4-7 erstmal
    }
    /**
     * PowerUp appears at a random location on the screen
     */
    public void spawn() {
        active = true; //sichtbar machen

        xPosition = 70 + rand.nextFloat() * (1020 - 70);// xpos zwischen 70 und 1020
        yPosition = 20 + rand.nextFloat() * (500 - 20); // ypos zwischen 20 und 500

        sprite = new Sprite(new Texture(Gdx.files.internal("assets/PixelFlashGross.png")));
        sprite.setPosition(xPosition, yPosition);
        box = new CollisionBox(xPosition, yPosition, width, height);
    }
    /**
     * PowerUp is applied
     * Call in class Collission
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
