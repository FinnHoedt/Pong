package com.mystudio.pong;



import org.mini2Dx.core.game.ScreenBasedGame;



public class Pong extends ScreenBasedGame {
	public static final String GAME_IDENTIFIER = "com.mystudio.pong";

	@Override
    public void initialise() {
    	this.addScreen(new MainScreen());
        this.addScreen(new OptionScreen());
        this.addScreen(new GameScreen());
    }

    @Override
    public int getInitialScreenId() {
        return GameScreen.ID;
    }
}
