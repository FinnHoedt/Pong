package GameObjects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.mystudio.pong.Settings;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

/**
 * platform class
 */
public abstract class Platform implements GameObject {
    protected float xPosition, yPosition, width, height;
    protected CollisionBox box;
    public Settings settings;
    protected int keyUP;
    protected int keyDown;
    public Color color;

    public Platform() {
        width = 10;
        height = 100;
        settings = Settings.getSettings();
    }

    /**
     * initialise platform
     * create collision box
     */
    @Override
    public void initialise(){
        yPosition = Gdx.graphics.getHeight()/2 - height/2;
        box = new CollisionBox(xPosition, yPosition, width, height);
    }

    /**
     * update platform
     */
    @Override
    public void update() {
        box.preUpdate();
        checkInput();
    }

    /**
     * interpolate platform
     * @param alpha
     */
    @Override
    public void interpolate(float alpha) {
        box.interpolate(null, alpha);
    }

    /**
     * render platform
     * @param g
     */
    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(box.getRenderX(), box.getRenderY(), box.getRenderWidth(), box.getRenderHeight());
    }

    /**
     * check keys for input
     */
    private void checkInput() {
        if(Gdx.input.isKeyPressed(keyUP)) {
            moveUP();
        }
        if(Gdx.input.isKeyPressed(keyDown)) {
            moveDown();
        }
    }

    /**
     * move platform up
     */
    private void moveUP() {
        if(!(box.getY() <= 0)) {
            box.setY(box.getY() - 8f);
        }
    }

    /**
     * move platform down
     */
    private void moveDown() {
        if(!(box.getY() + box.getHeight() >= Gdx.graphics.getHeight())) {
            box.setY(box.getY() + 8f);
        }
    }

    /**
     * get collision box
     * @return collision box
     */
    public CollisionBox getCollisionBox() {
        return box;
    }

    /**
     * change keys
     * @param keyUP
     * @param keyDown
     */
    public void changeKeybinds(int keyUP, int keyDown) {
        this.keyUP = keyUP;
        this.keyDown = keyDown;
    }

    /**
     * change platform height
     * @param newHeight
     */
    public void changeHeight(float newHeight) {
        box.setY(box.getY() - ((newHeight - box.getHeight()) / 2));
        box.setHeight(newHeight);
    }

    /**
     * reset platform height
     */
    public void resetHeight() {
        box.setY(box.getY() - ((height - box.getHeight()) / 2));
        box.setHeight(height);
    }

    public void changeColor(Color color) {
        this.color = color;
    }
}
