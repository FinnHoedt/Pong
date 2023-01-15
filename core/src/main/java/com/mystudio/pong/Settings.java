package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;

public class Settings {
    public static Settings settings = new Settings();
    private float volume;
    private boolean music;
    private boolean sound;
    private int leftPlatformUp;
    private int leftPlatformDown;
    private int rightPlatformUp;
    private int rightPlatformDown;
    private Color leftPlatformColor;
    private Color rightPlatformColor;
    private Settings(){
        volume = 1f;
        music = true;
        sound = true;
        leftPlatformUp = 51;
        leftPlatformDown = 47;
        rightPlatformUp = 19;
        rightPlatformDown = 20;
        leftPlatformColor = Color.WHITE;
        rightPlatformColor = Color.BLACK;
    }

    public static Settings getSettings(){
        return settings;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getVolume() {
        return volume;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean getSound() {
        return sound;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public boolean getMusic() {
        return music;
    }

    public int getLeftPlatformUp() {
        return leftPlatformUp;
    }

    public void setLeftPlatformUp(int leftPlatformUp) {
        this.leftPlatformUp = leftPlatformUp;
    }

    public int getLeftPlatformDown() {
        return leftPlatformDown;
    }

    public void setLeftPlatformDown(int leftPlatformDown) {
        this.leftPlatformDown = leftPlatformDown;
    }

    public int getRightPlatformUp() {
        return rightPlatformUp;
    }

    public void setRightPlatformUp(int rightPlatformUp) {
        this.rightPlatformUp = rightPlatformUp;
    }

    public int getRightPlatformDown() {
        return rightPlatformDown;
    }

    public void setRightPlatformDown(int rightPlatformDown) {
        this.rightPlatformDown = rightPlatformDown;
    }

    public Color getLeftPlatformColor() {
        return leftPlatformColor;
    }

    public void setLeftPlatformColor(Color leftPlatformColor) {
        this.leftPlatformColor = leftPlatformColor;
    }

    public Color getRightPlatformColor() {
        return rightPlatformColor;
    }

    public void setRightPlatformColor(Color rightPlatformColor) {
        this.rightPlatformColor = rightPlatformColor;
    }
}
