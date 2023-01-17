package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;

/**
 * This PowerUp splits 1 Ball into 2
 * @see PowerUp
 */
public class SplitBall extends PowerUp{

    private GameScreen game;

    public SplitBall(GameScreen game){
        sprite = new Sprite(new Texture(Gdx.files.internal("assets/axe.png")));
        delay = 20;
        this.game = game;
    }

    /**
     * applies the PowerUps unique Power then disappears again
     * in this case the ball is duplicated
     * @see Ball
     */
    public void applyPowerUp(Ball[] ball) {
        game.addBall();
        ball[1].getBallCollision().set(ball[0].getBallCollision().getX(), ball[0].getBallCollision().getY());
        ball[1].setBallDirection(ball[0].getBallDirection());
        ball[1].setBallSpeed(-ball[0].getBallSpeed());
        /*
        ball[0].changeVerticalDirection();
        */

        active = false;
        waitForPowerUp();
    }
}
