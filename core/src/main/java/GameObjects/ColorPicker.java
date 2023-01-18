package GameObjects;

import com.badlogic.gdx.graphics.Color;
import org.mini2Dx.core.graphics.Graphics;

import java.lang.reflect.Array;

public class ColorPicker implements GameObject {

    private Color[] colorArray;
    int i;

    float x;
    float y;
    float width;
    float height;

    public ColorPicker(float x,float y,float width,float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        colorArray = new Color[]{Color.WHITE, Color.GOLD, Color.BLUE, Color.GREEN};
        i = 0;
    }

    @Override
    public void initialise() {

    }

    @Override
    public void update() {

    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(colorArray[i]);
        g.fillRect(x,y,width,height);
    }

    public void upArray() {
        if(i == colorArray.length - 1) {
            i = 0;
        } else {
            i++;
        }
    }

    public Color getColorArray() {
        return colorArray[i];
    }
}
