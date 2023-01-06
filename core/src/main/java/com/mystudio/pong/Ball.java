package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionCircle;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import java.util.Random;

public class Ball implements GameObject{

    private CollisionCircle ballCollision;
    private Random rand;

    //private Sprite sprite;
    private float ballPosX, ballPosY, ballDirection, ballPlusX, ballPlusY, ballSpeedStart, ballSpeed, ballDiameter;
    boolean gameStart, lastPoint;

    @Override
    public void initialise() {
        gameStart = false;
        ballDiameter = 10;
        ballPosX = Gdx.graphics.getWidth()/2;
        ballPosY = Gdx.graphics.getHeight()/2;
        ballSpeedStart = 10;
        ballSpeed = ballSpeedStart;
        ballDirection = -1f;
        rand = new Random();
        ballCollision = new CollisionCircle(ballPosX, ballPosY, ballDiameter);
        
        //sprite = new Sprite(new Texture(Gdx.files.internal("pongBall.png")));
    }

    public void ballReset(){
        gameStart = false;
        ballPosX = Gdx.graphics.getWidth()/2;
        ballPosY = Gdx.graphics.getHeight()/2;
        ballCollision.set(ballPosX, ballPosY);
        speedDirection();
        randomBallDirection();

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

    public void changeVerticalDirection() {
        ballDirection = -ballDirection;
    }

    public void changeHorizontalDirection() {
        ballSpeed = -ballSpeed;
        randomBallDirection();
    }

    public void randomBallDirection(){
        ballDirection = (rand.nextFloat() - 0.5f) * 2.5f;
    }

    public CollisionCircle getBallCollision() {
        return ballCollision;
    }

    private void speedDirection(){
        if(lastPoint){
            ballSpeed = ballSpeedStart * (1);
        }
        else{
            ballSpeed = ballSpeedStart * (-1);
        }
    }

    public void setLastPoint(boolean lastPoint){
        this.lastPoint = lastPoint;
    }
}
