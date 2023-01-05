package com.mystudio.pong;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import org.mini2Dx.core.assets.FallbackFileHandleResolver;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.ui.UiContainer;
import org.mini2Dx.ui.UiThemeLoader;
import org.mini2Dx.ui.element.*;
import org.mini2Dx.ui.event.ActionEvent;
import org.mini2Dx.ui.listener.ActionListener;
import org.mini2Dx.ui.style.UiTheme;


public class OptionScreen extends BasicGameScreen {
    public static int ID = 2;

    private AssetManager assetManager;
    private UiContainer uiContainer;

    private Label settingsText;
    private Label soundText;
    private Checkbox soundCheckBox;
    private Label musicText;
    private Checkbox musicCheckBox;
    private Label volumeText;
    private Slider volumeSlider;
    private Label hotkeysText;
    private Label upText;
    private Label downText;
    private Label playerOneText;
    private Label playerTwoText;

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
    public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
        if(!assetManager.update()) {
            return;
        }
        if(!UiContainer.isThemeApplied()) {
            UiContainer.setTheme(assetManager.get(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class));
        }
        uiContainer.update(delta);
        volumeSliderValue();
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
        settingsText = new Label(550,40,1,1);
        settingsText.setText("Settings");
        settingsText.setVisibility(Visibility.VISIBLE);
        settingsText.setColor(Color.WHITE);
        uiContainer.add(settingsText);

        soundText = new Label(350,150,1,1);
        soundText.setText("Sound");
        soundText.setVisibility(Visibility.VISIBLE);
        soundText.setColor(Color.WHITE);
        uiContainer.add(soundText);

        soundCheckBox = new Checkbox(450,145,1,1);
        soundCheckBox.setVisibility(Visibility.VISIBLE);
        uiContainer.add(soundCheckBox);

        musicText = new Label(350,200,1,1);
        musicText.setText("Music");
        musicText.setVisibility(Visibility.VISIBLE);
        musicText.setColor(Color.WHITE);
        uiContainer.add(musicText);

        musicCheckBox = new Checkbox(450,195,1,1);
        musicCheckBox.setVisibility(Visibility.VISIBLE);
        uiContainer.add(musicCheckBox);

        volumeText = new Label(350,250,1,1);
        volumeText.setText("Volume");
        volumeText.setVisibility(Visibility.VISIBLE);
        volumeText.setColor(Color.WHITE);
        uiContainer.add(volumeText);

        volumeSlider = new Slider(450,240,1,1);
        volumeSlider.setVisibility(Visibility.VISIBLE);
        uiContainer.add(volumeSlider);

        hotkeysText = new Label(350,300,1,1);
        hotkeysText.setText("Hotkeys");
        hotkeysText.setVisibility(Visibility.VISIBLE);
        hotkeysText.setColor(Color.WHITE);
        uiContainer.add(hotkeysText);
    }

    private void volumeSliderValue(){
        volumeSlider.addActionListener(new ActionListener() {
            @Override
            public void onActionBegin(ActionEvent event) {

            }

            @Override
            public void onActionEnd(ActionEvent event) {
                    System.out.println(volumeSlider.getValue());
            }
        });
    }
}
