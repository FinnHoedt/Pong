package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import org.mini2Dx.core.engine.geom.CollisionBox;

public class Collision {

    private Platform platformA, platformB;
    private Ball ball;
    private Score score;
    private CollisionBox borderTop;
    private CollisionBox borderBottom;

    public Collision(Platform platformA, Platform platformB, Ball ball, Score score) {
        this.platformA = platformA;
        this.platformB = platformB;
        this.ball = ball;
        this.score = score;
        borderTop = new CollisionBox(0, 0, Gdx.graphics.getWidth(),0);
        borderBottom = new CollisionBox(0, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(),0);
    }

    public void checkCollision() {
        checkScoreCollision();
        checkPlatformCollision();
        checkTopBottomCollision();
    }

    private void checkScoreCollision() {
        if(ball.getBallCollision().getX() < 0) {
            score.upRightScore();
            ball.initialise();
        } else if(ball.getBallCollision().getX() > Gdx.graphics.getWidth()) {
            score.upLeftScore();
            ball.initialise();
        }
    }

    private void checkPlatformCollision() {
        if (platformA.getCollisionBox().intersects(ball.getBallCollision())){
            ball.changeHorizontalDirection();
        } else if(platformB.getCollisionBox().intersects(ball.getBallCollision())) {
            ball.changeHorizontalDirection();
        }
    }

    private void checkTopBottomCollision() {
        if (borderTop.intersects(ball.getBallCollision())) {
            ball.changeVerticalHorizontal();
        } else if (borderBottom.intersects(ball.getBallCollision())) {
            ball.changeVerticalHorizontal();
        }
    }
}
