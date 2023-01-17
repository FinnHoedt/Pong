package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.engine.geom.CollisionBox;

/**
 * collision physics for ball
 */
public class Collision {

    private Platform platformA, platformB;
    private Ball[] ball;
    private int ballCount = 1;
    private Score score;
    private CollisionBox borderTop;
    private CollisionBox borderBottom;
    private Flash flash; private SplitBall split; private BiggerPlatform grow;
    private Boolean [] platformCollision = {true, true};
    private Platform lastPlatform;
    private GameScreen game;
    private Sounds sounds;

    public Collision(Platform platformA, Platform platformB, Ball[] ball, Score score, Flash flash, SplitBall split, BiggerPlatform grow, GameScreen game) {
        this.platformA = platformA;
        this.platformB = platformB;
        this.ball = ball;
        this.score = score;
        this.game = game;
        //PowerUp
        this.flash = flash;
        this.split = split;
        this.grow = grow;

        borderTop = new CollisionBox(0, 0, Gdx.graphics.getWidth(),0);
        borderBottom = new CollisionBox(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(),0);
        sounds = Sounds.getSounds();
        sounds.soundsSetUp();
    }

    /**
     * check collisions
     */
    public void checkCollision() {
        for(int i = 0; i< ballCount; i++) {
            checkScoreCollision(i);
            checkPlatformCollision(i);
            checkBorderCollision(i);
            checkFlashCollision(i);
            checkSplitCollision(i);
            checkGrowCollision(i);
            checkBallX(i);

        }
    }

    /**
     * check collision with left and right border
     * increase score
     */
    private void checkScoreCollision(int i) {
        if(ball[i].getBallCollision().getX() < 0) {
            score.upRightScore();
            ball[0].setLastPoint(true);
            ball[0].ballReset();
            game.removeBall();
            sounds.playPlayerScores();
        } else if(ball[i].getBallCollision().getX() > Gdx.graphics.getWidth()) {
            score.upLeftScore();
            ball[0].setLastPoint(false);
            ball[0].ballReset();
            game.removeBall();
            sounds.playPlayerScores();
        }
    }

    /**
     * sets Boolean platformCollision true when ball is in center
     */
    private void checkBallX(int i) {
        float ballX = ball[i].getBallCollision().getX();
        float width = Gdx.graphics.getWidth()/2;
        if(ballX >= width -50 && ballX <= width + 50 && !platformCollision[i]) {
            platformCollision[i] = true;
        }
    }


    /**
     * checks collision with platforms if Boolean platformCollision is true
     */
    private void checkPlatformCollision(int i) {
        if(platformCollision[i]) {
            checkPlatformA(i);
            checkPlatformB(i);
        }
    }
    /*
    private void checkPlatformCollision() {
        if (platformA.getCollisionBox().intersects(ball.getBallCollision())){
            ball.changeHorizontalDirection();
            sounds.playHitPaddle();
        } else if(platformB.getCollisionBox().intersects(ball.getBallCollision())) {
            ball.changeHorizontalDirection();
        }
    }*/

    /**
     * check collision with platform A
     */
    private void checkPlatformA(int i) {
        if (platformA.getCollisionBox().intersects(ball[i].getBallCollision())) {
            ball[i].changeHorizontalDirection();
            platformCollision[i] = false;
            lastPlatform = platformA;
            sounds.playHitPaddle();
        }
    }

    /**
     * check collision with platform B
     */
    private void checkPlatformB(int i) {
        if(platformB.getCollisionBox().intersects(ball[i].getBallCollision())) {
            ball[i].changeHorizontalDirection();
            platformCollision[i] = false;
            lastPlatform = platformB;
            sounds.playHitPaddle();
        }
    }

    /**
     * check collision with top and bottom border
     */
    private void checkBorderCollision(int i) {
        if (borderTop.intersects(ball[i].getBallCollision())) {
            ball[i].changeVerticalDirection();
            sounds.playHitWall();
        } else if (borderBottom.intersects(ball[i].getBallCollision())) {
            ball[i].changeVerticalDirection();
            sounds.playHitWall();
        }
    }
    private void checkFlashCollision(int i) {
        if (flash.getCollisionBox().intersects(ball[i].getBallCollision()) && flash.getState()) { //Collission nur wenn sichtbar
            flash.applyPowerUp(ball[i]);
        }
    }
    private void checkSplitCollision(int i) {
        if (split.getCollisionBox().intersects(ball[i].getBallCollision()) && split.getState()) { //Collission nur wenn sichtbar
            split.applyPowerUp(ball);
        }
    }
    private void checkGrowCollision(int i) {
        if (grow.getCollisionBox().intersects(ball[i].getBallCollision()) && grow.getState()) { //Collission nur wenn sichtbar
            grow.applyPowerUp(lastPlatform);
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

    public void addBall() {
        ballCount = 2;
    }

    public void removeBall() {
        ballCount = 1;
    }
}
