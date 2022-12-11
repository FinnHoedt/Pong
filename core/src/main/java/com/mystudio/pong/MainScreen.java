package com.mystudio.pong;

import com.badlogic.gdx.graphics.Color;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.ui.element.Button;

public class MainScreen extends BasicGameScreen {
    public static int ID = 1;

    public Button startButton;

    @Override
    public void initialise(GameContainer gc) {

    }

    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {

    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {

    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Main Menu", 32,32);
    }

    @Override
    public int getId() {
        return ID;
    }
}
