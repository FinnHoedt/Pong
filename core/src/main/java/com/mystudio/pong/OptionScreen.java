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

/**
 * OptionScreen of application
 */
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
    private Label colorText;
    private Sounds sounds;


    /**
     * Initializes OptionScreen and loads theme and UI-Elements
     * @param gc The {@link GameContainer} of the game
     */
    @Override
    public void initialise(GameContainer gc) {
        FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());

        assetManager = new AssetManager(fileHandleResolver);

        assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));

        assetManager.load(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class);

        uiContainer = new UiContainer(gc, assetManager);

        uiSetup(uiContainer);

        Pong.inputMultiplexer.addProcessor(uiContainer);

        settings = Settings.getSettings();
    }
    /**
     * Updates OptionScreen and waits until theme is loaded
     * @param gc The {@link GameContainer} of the game
     * @param screenManager The {@link ScreenManager} of the game
     * @param delta The time in seconds since the last update
     */
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
        musicCheckBoxEvent();
    }
    /**
     * Interpolates OptionScreen
     * @param gc GameContainer
     * @param alpha The interpolation alpha value
     */
    @Override
    public void interpolate(GameContainer gc, float alpha) {
        uiContainer.interpolate(alpha);
    }
    /**
     * Renders OptionScreen and UI-Elements
     * @param gc The {@link GameContainer} of the game
     * @param g The {@link Graphics} context available for rendering
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        uiContainer.render(g);
    }
    /**
     * Returns OptionScreenID
     * @return ID OptionScreenID
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
        musicCheckBox.setChecked(true);
        uiContainer.add(musicCheckBox);

        volumeText = new Label(350,250,1,1);
        volumeText.setText("Volume");
        volumeText.setVisibility(Visibility.VISIBLE);
        volumeText.setColor(Color.WHITE);
        uiContainer.add(volumeText);

        volumeSlider = new Slider(450,240,1,1);
        volumeSlider.setVisibility(Visibility.VISIBLE);
        volumeSlider.setValue(1f);
        uiContainer.add(volumeSlider);

        hotkeysText = new Label(350,310,1,1);
        hotkeysText.setText("Hotkeys");
        hotkeysText.setVisibility(Visibility.VISIBLE);
        hotkeysText.setColor(Color.WHITE);
        uiContainer.add(hotkeysText);

        upText = new Label(350,360,1,1);
        upText.setText("Up");
        upText.setVisibility(Visibility.VISIBLE);
        upText.setColor(Color.WHITE);
        uiContainer.add(upText);

        downText = new Label(350,390,1,1);
        downText.setText("Down");
        downText.setVisibility(Visibility.VISIBLE);
        downText.setColor(Color.WHITE);
        uiContainer.add(downText);

        playerOneText = new Label(500,310,1,1);
        playerOneText.setText("Player 1");
        playerOneText.setVisibility(Visibility.VISIBLE);
        playerOneText.setColor(Color.WHITE);
        uiContainer.add(playerOneText);

        playerTwoText = new Label(630,310,1,1);
        playerTwoText.setText("Player 2");
        playerTwoText.setVisibility(Visibility.VISIBLE);
        playerTwoText.setColor(Color.WHITE);
        uiContainer.add(playerTwoText);

        colorText = new Label(350,440,1,1);
        colorText.setText("Color");
        colorText.setVisibility(Visibility.VISIBLE);
        colorText.setColor(Color.WHITE);
        uiContainer.add(colorText);
    }

    /**
     * Changes volume when slider is used
     */
    private void volumeSliderValue(){
        volumeSlider.addActionListener(new ActionListener() {
            @Override
            public void onActionBegin(ActionEvent event) {

            }

            @Override
            public void onActionEnd(ActionEvent event) {
                settings.setVolume(volumeSlider.getValue());
                .volumeChanged();
            }
        });
    }

    private void musicCheckBoxEvent() {
        musicCheckBox.addActionListener(new ActionListener() {
            @Override
            public void onActionBegin(ActionEvent event) {

            }

            @Override
            public void onActionEnd(ActionEvent event) {
                if(!musicCheckBox.isChecked()) {
                    settings.setMusic(false);
                    settings.toggleSoundtrack();
                } else {
                    settings.setMusic(true);
                    settings.toggleSoundtrack();
                }
            }
        });
    }
}
