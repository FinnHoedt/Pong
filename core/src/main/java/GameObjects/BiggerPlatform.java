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

    public BiggerPlatform() {
        sprite = new Sprite(new Texture(Gdx.files.internal("assets/Grow.png")));
        delay = 25;
    }

    /**
     * applies the PowerUps unique Power then disappears again
     * in this case the platform grows
     * @see Platform
     */
    public void applyPowerUp(final Platform platform) {
        active = false;

        platform.changeHeight(200);//Plattform wird kurz größer
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                platform.resetHeight();//Plattform wird wieder normal
            }
        }, 8);

        waitForPowerUp();
    }
}
