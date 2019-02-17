import java.awt.Color;
import java.lang.reflect.Field;

/**
 * Created by hadar on 18/06/2017.
 */
public class ColorsParser {


    /**
     * parse color definition and return the specified color.
     *
     * @param s a string that represent a color
     * @return color
     */
    public Color colorFromString(String s) {
        Color color = null;
        //if color is given by name of a color-
        try {
            Field field = Class.forName("java.awt.Color").getField(s);
            color = (Color) field.get(null);
        } catch (Exception e) {
            // color = null;
            //if color is given as rgb args-
            if (s.startsWith("RGB")) {
                String rgbString = s.substring(4, s.length() - 1);
                String[] rgb = rgbString.split(",");
                color = new Color(Integer.parseInt(rgb[0]),
                        Integer.parseInt(rgb[1]),
                        Integer.parseInt(rgb[2]));
            }
        }
        return color;
    }
}
