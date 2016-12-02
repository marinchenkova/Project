package ru.spbstu.icc.kspt.graphicEditor.core.model.paintedElements;

import org.junit.Test;
import ru.spbstu.icc.kspt.graphicEditor.core.model.instruments.Line;
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

        assertEquals(true, le.findPoint(x, y));
    }

    @Test
    public void scale() throws Exception {

    }

    @Test
    public void translate() throws Exception {

    }

    @Test
    public void rotate() throws Exception {

    }

}