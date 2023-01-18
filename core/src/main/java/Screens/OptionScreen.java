package Screens;

import GameObjects.ColorPicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.mystudio.pong.Pong;
import com.mystudio.pong.Settings;
import com.mystudio.pong.Sounds;
import org.mini2Dx.core.assets.FallbackFileHandleResolver;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.Transition;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;
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
    private Settings settings;
    private ColorPicker colorPickerLeft;
    private ColorPicker colorPickerRight;
    private  Button colorPickerLeftButton;
    private Button colorPickerRightButton;


    /**
     * Initializes OptionScreen and loads theme and UI-Elements
     * @param gc The {@link GameContainer} of the game
     */
    @Override
    public void initialise(GameContainer gc) {
        settings = Settings.getSettings();

        FileHandleResolver fileHandleResolver = new FallbackFileHandleResolver(new ClasspathFileHandleResolver(), new InternalFileHandleResolver());

        assetManager = new AssetManager(fileHandleResolver);

        assetManager.setLoader(UiTheme.class, new UiThemeLoader(fileHandleResolver));

        assetManager.load(UiTheme.DEFAULT_THEME_FILENAME, UiTheme.class);

        uiContainer = new UiContainer(gc, assetManager);

        sounds = Sounds.getSounds();

        colorPickerLeft = new ColorPicker(615,435,70,20);

        colorPickerRight = new ColorPicker(920,435,70,20);

        uiSetup(uiContainer);
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
        soundsCheckboxEvent();
        exitGameScreen(screenManager);
        leftColorPickerEvent();
        rightColorPickerEvent();
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
        renderColorPicker(g);
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
        soundCheckBox.setChecked(settings.getSound());
        uiContainer.add(soundCheckBox);

        musicText = new Label(350,200,1,1);
        musicText.setText("Music");
        musicText.setVisibility(Visibility.VISIBLE);
        musicText.setColor(Color.WHITE);
        uiContainer.add(musicText);

        musicCheckBox = new Checkbox(450,195,1,1);
        musicCheckBox.setVisibility(Visibility.VISIBLE);
        musicCheckBox.setChecked(settings.getMusic());
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

        playerOneText = new Label(600,310,1,1);
        playerOneText.setText("Player 1");
        playerOneText.setVisibility(Visibility.VISIBLE);
        playerOneText.setColor(Color.WHITE);
        uiContainer.add(playerOneText);

        playerTwoText = new Label(900,310,1,1);
        playerTwoText.setText("Player 2");
        playerTwoText.setVisibility(Visibility.VISIBLE);
        playerTwoText.setColor(Color.WHITE);
        uiContainer.add(playerTwoText);

        colorText = new Label(350,440,1,1);
        colorText.setText("Color");
        colorText.setVisibility(Visibility.VISIBLE);
        colorText.setColor(Color.WHITE);
        uiContainer.add(colorText);

        colorPickerLeftButton = new Button(700, 435, 30, 20);
        colorPickerLeftButton.setVisibility(Visibility.VISIBLE);
        uiContainer.add(colorPickerLeftButton);

        colorPickerRightButton = new Button(1000, 435, 30, 20);
        colorPickerRightButton.setVisibility(Visibility.VISIBLE);
        uiContainer.add(colorPickerRightButton);
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
                sounds.volumeChanged();
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
                    sounds.toggleMusic();
                } else {
                    settings.setMusic(true);
                    sounds.toggleMusic();
                }
            }
        });
    }

    private void soundsCheckboxEvent() {
        soundCheckBox.addActionListener(new ActionListener() {
            @Override
            public void onActionBegin(ActionEvent event) {

            }

            @Override
            public void onActionEnd(ActionEvent event) {
                if(!soundCheckBox.isChecked()) {
                    settings.setSound(false);
                    sounds.toggleSounds();
                } else {
                    settings.setSound(true);
                    sounds.toggleSounds();
                }
            }
        });
    }

    private void leftColorPickerEvent() {
        colorPickerLeftButton.addActionListener(new ActionListener() {
            @Override
            public void onActionBegin(ActionEvent event) {
                colorPickerLeft.upArray();
                settings.setLeftPlatformColor(colorPickerLeft.getColorArray());
            }

            @Override
            public void onActionEnd(ActionEvent event) {

            }
        });
    }

    private void rightColorPickerEvent() {
        colorPickerRightButton.addActionListener(new ActionListener() {
            @Override
            public void onActionBegin(ActionEvent event) {
                colorPickerRight.upArray();
                settings.setLeftPlatformColor(colorPickerRight.getColorArray());
            }

            @Override
            public void onActionEnd(ActionEvent event) {

            }
        });
    }


    private void exitGameScreen(ScreenManager screenManager) {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            screenManager.enterGameScreen(MainScreen.ID, new FadeOutTransition(), new FadeInTransition());
            //init = true;
        }
    }

    private void renderColorPicker(Graphics g) {
        colorPickerLeft.render(g);
        colorPickerRight.render(g);
    }


    @Override
    public void preTransitionIn(Transition transitionIn) {
        Pong.inputMultiplexer.addProcessor(uiContainer);
    }
    @Override
    public void preTransitionOut(Transition transitionIn) {
        Pong.inputMultiplexer.removeProcessor(uiContainer);
    }
}
