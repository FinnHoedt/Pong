package com.mystudio.pong;

import GameObjects.*;
import Screens.GameScreen;

public class ComputerPlayer {
    private Ball[] ball;
    private Platform platformA, platformB;
    private Game game;
    public ComputerPlayer(Ball[] ball, Platform platformA, Platform platformB, Game game) {
        this.ball = ball;
        this.platformA = platformA;
        this.platformB = platformB;
        this.game = game;
    }




    public void update() {
        int i = 0;
        if(game.getBallCount() == 2) {
            if(ball[1].getBallCollision().getX() > ball[0].getBallCollision().getX()) {
                i = 1;
            }
        }
        if(ball[i].getBallCollision().getY() < platformB.getCollisionBox().getY() + platformB.getCollisionBox().getHeight()/2 - 20) {
            platformB.moveUP();
        }
        if(ball[i].getBallCollision().getY() > platformB.getCollisionBox().getY() + platformB.getCollisionBox().getHeight()/2 + 20) {
            platformB.moveDown();
        }


    }

    public void update2() {
        int i = 0;
        if(game.getBallCount() == 2) {
            if(ball[1].getBallCollision().getX() < ball[0].getBallCollision().getX()) {
                i = 1;
            }
        }

        if(ball[i].getBallCollision().getY() < platformA.getCollisionBox().getY() + platformA.getCollisionBox().getHeight()/2 - 20) {
            platformA.moveUP();
        }
        if(ball[i].getBallCollision().getY() > platformA.getCollisionBox().getY() + platformA.getCollisionBox().getHeight()/2 + 20) {
            platformA.moveDown();
        }
    }
}
