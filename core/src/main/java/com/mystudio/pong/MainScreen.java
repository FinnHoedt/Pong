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
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.UiThemeLoader;
import org.mini2Dx.ui.element.*;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;
import org.mini2Dx.ui.style.UiTheme;


public class MainScreen extends BasicGameScreen {
    public static int ID = 1;
    private AssetManager assetManager;
    private UiContainer uiContainer;
    private TextButton startButton;
    private TextButton settingsButton;
    private TextButton quitButton;

    @Override
    public void initialise(GameContainer gc) {

        FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());

        assetManager = new AssetManager(fileHandleResolver);

        assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));

        assetManager.load(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class);

        uiContainer = new UiContainer(gc, assetManager);

        uiSetup(uiContainer);

        Pong.inputMultiplexer.addProcessor(uiContainer);
    }

    @Override
    public void update(GameContainer gc, final ScreenManager screenManager, float delta) {
        if(!assetManager.update()) {
            return;
        }
        if(!UiContainer.isThemeApplied()) {
            UiContainer.setTheme(assetManager.get(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class));
        }
        uiContainer.update(delta);
        startButtonPress(screenManager);
        settingsButtonPress(screenManager);
        quitButtonPress();
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

    private void uiSetup(UiContainer uiContainer) {
        startButton = new TextButton(500, 200, 200, 35);
        startButton.setText("START");
        startButton.setVisibility(Visibility.VISIBLE);
        uiContainer.add(startButton);

        settingsButton = new TextButton(500, 250, 200, 35);
        settingsButton.setText("SETTINGS");
        settingsButton.setVisibility(Visibility.VISIBLE);
        uiContainer.add(settingsButton);

        quitButton = new TextButton(500,300,200,35);
        quitButton.setText("Quit");
        quitButton.setVisibility(Visibility.VISIBLE);
        uiContainer.add(quitButton);
    }

    private void startButtonPress(final ScreenManager screenManager) {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void onActionBegin(ActionEvent event) {
                screenManager.enterGameScreen(GameScreen.ID, new FadeOutTransition(), new FadeInTransition());
            }

            @Override
            public void onActionEnd(ActionEvent event) {

            }
        });
    }

    private void settingsButtonPress(final ScreenManager screenManager) {
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void onActionBegin(ActionEvent event) {
                screenManager.enterGameScreen(OptionScreen.ID, new FadeOutTransition(), new FadeInTransition());
                Pong.inputMultiplexer.removeProcessor(uiContainer);
            }

            @Override
            public void onActionEnd(ActionEvent event) {

            }
        });
    }

    private void quitButtonPress() {
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void onActionBegin(ActionEvent event) {
                Gdx.app.exit();
            }

            @Override
            public void onActionEnd(ActionEvent event) {

            }
        });
    }
}





