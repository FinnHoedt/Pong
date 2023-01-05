package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import org.mini2Dx.core.assets.FallbackFileHandleResolver;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.UiThemeLoader;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.style.UiTheme;




public class MainScreen extends BasicGameScreen {
    public static int ID = 1;
    private AssetManager assetManager;
    private UiContainer uiContainer;


    @Override
    public void initialise(GameContainer gc) {
        Gdx.graphics.setWindowedMode(1200, 580);

        FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());

        assetManager = new AssetManager(fileHandleResolver);

        assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));

        assetManager.load("test_1.json", UiTheme.class);

        uiContainer = new UiContainer(gc, assetManager);

        TextButton startButton = new TextButton(500, 200, 200, 35);

        TextButton settingsButton = new TextButton(500, 250, 200, 35);

        startButton.setText("START");

        settingsButton.setText("SETTINGS");

        startButton.setVisibility(Visibility.VISIBLE);

        startButton.setEnabled(true);

        settingsButton.setVisibility(Visibility.VISIBLE);

        uiContainer.add(startButton);

        uiContainer.add(settingsButton);

        Gdx.input.setInputProcessor(uiContainer);

    }

    @Override
    public void update(GameContainer gc, ScreenManager screenManager, float delta) {
        if(!assetManager.update()) {
            return;
        }
        if(!UiContainer.isThemeApplied()) {
            UiContainer.setTheme(assetManager.get("test_1.json", UiTheme.class));
        }
        uiContainer.update(delta);
    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {
        uiContainer.interpolate(alpha);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        uiContainer.render(g);
    }

    @Override
    public int getId() {
        return ID;
    }
}
