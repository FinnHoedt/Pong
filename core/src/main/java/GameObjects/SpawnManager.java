package GameObjects;

import com.badlogic.gdx.utils.Timer;
import org.mini2Dx.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.Random;

public class SpawnManager implements GameObject{
    private Flash flash;
    private SplitBall split;
    private BiggerPlatform grow;
    ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
    private Boolean spawn;
    private Random random;
    private Game game;

    public SpawnManager(Game game) {
        random = new Random();
        spawn = false;
        this.game = game;
        flash = new Flash();
        split = new SplitBall(game);
        grow = new BiggerPlatform();

    }

    @Override
    public void initialise() {
        spawn = false;
        flash.initialise();
        split.initialise();
        grow.initialise();
        powerUps.clear();
        powerUps.add(flash);
        powerUps.add(split);
        powerUps.add(grow);
        System.out.println(powerUps);
    }

    @Override
    public void update() {
        flash.update();
        split.update();
        grow.update();
        spawner();
    }

    @Override
    public void interpolate(float alpha) {
    }

    @Override
    public void render(Graphics g) {
        flash.render(g);
        split.render(g);
        grow.render(g);
    }

    private void spawner() {
        if(spawn) {
            spawn = false;
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if(game.getGameStart()) {
                        if (!powerUps.isEmpty()) {
                            pickPowerUp().spawn();
                        }
                        spawn = true;
                    }
                }
            }, 4 + random.nextFloat() * 4);
        }
    }

    public PowerUp pickPowerUp() {
        PowerUp currentPowerUp = powerUps.get(random.nextInt(powerUps.size()));
        powerUps.remove(currentPowerUp);
        return currentPowerUp;
    }

    public Flash getFlash() {
        return flash;
    }

    public SplitBall getSplit() {
        return split;
    }

    public BiggerPlatform getGrow() {
        return grow;
    }

    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
        System.out.println(powerUps);
    }

    public void setSpawn(Boolean spawn) {
        this.spawn = spawn;
    }
}
