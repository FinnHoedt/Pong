package GameObjects;

import org.mini2Dx.core.graphics.Graphics;

public abstract class PowerUp implements GameObject {//vielleicht sollte es doch eher ein Interface sein

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
