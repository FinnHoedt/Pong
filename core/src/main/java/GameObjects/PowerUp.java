package GameObjects;

import com.badlogic.gdx.utils.Timer;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.Random;

public abstract class PowerUp implements GameObject {//vielleicht sollte es doch eher ein Interface sein

    protected Sprite sprite;
    protected float xPosition, yPosition;
    protected float width;
    protected float height;
    protected Random rand;
    protected CollisionBox box;
    protected boolean active;
    protected int delay;

    //Objekt fuer die Verwendung vorzubereiten
    @Override
    public void initialise() {
        width = 70;
        height = 70;
        active = false;
        box = new CollisionBox(10, 10, width, height); // wird geupdatet
        waitForPowerUp();// wird mit Anfang Spiel gestartet NICHT MIT GAMESCREEN !!! ÄNDERN
    }

    //internen Zustand eines Objekts zu aktualisieren}
    @Override
    public void update() {
        box.preUpdate();
    }

    //Animation eines Objekts auf dem Bildschirm
    @Override
    public void interpolate(float alpha) {
        box.interpolate(null, alpha);
    }

    // Zeichnen des Objekts auf einem Bildschirm
    @Override
    public void render(Graphics g) {
        if (active) {
            g.drawSprite(sprite);
        }
    }

    /**
     * sets a Timer, which waits a random delay time before the PowerUp should appear on the Screen
     */
    public void waitForPowerUp() {
        rand = new Random();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                spawn();
            }
        }, 20 + rand.nextFloat() * delay);
    }

    /**
     * makes PowerUp-Sprite appear at a random space on the GameScreen
     */
    protected void spawn() {
        active = true; //sichtbar machen
        rand = new Random();
        xPosition = 70 + rand.nextFloat() * (1020 - 70);// xpos zwischen 70 und 1020
        yPosition = 20 + rand.nextFloat() * (500 - 20); // ypos zwischen 20 und 500

        sprite.setPosition(xPosition, yPosition);
        box = new CollisionBox(xPosition, yPosition, width, height);
    }

    /**
     applies the PowerUps unique Power then disappears again
     */
    public void applyPowerUp() {} // individuell für Unterklassen

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
