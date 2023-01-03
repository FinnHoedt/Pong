package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.collisions.PointQuadTree;
import org.mini2Dx.core.collisions.QuadTree;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.geom.Circle;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.screen.BasicGameScreen;

public class Ball implements GameObject{
    private CollisionPoint point;
    private CollisionBox ballCollisions, testCollisions;
    private Sprite sprite;
    private float ballPosX, ballPosY, ballDirection, ballPlusX, ballPlusY, ballSpeed, ballHeight, ballWidth;
    boolean gameStart = false;
    public Ball() {
        ballPosX = 400;
        ballPosY = 400;
        ballHeight = 10;
        ballWidth = 10;
        ballSpeed = 4;
        ballDirection = 2f;
        point = new CollisionPoint();
        point.set(ballPosX, ballPosY);
        ballCollisions = new CollisionBox(point.getRenderX(), point.getRenderY(), ballWidth, ballHeight);
        sprite = new Sprite(new Texture(Gdx.files.internal("pongBall.png")));
        collisionTest();

    }
    @Override
    public void initialise() {

    }

    @Override
    public void update() {
        point.preUpdate();
        ballStart();
        ballCollisionsTest();

    }


    @Override
    public void interpolate(float alpha) {
        point.interpolate(null, alpha);
    }

    @Override
    public void render(Graphics g) {
        g.drawSprite(sprite, point.getRenderX(), point.getRenderY());
    }

    public void ballStart(){
        if(Gdx.input.isButtonPressed(Input.Keys.SPACE)) {
            gameStart = true;
        }
        if(gameStart) {
            calcNewPos();
        }
    }

    public void ballCollisionsTest(){
        if(point.getY() + ballHeight/2 > 600 || point.getY() + ballHeight/2 <= 0){
            ballDirection = -1*ballDirection;
        }
        if(point.getX() + ballHeight/2 > 600 || point.getX() + ballHeight/2 <= 0){
            ballSpeed = -1 * ballSpeed;
        }

    }


    public void collisionTest() {
        testCollisions = new CollisionBox(0, 500, 1000, 10);
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
        point.set(point.getX() + ballPlusX, point.getY() + ballPlusY);
    }

}
