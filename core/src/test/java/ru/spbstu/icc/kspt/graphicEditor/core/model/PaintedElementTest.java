package ru.spbstu.icc.kspt.graphicEditor.core.model;

import org.junit.Test;
import ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements.LineElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import static org.junit.Assert.*;

/**
 * @author Marinchenko V. A.
 */
public class PaintedElementTest {

    private PaintedElement pe;
    private Point p;

    @Test
    public void getRotatedPoint() throws Exception {
        pe = new LineElement(new Point(100, 100), 10);
        pe.addPoint(new Point(200, 200));

        p = new Point(100, 200);
        Point newP = pe.getRotatedPoint(p, Math.PI / 2);

        assertEquals(100, newP.getY(), 0);
    }

    @Test
    public void getScaledPoint() throws Exception {
        pe = new LineElement(new Point(100, 100), 10);
        pe.addPoint(new Point(200, 200));

        p = new Point(100, 200);
        Point newP = pe.getScaledPoint(p, 2, 3);

        assertEquals(300, newP.getY(), 0);
    }

}