package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import org.mini2Dx.core.assets.FallbackFileHandleResolver;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.UiThemeLoader;
import org.mini2Dx.ui.element.Button;
import org.mini2Dx.ui.element.TextBox;
import org.mini2Dx.ui.style.UiTheme;


public class MainScreen extends BasicGameScreen {
    public static int ID = 1;

    private AssetManager assetManager;
    private UiContainer uiContainer;

    private Button startButton;
    private Button settingsButton;

    private TextBox text;


    @Override
    public void initialise(GameContainer gc) {
        FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());

        assetManager = new AssetManager(fileHandleResolver);

        assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));

        assetManager.load(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class);

        uiContainer = new UiContainer(gc, assetManager);

        startButton = new Button(10,10,20,20);

        settingsButton = new Button(10,50,20,20);

        text = new TextBox(10,100,20,20);

        text.setValue("Test");

        uiContainer.add(startButton);

        uiContainer.add(settingsButton);

        uiContainer.add(text);

        uiContainer.setFlexLayout("flex-column:xs-4c md-3x xl-1c");

        Gdx.input.setInputProcessor(uiContainer);

        uiContainer.setXY(200,200);
    }

    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {
        if(!assetManager.update()) {
            return;
        }
        if(!UiContainer.isThemeApplied()) {
            UiContainer.setTheme(assetManager.get(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class));
        }
        uiContainer.update(delta);

    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {
        uiContainer.interpolate(alpha);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.setColor(Color.CORAL);
        g.fillRect(20,10,200,200);
        g.setColor(Color.WHITE);
        uiContainer.render(g);
    }

    @Override
    public int getId() {
        return ID;
    }
}
