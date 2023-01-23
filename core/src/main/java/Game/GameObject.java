package Game;

import org.mini2Dx.core.graphics.Graphics;
/**
 * Interface for Gameobjects
 */
public interface GameObject {

    public void initialise();

    public void update();

    public void interpolate(float alpha);

    public void render(Graphics g);
}
