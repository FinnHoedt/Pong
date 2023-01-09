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

/**
 * MainScreen of application
 */
public class MainScreen extends BasicGameScreen {
    public static int ID = 1;
    private AssetManager assetManager;
    private UiContainer uiContainer;
    private TextButton startButton;
    private TextButton settingsButton;
    private TextButton quitButton;

    /**
     * Initializes MainScreen and loads theme and UI-Elements
     * @param gc The {@link GameContainer} of the game
     */
    @Override
    public void initialise(GameContainer gc) {

        FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());

        assetManager = new AssetManager(fileHandleResolver);

        assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));

        assetManager.load("test_1.json", UiTheme.class);

        uiContainer = new UiContainer(gc, assetManager);

        uiSetup(uiContainer);

        Pong.inputMultiplexer.addProcessor(uiContainer);
    }

    /**
     * Updates MainScreen and waits until theme is loaded
     * @param gc The {@link GameContainer} of the game
     * @param screenManager The {@link ScreenManager} of the game
     * @param delta The time in seconds since the last update
     */
    @Override
    public void update(GameContainer gc, final ScreenManager screenManager, float delta) {
        if(!assetManager.update()) {
            return;
        }
        if(!UiContainer.isThemeApplied()) {
            UiContainer.setTheme(assetManager.get("test_1.json", UiTheme.class));
        }
        uiContainer.update(delta);
        startButtonPress(screenManager);
        settingsButtonPress(screenManager);
        quitButtonPress();
    }

    /**
     * Interpolates MainScreen
     * @param gc GameContainer
     * @param alpha The interpolation alpha value
     */
    @Override
    public void interpolate(GameContainer gc, float alpha) {
        uiContainer.interpolate(alpha);
    }

    /**
     * Renders MainScreen and UI-Elements
     * @param gc The {@link GameContainer} of the game
     * @param g The {@link Graphics} context available for rendering
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        uiContainer.render(g);
    }

    /**
     * Returns MainScreenID
     * @return ID MainScreenID
     */
    @Override
    public int getId() {
        return ID;
    }

    /**
     * Creates UI-Elements and sets them up
     * @param uiContainer UIContainer
     */
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

    /**
     * Changes to GameScreen if start-button is pressed
     * @param screenManager ScreenManager
     */
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

    /**
     * Changes to OptionsScreen if settings-button is pressed
     * @param screenManager ScreenManager
     */
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

    /**
     * Quits application if quit-button is pressed
     */
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





