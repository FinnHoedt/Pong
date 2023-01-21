package GameObjects;

import com.badlogic.gdx.utils.Timer;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Random;

public class SpawnHandler implements GameObject {
    private Flash flash;
    private BiggerPlatform biggerPlatform;
    private SplitBall splitBall;

    private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
    private Random rand;


    public SpawnHandler(Game game) {
        flash = new Flash();
        biggerPlatform = new BiggerPlatform();
        splitBall = new SplitBall(game);
    }

    @Override
    public void initialise() {
        powerUps.add(flash);
        powerUps.add(biggerPlatform);
        powerUps.add(splitBall);
        flash.initialise();
        splitBall.initialise();
        biggerPlatform.initialise();
    }

    public void update() {
        flash.update();
        biggerPlatform.update();
        splitBall.update();
        waitForPowerUp();
    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        flash.render(g);
        splitBall.render(g);
        biggerPlatform.render(g);
    }


    public void waitForPowerUp() {
        if(powerUps.size() > 0) {
            rand = new Random();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    int nextPowerUp = rand.nextInt(powerUps.size());
                    powerUps.get(nextPowerUp).spawn();
                    powerUps.remove(nextPowerUp);
                }
            }, 1 + rand.nextFloat());
        } else {
            return;
        }
    }

    public Flash getFlash() {
        return flash;
    }
    public BiggerPlatform getBiggerPlatform() {
        return biggerPlatform;
    }

    public SplitBall getSplitBall() {
        return splitBall;
    }
}
