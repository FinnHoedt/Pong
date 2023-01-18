package com.mystudio.pong;

public class ComputerPlayer {
    private Ball[] ball;
    private Platform platform;
    private GameScreen game;
    public ComputerPlayer(Ball[] ball, Platform platform, GameScreen game) {
        this.ball = ball;
        this.platform = platform;
        this.game = game;
    }




    public void update() {
        int i = 0;
        if(game.getBallCount() == 2) {
            if(ball[1].getBallCollision().getX() > ball[0].getBallCollision().getX()) {
                i = 1;
            }
        }
        if(ball[i].getBallCollision().getX() > 600) {
            if(ball[i].getBallCollision().getY() < platform.getCollisionBox().getY() + platform.getCollisionBox().getHeight()/2 - 20) {
                platform.moveUP();
            }
            if(ball[i].getBallCollision().getY() > platform.getCollisionBox().getY() + platform.getCollisionBox().getHeight()/2 + 20) {
                platform.moveDown();
            }
        }

    }
}
