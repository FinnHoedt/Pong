package Menu;

import Game.GameObject;
import com.badlogic.gdx.graphics.Color;
import org.mini2Dx.core.graphics.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * ColorPicker through which you can change color of Platforms
 */
public class ColorPicker {

    private ArrayList<Color> colorArray;
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
        colorArray = new ArrayList<Color>(Arrays.asList(Color.WHITE, Color.GOLD, Color.BLUE, Color.GREEN));
        i = 0;
    }

    /**
     * Renders ColorPicker
     * @param g The {@link Graphics} context available for rendering
     */
    public void render(Graphics g) {
        g.setColor(colorArray.get(i));
        g.fillRect(x,y,width,height);
    }

    /**
     * Steps through Array of Color
     */
    public void upArray() {
        if(i == colorArray.size() - 1) {
            i = 0;
        } else {
            i++;
        }
    }

    /**
     * Returns current ArrayColor
     * @return color current Color
     */
    public Color getColorArray() {
        return colorArray.get(i);
    }
}
