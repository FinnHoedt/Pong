package GameObjects;

import Screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.graphics.Sprite;

/**
 * This PowerUp splits 1 Ball into 2
 * @see PowerUp
 */
public class SplitBall extends PowerUp{

    private Game game;

    public SplitBall(Game game){
        sprite = new Sprite(new Texture(Gdx.files.internal("assets/axe.png")));
        delay = 0;
        this.game = game;
    }

    /**
     * applies the PowerUps unique Power then disappears again
     * in this case the ball is duplicated
     * @see Ball
     */
    public void applyPowerUp() {
        game.addBall();
        active = false;
    }




}
