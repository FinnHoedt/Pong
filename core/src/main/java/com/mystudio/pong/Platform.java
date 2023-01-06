package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

public abstract class Platform implements GameObject{
    protected float xPosition, yPosition, width, height;
    protected CollisionBox box;
    protected int keyUP;
    protected int keyDown;

    public Platform() {
        width = 10;
        height = 100;
    }
    @Override
    public void initialise(){
        yPosition = Gdx.graphics.getHeight()/2 - height/2;
        box = new CollisionBox(xPosition, yPosition, width, height);
    }

    @Override
    public void update() {
        box.preUpdate();
        checkInput();
    }

    @Override
    public void interpolate(float alpha) {
        box.interpolate(null, alpha);
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(box.getRenderX(), box.getRenderY(), box.getRenderWidth(), box.getRenderHeight());
    }

    private void checkInput() {
        if(Gdx.input.isKeyPressed(keyUP)) {
            moveUP();
        }
        if(Gdx.input.isKeyPressed(keyDown)) {
            moveDown();
        }
    }

    private void moveUP() {
        if(!(box.getY() <= 0)) {
            box.setY(box.getY() - 8f);
        }
    }

    private void moveDown() {
        if(!(box.getY() + height >= Gdx.graphics.getHeight())) {
            box.setY(box.getY() + 8f);
        }
    }

    public CollisionBox getCollisionBox() {
        return box;
    }

    public void changeKeybinds(int keyUP, int keyDown) {
        this.keyUP = keyUP;
        this.keyDown = keyDown;
    }

}
