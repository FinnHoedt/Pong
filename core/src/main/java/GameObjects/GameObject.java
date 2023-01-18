package GameObjects;

import org.mini2Dx.core.graphics.Graphics;

public interface GameObject {

    public void initialise();

    public void update();

    public void interpolate(float alpha);

    public void render(Graphics g);
}
