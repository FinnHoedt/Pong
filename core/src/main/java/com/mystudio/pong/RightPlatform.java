package com.mystudio.pong;

import com.badlogic.gdx.Gdx;

public class RightPlatform extends Platform{

    public RightPlatform() {
        keyUP = 19;
        keyDown = 20;
        xPosition = Gdx.graphics.getWidth() - width - 50;
    }
}
