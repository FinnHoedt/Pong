package GameObjects;

import GameObjects.PowerUp;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;

/**
 * This PowerUp accelerates the speed of the ball
 * @see PowerUp
 */
public class Flash extends PowerUp {

    public Flash(){
        sprite = new Sprite(new Texture(Gdx.files.internal("assets/PixelFlashGross.png")));
        delay = 7;
    }

    /**
     * applies the PowerUps unique Power then disappears again
     * in this case the speed is raised
     * @see Ball
     */
    public void applyPowerUp(Ball ball) {
        ball.raiseSpeed(3);//Ball wird schneller. Neuer Ball ist wieder normal schnell
        active = false;
        waitForPowerUp();
    }
}
