package Screens;

import GameObjects.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.mystudio.pong.*;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.Transition;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;

/**
 * The GameScreen generates the screen for playing pong itself and manages it
 * @author bwecke
 */
public class GameScreen extends BasicGameScreen {
    public static int ID = 3;

    private Game game;


    @Override
    public void initialise(GameContainer gc) {
        game = new Game();
        game.initialise();
    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends org.mini2Dx.core.screen.GameScreen> screenManager, float delta) {
        game.update();
        exitGameScreen(screenManager);
    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {
        game.interpolate(alpha);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        generateHyphen(g);
        game.render(g);
    }

    @Override
    public int getId() {
        return ID;
    }

    private void generateHyphen(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        float width = 8;
        float count = 12;
        float gap = 10;
        float height = (Gdx.graphics.getHeight() /count - gap);
        float x = (Gdx.graphics.getWidth()/2) - (width/2);
        float y;
        for (int i = 1; i <= count; i++) {
            y = (height+gap)*i - (height+gap/2);
            g.fillRect(x,y,width,height);
        }
    }

    private void exitGameScreen(ScreenManager screenManager) {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            screenManager.enterGameScreen(MainScreen.ID, new FadeOutTransition(), new FadeInTransition());
        }
    }

    @Override
    public void preTransitionIn(Transition transitionIn) {
        game.preTransitionIn();
    }

}
