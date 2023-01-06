package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionCircle;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

public class Ball implements GameObject{

    private CollisionCircle ballCollision;

    //private Sprite sprite;
    private float ballPosX, ballPosY, ballDirection, ballPlusX, ballPlusY, ballSpeed, ballDiameter;
    boolean gameStart;

    @Override
    public void initialise() {
        gameStart = false;
        ballDiameter = 10;
        ballPosX = Gdx.graphics.getWidth()/2;
        ballPosY = Gdx.graphics.getHeight()/2;
        ballSpeed = 8;
        ballDirection = -1f;

        ballCollision = new CollisionCircle(ballPosX, ballPosY, ballDiameter);
        
        //sprite = new Sprite(new Texture(Gdx.files.internal("pongBall.png")));
    }

    @Override
    public void update() {
        ballCollision.preUpdate();
        ballStart();
    }

    @Override
    public void interpolate(float alpha) {
        ballCollision.interpolate(null, alpha);
    }

    @Override
    public void render(Graphics g) {
        g.fillCircle(ballCollision.getRenderX(), ballCollision.getRenderY(), ballCollision.getRenderRadius());
        //g.drawSprite(sprite, ballCollision.getRenderX(), ballCollision.getRenderY());
    }

    public void ballStart(){
        if(!gameStart) {
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                gameStart = true;
            }
        }
        if(gameStart) {
            calcNewPos();
        }
    }


    public void calcNewPos(){
        int sign;
        if(ballDirection == 0) {
            sign = 1;
        }
        else {
            sign = (int) (ballDirection / Math.abs(ballDirection));
        }
        ballPlusX = (float) (1 / (Math.sqrt(Math.pow(ballDirection, 2) + 1)));
        ballPlusY = (float) (Math.sqrt(-Math.pow(ballPlusX, 2) + 1) * sign);
        ballPlusX = ballPlusX * ballSpeed;
        ballPlusY = ballPlusY * Math.abs(ballSpeed);
        ballCollision.set(ballCollision.getX() + ballPlusX, ballCollision.getY() + ballPlusY);
    }

    public void changeVerticalHorizontal() {
        ballDirection = -ballDirection;
    }

    public void changeHorizontalDirection() {
        ballSpeed = -ballSpeed;
    }

    public CollisionCircle getBallCollision() {
        return ballCollision;
    }
}
