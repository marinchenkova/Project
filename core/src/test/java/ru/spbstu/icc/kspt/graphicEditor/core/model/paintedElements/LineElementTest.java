package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import org.junit.Test;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import static org.junit.Assert.*;

/**
 * @author Marinchenko V. A.
 */
public class LineElementTest {

    private LineElement le;

    @Test
    public void findPoint() throws Exception {
        le = new LineElement(new Point(100, 100), 10);
        le.addPoint(new Point(200, 200));

        int x = 95 + (int)(Math.random() * 111);
        int y = x + (int)(Math.random() * 8);

        assertEquals(true, le.findElement(new Point(x, y)));
    }

    @Test
    public void scale() throws Exception {
        le = new LineElement(new Point(100, 100), 10);
        le.addPoint(new Point(200, 200));

        le.scale(2, 2);
        double newX = le.getEnd().getX();

        assertEquals(300, newX, 0);
    }

    @Test
    public void translate() throws Exception {
        le = new LineElement(new Point(100, 100), 10);
        le.addPoint(new Point(200, 200));

        le.translate(50, 50);
        double newX = le.getEnd().getX();

        assertEquals(250, newX, 0);
    }

    @Test
    public void rotate() throws Exception {
        le = new LineElement(new Point(100, 100), 10);
        le.addPoint(new Point(200, 200));

        le.rotate(Math.PI / 2);
        double newX = le.getEnd().getX();

        assertEquals(100, newX, 0);
    }

}