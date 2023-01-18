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
    private Ball ball[] = {new Ball(), new Ball()};
    private Score score;
    private LeftPlatform leftPlatform;
    private RightPlatform rightPlatform;
    private Collision collision;
    private Flash flash;
    private SplitBall split;
    private BiggerPlatform grow;
    private int ballCount = 1;
    //private boolean init;

    /**
     * Generates a new ball, score, a left and right platform and a collision class
     * @param gc The {@link GameContainer} of the game
     */

    @Override
    public void initialise(GameContainer gc) {
        score = new Score();
        leftPlatform = new LeftPlatform();
        rightPlatform = new RightPlatform();
        flash = new Flash();
        split = new SplitBall(this);
        grow = new BiggerPlatform();
        collision = new Collision(leftPlatform, rightPlatform, ball, score,flash, split, grow, this);

    }

    /**
     * Calls update methods for ball, left and right platform, and for the collision class
     * @param gc The {@link GameContainer} of the game
     * @param screenManager The {@link ScreenManager} of the game
     * @param delta The time in seconds since the last update
     */
    @Override
    public void update(GameContainer gc, ScreenManager<? extends org.mini2Dx.core.screen.GameScreen> screenManager, float delta) {
        for(int i = 0; i< ballCount; i++) {
            ball[i].update();
        }
        leftPlatform.update();
        rightPlatform.update();
        flash.update();
        split.update();
        grow.update();
        collision.checkCollision();
        exitGameScreen(screenManager);
    }

    /**
     * Calls interpolate methods for ball, left and right platform
     * @param gc
     * @param alpha The interpolation alpha value
     */
    @Override
    public void interpolate(GameContainer gc, float alpha) {
        for(int i = 0; i< ballCount; i++) {
            ball[i].interpolate(alpha);
        }
        leftPlatform.interpolate(alpha);
        rightPlatform.interpolate(alpha);
    }

    /**
     * Renders the game field
     * @param gc The {@link GameContainer} of the game
     * @param g The {@link Graphics} context available for rendering
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        generateHyphen(g);
        score.render(g);
        for(int i = 0; i< ballCount; i++) {
            ball[i].render(g);
        }
        leftPlatform.render(g);
        rightPlatform.render(g);
        flash.render(g);
        split.render(g);
        grow.render(g);
    }

    /**
     * Returns ID form GameScreen
     * @return ID
     */
    @Override
    public int getId() {
        return ID;
    }

    /**
     * Generates the hyphen in the middle of the screen
     * @param g
     */
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
            //init = true;
        }
    }

    @Override
    public void preTransitionIn(Transition transitionIn) {
        for(int i = 0; i< ballCount; i++) {
            ball[i].initialise();
        }
        ball[0].raiseSpeed(2);
        score.initialise();
        leftPlatform.initialise();
        rightPlatform.initialise();
        flash.initialise();
        split.initialise();
        grow.initialise();
    }

    public void addBall() {
        ballCount = 2;
        ball[1].initialise();
        ball[1].setGameStartTrue();
        collision.addBall();
    }

    public void removeBall() {
        ballCount = 1;
        collision.removeBall();
    }
}
