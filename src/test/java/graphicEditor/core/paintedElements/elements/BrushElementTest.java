package graphicEditor.core.paintedElements.elements;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Marinchenko V. A.
 */
public class BrushElementTest {

    private BrushElement be;

    @Test
    public void onPainted() throws Exception {
        be = new BrushElement(100, 100, 10);
        int c = 95 + (int)(Math.random() * ((10) + 1));

        assertEquals(true, be.onPainted(c, c));
    }

    @Test
    public void scale() throws Exception {
        be = new BrushElement(100, 100, 10);
        be.paintAtom(200, 200);
        be.scale(2);
        int newX = be.getXList().get(1);

        assertEquals(300, newX, 0);
    }

    @Test
    public void translate() throws Exception {
        be = new BrushElement(100, 100, 10);
        be.paintAtom(200, 200);
        be.translate(50, 50);
        int newX = be.getXList().get(1);

        assertEquals(250, newX, 0);
    }

    @Test
    public void rotate() throws Exception {
        be = new BrushElement(100, 100, 10);
        be.paintAtom(200, 200);
        be.rotate(Math.PI / 2);
        int newX = be.getXList().get(1);

        assertEquals(100, newX, 0);
    }

}