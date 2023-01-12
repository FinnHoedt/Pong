package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.engine.geom.CollisionBox;

/**
 * collision physiks for ball
 */
public class Collision {

    private Platform platformA, platformB;
    private Ball ball;
    private Score score;
    private CollisionBox borderTop;
    private CollisionBox borderBottom;
    private Flash flash; private SplitBall split; private BiggerPlatform grow;

    public Collision(Platform platformA, Platform platformB, Ball ball, Score score, Flash flash, SplitBall split, BiggerPlatform grow) {
        this.platformA = platformA;
        this.platformB = platformB;
        this.ball = ball;
        this.score = score;
        //PowerUp
        this.flash = flash;
        this.split = split;
        this.grow = grow;

        borderTop = new CollisionBox(0, 0, Gdx.graphics.getWidth(),0);
        borderBottom = new CollisionBox(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(),0);
    }

    /**
     * check collisions
     */
    public void checkCollision() {
        checkScoreCollision();
        checkPlatformCollision();
        checkBorderCollision();
        checkFlashCollision();
        checkSplitCollision();
        checkGrowCollision();
    }

    /**
     * check collision with left and right border
     * increase score
     */
    private void checkScoreCollision() {
        if(ball.getBallCollision().getX() < 0) {
            score.upRightScore();
            ball.setLastPoint(true);
            ball.ballReset();
        } else if(ball.getBallCollision().getX() > Gdx.graphics.getWidth()) {
            score.upLeftScore();
            ball.setLastPoint(false);
            ball.ballReset();
        }
    }

    /**
     * check collision with platform
     */
    private void checkPlatformCollision() {
        if (platformA.getCollisionBox().intersects(ball.getBallCollision())){
            ball.changeHorizontalDirection();
        } else if(platformB.getCollisionBox().intersects(ball.getBallCollision())) {
            ball.changeHorizontalDirection();
        }
    }

    /**
     * check collision with top and bottom border
     */
    private void checkBorderCollision() {
        if (borderTop.intersects(ball.getBallCollision())) {
            ball.changeVerticalDirection();
        } else if (borderBottom.intersects(ball.getBallCollision())) {
            ball.changeVerticalDirection();
        }
    }
    private void checkFlashCollision() {
        if (flash.getCollisionBox().intersects(ball.getBallCollision()) && flash.getState() == true) { //Collission nur wenn sichtbar
            flash.applyPowerUp();
        }
    }
    private void checkSplitCollision() {
        if (split.getCollisionBox().intersects(ball.getBallCollision()) && split.getState() == true) { //Collission nur wenn sichtbar
            split.applyPowerUp();
        }
    }
    private void checkGrowCollision() {
        if (grow.getCollisionBox().intersects(ball.getBallCollision()) && grow.getState() == true) { //Collission nur wenn sichtbar
            grow.applyPowerUp();
        }
    }
    /*private void checkPowerUpCollision() {
        //if(powerUp instance of flash) {
            if (flash.getCollisionBox().intersects(ball.getBallCollision()) && flash.getState() == true) {
                flash.applyPowerUp();
            }
        //}
    }
    */
}
