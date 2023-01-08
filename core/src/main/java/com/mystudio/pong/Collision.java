package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.engine.geom.CollisionBox;

public class Collision {

    private Platform platformA, platformB;
    private Ball ball;
    private Score score;
    private CollisionBox borderTop;
    private CollisionBox borderBottom;
    private Flash flash;

    public Collision(Platform platformA, Platform platformB, Ball ball, Score score, Flash flash) {
        this.platformA = platformA;
        this.platformB = platformB;
        this.ball = ball;
        this.score = score;
        this.flash = flash;
        borderTop = new CollisionBox(0, 0, Gdx.graphics.getWidth(),0);
        borderBottom = new CollisionBox(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(),0);
    }

    public void checkCollision() {
        checkScoreCollision();
        checkPlatformCollision();
        checkBorderCollision();
        checkFlashCollision();
    }

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

    private void checkPlatformCollision() {
        if (platformA.getCollisionBox().intersects(ball.getBallCollision())){
            ball.changeHorizontalDirection();
        } else if(platformB.getCollisionBox().intersects(ball.getBallCollision())) {
            ball.changeHorizontalDirection();
        }
    }

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
}
