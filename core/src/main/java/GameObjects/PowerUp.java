package GameObjects;

import com.badlogic.gdx.utils.Timer;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.Random;

public abstract class PowerUp implements GameObject {

    protected Sprite sprite;
    protected float xPosition, yPosition;
    protected float width;
    protected float height;
    protected Random rand;
    protected CollisionBox box;
    protected boolean active;
    protected int delay;

    public PowerUp() {
        width = 70;
        height = 70;
        box = new CollisionBox(10, 10, width, height);
    }

    @Override
    public void initialise() {
        active = false;
        //waitForPowerUp();
    }

    @Override
    public void update() {
        box.preUpdate();
    }

    @Override
    public void interpolate(float alpha) {
        box.interpolate(null, alpha);
    }

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
        }, 10 + rand.nextFloat() * delay);
    }

    /**
     * makes PowerUp-Sprite appear at a random space on the GameScreen
     */
    protected void spawn() {
        active = true;
        rand = new Random();
        xPosition = Gdx.graphics.getWidth()/2 - 300 + (rand.nextFloat() * 600);
        yPosition = Gdx.graphics.getHeight()/2 - 150 + (rand.nextFloat() * 300);

        sprite.setPosition(xPosition, yPosition);
        box.set(xPosition, yPosition, width, height);
        System.out.println("spawwwwwwwwwwwwwn");
    }

    /**
     applies the PowerUps unique Power then disappears again
     */
    public void applyPowerUp() {}

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
