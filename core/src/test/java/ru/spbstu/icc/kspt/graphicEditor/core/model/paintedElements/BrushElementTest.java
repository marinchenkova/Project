package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import org.junit.Test;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import static org.junit.Assert.assertEquals;

public class BrushElementTest {

    private BrushElement be;

    @Test
    public void findPoint() throws Exception {
        be = new BrushElement(new Point(100, 100), 10);
        int c = 95 + (int)(Math.random() * 11);

        assertEquals(true, be.findPoint(c, c));
    }

    @Test
    public void scale() throws Exception {
        be = new BrushElement(new Point(100, 100), 10);
        be.addPoint(new Point(200, 200));
        be.scale(2, 2);
        int newX = be.getPoints().get(1).getX();

        assertEquals(300, newX, 0);
    }

    @Test
    public void translate() throws Exception {
        be = new BrushElement(new Point(100, 100), 10);
        be.addPoint(new Point(200, 200));
        be.translate(50, 50);
        int newX = be.getPoints().get(1).getX();

        assertEquals(250, newX, 0);
    }

    @Test
    public void rotate() throws Exception {
        be = new BrushElement(new Point(100, 100), 10);
        be.addPoint(new Point(200, 200));
        be.rotate(Math.PI / 2);
        int newX = be.getPoints().get(1).getX();

        assertEquals(100, newX, 0);
    }

}