package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

    public static Sounds sounds = new Sounds();
    private Music soundTrack;
    private Sound hitPaddle, hitWall, playerScores;
    private Sound flashSound, biggerPlatformSound, splitBallSound;
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
        flashSound = Gdx.audio.newSound(Gdx.files.internal("sounds/flash_sound.mp3"));
        biggerPlatformSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bigger_plattform_sound.mp3"));
        splitBallSound = Gdx.audio.newSound(Gdx.files.internal("sounds/split_ball_sound.mp3"));
        hitPaddle.setVolume(1, soundVolume);
        hitWall.setVolume(1, soundVolume);
        playerScores.setVolume(1, soundVolume);
        flashSound.setVolume(1, soundVolume);
        biggerPlatformSound.setVolume(1, soundVolume);
        splitBallSound.setVolume(1, soundVolume);
    }

    public void soundTrackSetUp() {
        soundTrack = Gdx.audio.newMusic(Gdx.files.internal("ponggoeshard.mp3"));
        soundTrack.setLooping(true);
        soundTrack.setVolume(settings.getVolume());
        soundTrack.play();
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
            soundVolume = .5f;
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
    public void playFlashSound() {
        flashSound.play(soundVolume);
    }
    public void playBiggerPlatformSound() {
        biggerPlatformSound.play(soundVolume);
    }
    public void playSplitBallSound() {
        splitBallSound.play(soundVolume);
    }

}
