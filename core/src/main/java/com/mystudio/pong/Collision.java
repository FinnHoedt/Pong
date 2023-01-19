package com.mystudio.pong;

import GameObjects.*;
import Screens.GameScreen;
import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.engine.geom.CollisionBox;

/**
 * collision physics for ball
 */
public class Collision {

    private Platform platformA, platformB;
    private Ball ball;
    private Flash flash;
    private SplitBall split;
    private BiggerPlatform grow;
    private Boolean platformCollision = true;
    private Platform lastPlatform;
    private Game game;
    private Sounds sounds;

    public Collision(Platform platformA, Platform platformB, Ball ball, SpawnManager manager, Game game) {
        this.platformA = platformA;
        this.platformB = platformB;
        this.ball = ball;
        this.game = game;
        this.flash = manager.getFlash();
        this.split = manager.getSplit();
        this.grow = manager.getGrow();
        lastPlatform = platformA;
        sounds = Sounds.getSounds();
        sounds.soundsSetUp();
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
        checkBallX();
    }

    /**
     * check collision with left and right border
     * increase score
     */
    private void checkScoreCollision() {
        if(ball.getBallCollision().getX() < 0) {
            ball.setLastPoint(true);
            game.upRightScore();
            sounds.playPlayerScores();
        } else if(ball.getBallCollision().getX() > Gdx.graphics.getWidth()) {
            ball.setLastPoint(false);
            game.upLeftScore();
            sounds.playPlayerScores();
        }
    }

    /**
     * sets Boolean platformCollision true when ball is in center
     */
    private void checkBallX() {
        float ballX = ball.getBallCollision().getX();
        float width = Gdx.graphics.getWidth()/2;
        if(ballX >= width -50 && ballX <= width + 50 && !platformCollision) {
            platformCollision = true;
        }
    }

    /**
     * checks collision with platforms if Boolean platformCollision is true
     */
    private void checkPlatformCollision() {
        if(platformCollision) {
            checkPlatformA();
            checkPlatformB();
        }
    }

    /**
     * check collision with platform A
     */
    private void checkPlatformA() {
        if (platformA.getCollisionBox().intersects(ball.getBallCollision())) {
            ball.changeHorizontalDirection();
            platformCollision = false;
            lastPlatform = platformA;
            sounds.playHitPaddle();
        }
    }

    /**
     * check collision with platform B
     */
    private void checkPlatformB() {
        if(platformB.getCollisionBox().intersects(ball.getBallCollision())) {
            ball.changeHorizontalDirection();
            platformCollision = false;
            lastPlatform = platformB;
            sounds.playHitPaddle();
        }
    }

    /**
     * check collision with top and bottom border
     */
    private void checkBorderCollision() {
        if (ball.getBallCollision().getY() - 10 <= 0 || ball.getBallCollision().getY() + 10 >= 580) {
            ball.changeVerticalDirection();
            sounds.playHitWall();
        }
    }

    private void checkFlashCollision() {
        if (flash.getCollisionBox().intersects(ball.getBallCollision()) && flash.getState()) {
            flash.applyPowerUp(ball);
        }
    }
    private void checkSplitCollision() {
        if (split.getCollisionBox().intersects(ball.getBallCollision()) && split.getState()) {
            split.applyPowerUp();
        }
    }
    private void checkGrowCollision() {
        if (grow.getCollisionBox().intersects(ball.getBallCollision()) && grow.getState()) {
            grow.applyPowerUp(lastPlatform);
        }
    }

    public void setPlatformCollision() {
        platformCollision = true;
    }
}
