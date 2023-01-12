package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Settings {
    public static Settings settings = new Settings();
    private float volume;
    private boolean music;


    private boolean sound;
    private Settings(){
        volume = 0f;
        music = true;
        sound = true;
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

}
