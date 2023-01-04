package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.mini2Dx.core.assets.FallbackFileHandleResolver;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.ui.InputSource;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.UiThemeLoader;
import org.mini2Dx.ui.element.Hoverable;
import org.mini2Dx.ui.element.TextButton;
import org.mini2Dx.ui.element.Visibility;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;
import org.mini2Dx.ui.listener.HoverListener;
import org.mini2Dx.ui.render.ActionableRenderNode;
import org.mini2Dx.ui.style.UiTheme;




public class MainScreen extends BasicGameScreen {
    public static int ID = 1;
    private AssetManager assetManager;
    private UiContainer uiContainer;
    private TextButton startButton;
    private TextButton settingsButton;
    private TextButton quitButton;
    private ActionListener buttonPress;

    private HoverListener buttonHover;


    @Override
    public void initialise(GameContainer gc) {
        Gdx.graphics.setWindowedMode(1200, 580);

        FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());

        assetManager = new AssetManager(fileHandleResolver);

        assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));

        assetManager.load(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class);

        uiContainer = new UiContainer(gc, assetManager);

        uiSetup(uiContainer);

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
        uiContainer.render(g);
    }

    @Override
    public int getId() {
        return ID;
    }

    private void uiSetup(UiContainer uiContainer) {
        startButton = new TextButton(500, 200, 200, 35);

        settingsButton = new TextButton(500, 250, 200, 35);

        quitButton = new TextButton(500,300,200,35);

        startButton.setText("START");

        settingsButton.setText("SETTINGS");

        quitButton.setText("Quit");

        startButton.setVisibility(Visibility.VISIBLE);

        settingsButton.setVisibility(Visibility.VISIBLE);

        quitButton.setVisibility(Visibility.VISIBLE);

        uiContainer.add(startButton);

        uiContainer.add(settingsButton);

        uiContainer.add(quitButton);

        startButton.addActionListener(buttonPress);

        startButton.addHoverListener(buttonHover);


    }


}





