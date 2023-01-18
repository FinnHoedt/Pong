package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

    public static Sounds sounds = new Sounds();
    private Music soundTrack;
    private Sound hitPaddle;
    private Sound hitWall;
    private Sound playerScores;
    private float soundVolume;
    private Settings settings;

    private Sounds() {
        settings = Settings.getSettings();
    }

    public static Sounds getSounds() {
        return sounds;
    }

    public void soundsSetUp() {
        if(settings.getSound()) {
            soundVolume = 0.5f;
        } else {
            soundVolume = 0f;
        }
        hitPaddle = Gdx.audio.newSound(Gdx.files.internal("sounds/hit_paddle_sound.mp3"));
        hitWall = Gdx.audio.newSound(Gdx.files.internal("sounds/hit_wall_sound.mp3"));
        playerScores = Gdx.audio.newSound(Gdx.files.internal("sounds/score_sound.mp3"));
        hitPaddle.setVolume(1, soundVolume);
        hitWall.setVolume(1, soundVolume);
        playerScores.setVolume(1, soundVolume);
    }

    public void soundTrackSetUp() {
        soundTrack = Gdx.audio.newMusic(Gdx.files.internal("ponggoeshard.mp3"));
        soundTrack.setLooping(true);
        soundTrack.setVolume(settings.getVolume());
        //soundTrack.play();
    }

    public void volumeChanged() {
        soundTrack.setVolume(settings.getVolume());
    }

    public void toggleMusic() {
        if(settings.getMusic()) {
            soundTrack.play();
        } else {
            soundTrack.pause();
        }
    }

    public void toggleSounds() {
        if(settings.getSound()) {
            soundVolume = 1f;
        } else {
            soundVolume = 0f;
        }
    }

    public void playHitPaddle() {
        hitPaddle.play(soundVolume);
    }

    public void playHitWall() {
        hitWall.play(soundVolume);
    }

    public void playPlayerScores() {
        playerScores.play(soundVolume);
    }

}
