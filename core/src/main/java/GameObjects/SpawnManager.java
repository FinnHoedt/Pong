package GameObjects;

import org.mini2Dx.core.graphics.Graphics;

public class SpawnManager implements GameObject{
    private Flash flash;
    private SplitBall split;
    private BiggerPlatform grow;
    private Boolean spawn;

    public SpawnManager(Game game) {
        spawn = false;
        flash = new Flash();
        split = new SplitBall(game);
        grow = new BiggerPlatform();
    }

    @Override
    public void initialise() {
        flash.initialise();
        split.initialise();
        grow.initialise();
    }

    @Override
    public void update() {
        flash.update();
        split.update();
        grow.update();
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

    public Flash getFlash() {
        return flash;
    }

    public SplitBall getSplit() {
        return split;
    }

    public BiggerPlatform getGrow() {
        return grow;
    }
}
