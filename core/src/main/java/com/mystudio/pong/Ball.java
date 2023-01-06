package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionCircle;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

public class Ball implements GameObject{
    private static final String KEY_ = "SPACE";
    //private CollisionPoint point;
    //private CollisionBox ballCollisions, testCollisions;

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
        //point = new CollisionPoint();
        //point.set(ballPosX, ballPosY);

        ballCollision = new CollisionCircle(ballPosX, ballPosY, ballDiameter);

        //ballCollisions = new CollisionBox(point.getRenderX(), point.getRenderY(), ballDiameter, ballDiameter);
        //sprite = new Sprite(new Texture(Gdx.files.internal("pongBall.png")));
        //collisionTest();
    }

    @Override
    public void update() {
        ballCollision.preUpdate();
        ballStart();
        //ballCollisionsTest();

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

    /*
    public void ballCollisionsTest(){
        if(ballCollision.getY() + ballDiameter/2 >= Gdx.graphics.getHeight() || ballCollision.getY() + ballDiameter/2 <= 0){
            ballDirection = -1*ballDirection;
        }
    }

    public void collisionTest() {
        testCollisions = new CollisionBox(0, 500, 1000, 10);
    }*/

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
    /*
    public CollisionPoint getPosition() {
        return point;
    }*/
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
