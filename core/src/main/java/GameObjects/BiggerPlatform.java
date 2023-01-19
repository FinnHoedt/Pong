package GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import org.mini2Dx.core.graphics.Sprite;

/**
 * This PowerUp makes the platform of the last Player who touched the ball grow in length
 * @see PowerUp
 */
public class BiggerPlatform extends PowerUp{
    /**
     Constructor sets the Sprite and delay
     */
    public BiggerPlatform() {
        sprite = new Sprite(new Texture(Gdx.files.internal("assets/Grow.png")));
        delay = 30;
    }

    /**
     * applies the PowerUps unique Power then disappears again
     * in this case the platform grows for 10 seconds then resets the height
     * @see Platform
     */
    public void applyPowerUp(final Platform platform) {
        active = false;

        platform.changeHeight(200);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                platform.resetHeight();
            }
        }, 10);


    }
}
