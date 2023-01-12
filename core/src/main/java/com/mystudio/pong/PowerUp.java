package com.mystudio.pong;

import org.mini2Dx.core.graphics.Graphics;

public abstract class PowerUp implements GameObject{//vielleicht sollte es doch eher ein Interface sein

    @Override
    public void initialise() {}//Objekt fuer die Verwendung vorzubereiten}

    @Override
    public void update() {}//internen Zustand eines Objekts zu aktualisieren}

    @Override
    public void interpolate(float alpha) {}//Animation eines Objekts auf dem Bildschirm}

    @Override
    public void render(Graphics g) {}// Zeichnen des Objekts auf einem Bildschirm}

    /**
     * sets a Timer, which waits a random delay time before the PowerUp should appear on the Screen
     */
    public abstract void waitForPowerUp();
    /**
     * makes PowerUp-Sprite appear at a random space on the GameScreen
     */
    public abstract void spawn();
    /**
     * applies the PowerUps unique Power then disappears again
     */
    public abstract void applyPowerUp(); // individuell für Unterklassen


}
