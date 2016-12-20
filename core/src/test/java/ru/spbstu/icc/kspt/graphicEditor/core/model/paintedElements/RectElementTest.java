package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import org.junit.Test;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import static org.junit.Assert.*;

/**
 * @author Marinchenko V. A.
 */
public class RectElementTest {

    private RectElement re;

    @Test
    public void findPoint() throws Exception {
        re = new RectElement(new Point(100, 100), 10);
        re.addPoint(new Point(200, 200));

        int x = 95 + (int)(Math.random() * 111);
        int y = 95 + (int)(Math.random() * 11);

        assertEquals(true, re.findElement(new Point(x, y)));
    }

    @Test
    public void scale() throws Exception {
        re = new RectElement(new Point(100, 100), 10);
        re.addPoint(new Point(200, 200));

        re.scale(2, 2);
        double newX = re.getPoints().get(3).getX();

        assertEquals(250, newX, 0);
    }

    @Test
    public void translate() throws Exception {
        re = new RectElement(new Point(100, 100), 10);
        re.addPoint(new Point(200, 200));

        re.translate(50, 50);
        double newX = re.getPoints().get(3).getX();

        assertEquals(250, newX, 0);
    }

    @Test
    public void rotate() throws Exception {
        re = new RectElement(new Point(100, 100), 10);
        re.addPoint(new Point(200, 200));

        re.rotate(Math.PI / 2);
        double newX = re.getPoints().get(3).getX();

        assertEquals(200, newX, 0);
    }

}