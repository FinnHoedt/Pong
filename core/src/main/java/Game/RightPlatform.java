package Game;

import Game.Platform;
import com.badlogic.gdx.Gdx;

/**
 * right platform class
 */
public class RightPlatform extends Platform {

    public RightPlatform() {
        keyUP = 19;
        keyDown = 20;
        xPosition = Gdx.graphics.getWidth() - width - 50;
    }
}
