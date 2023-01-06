package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;

public class Platform implements GameObject{
    private float xPosition, yPosition, width, height;
    private CollisionBox box;
    private int keyUP;
    private int keyDown;
    private boolean leftPlatform;

    public Platform(boolean leftPlatform) {
        this.leftPlatform = leftPlatform;
        if (leftPlatform) {
            keyUP = 51;
            keyDown = 47;
        } else {
            keyUP = 19;
            keyDown = 20;
        }
    }

    @Override
    public void initialise() {
        width = 20;
        height = 100;
        yPosition = Gdx.graphics.getHeight()/2 - height/2;

        if (leftPlatform) {
            xPosition = 0;
        } else {
            xPosition = Gdx.graphics.getWidth() - width;
        }

        box = new CollisionBox(xPosition, yPosition, width, height);
    }

    @Override
    public void update() {
        box.preUpdate();
        updateXPosition();
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

    private void updateXPosition() {
        if(leftPlatform) {
            xPosition = 0;
        } else {
            xPosition = Gdx.graphics.getWidth() - width;
        }
        box.setX(xPosition);
    }

    public CollisionBox getCollisionBox() {
        return box;
    }

}
