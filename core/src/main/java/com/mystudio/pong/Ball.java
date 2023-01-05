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
    private static final String KEY_ = "SPACE";
    private CollisionPoint point;
    private CollisionBox ballCollisions, testCollisions;
    private Sprite sprite;
    private float ballPosX, ballPosY, ballDirection, ballPlusX, ballPlusY, ballSpeed, ballDiameter;
    boolean gameStart;

    @Override
    public void initialise() {
        gameStart = false;
        ballDiameter = 10;
        ballPosX = Gdx.graphics.getWidth()/2 - ballDiameter/2;
        ballPosY = Gdx.graphics.getHeight()/2 - ballDiameter/2;
        ballSpeed = 4;
        ballDirection = 0f;
        point = new CollisionPoint();
        point.set(ballPosX, ballPosY);
        ballCollisions = new CollisionBox(point.getRenderX(), point.getRenderY(), ballDiameter, ballDiameter);
        sprite = new Sprite(new Texture(Gdx.files.internal("pongBall.png")));
        collisionTest();
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
        if(!gameStart) {
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                gameStart = true;
            }
        }
        if(gameStart) {
            calcNewPos();
        }
    }

    public void ballCollisionsTest(){
        if(point.getY() + ballDiameter/2 >= Gdx.graphics.getHeight() || point.getY() + ballDiameter/2 <= 0){
            ballDirection = -1*ballDirection;
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

    public CollisionPoint getPosition() {
        return point;
    }

}
