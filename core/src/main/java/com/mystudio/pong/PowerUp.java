package com.mystudio.pong;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.engine.geom.CollisionCircle;

import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;


public abstract class PowerUp implements GameObject{

    @Override
    public void initialise() {}//Objekt fuer die Verwendung vorzubereiten}

    @Override
    public void update() {}//internen Zustand eines Objekts zu aktualisieren}

    @Override
    public void interpolate(float alpha) {}//Animation eines Objekts auf dem Bildschirm}

    @Override
    public void render(Graphics g) {}// Zeichnen des Objekts auf einem Bildschirm}
    /**
     * waits a certain time until the PowerUp appears
     */
    public abstract void waitForPowerUp();
    /**
     * PowerUp appears at a random location on the screen
     */
    public abstract void spawn();
    /**
     * PowerUp is applied
     * Call in class Collission
     */
    public abstract void applyPowerUp(); // individuell für Unterklassen


}
