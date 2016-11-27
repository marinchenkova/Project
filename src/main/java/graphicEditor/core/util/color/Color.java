package graphicEditor.core.util.color;

/**
 * Цвет в системе RGBA
 */
public class Color {
    private double red;
    private double green;
    private double blue;
    private double alpha;

    public Color(double r, double g, double b){
        red = r;
        green = g;
        blue = b;
    }

    public Color(double r, double g, double b, double a){
        this(r, g, b);
        alpha = a;
    }
}
