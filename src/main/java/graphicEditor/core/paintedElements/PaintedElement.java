package graphicEditor.core.paintedElements;

import java.util.ArrayList;

/**
 * Интерфейс для объектов, нарисованных одним действием инструмента рисования
 */
public interface PaintedElement{

    /**
     * Возврат нарисованного элемента для действий над ним
     */
    default PaintedElement getPaintedElement(){ return this; }

    /**
     * Возврат списков координат
     */
    ArrayList<Integer> getXList();
    ArrayList<Integer> getYList();

    /**
     * Нарисовать объект с заданной шириной линии
     */
    void paintAtom(int x, int y);

    /**
     * Поиск нарисованного элемента в заданных координатах
     */
    boolean onPainted(int x, int y);

    /**
     * Операция масштабирования
     * @param k коэффициент масштабирования
     */
    void scale(int k);

    /**
     * Операция сдвига
     * @param dx сдвиг по x
     * @param dy сдвиг по y
     */
    void translate(int dx, int dy);

    /**
     * Операция поворота
     * @param a угол поворота в радианах
     */
    void rotate(double a);
}
