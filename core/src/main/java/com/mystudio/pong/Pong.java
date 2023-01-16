package com.mystudio.pong;



import Screens.GameScreen;
import Screens.MainScreen;
import Screens.OptionScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import org.mini2Dx.core.game.ScreenBasedGame;



public class Pong extends ScreenBasedGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.pong";

    private MainScreen mainScreen;
    private OptionScreen optionScreen;
    private GameScreen gameScreen;

    public static InputMultiplexer inputMultiplexer;

	@Override
    public void initialise() {
        Gdx.graphics.setWindowedMode(1200, 580);
        Gdx.graphics.setResizable(false);
        inputMultiplexer = new InputMultiplexer();
        mainScreen = new MainScreen();
        optionScreen = new OptionScreen();
        gameScreen = new GameScreen();
    	this.addScreen(mainScreen);
        this.addScreen(optionScreen);
        this.addScreen(gameScreen);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public int getInitialScreenId() {
        return MainScreen.ID;
    }
}
