package graphicEditor.core.paintedElements;

import java.util.ArrayList;

/**
 * Интерфейс для объектов, нарисованных одним действием инструмента рисования
 */
public interface PaintedElement{

    /**
     * Нарисовать объект с заданной шириной линии
     */
    void paintAtom(int x, int y);

    /**
     * Списки координат
     */
    ArrayList<Integer> getXList();

    ArrayList<Integer> getYList();

    /**
     * Если курсор находится на объекте, возвращает true
     */
    boolean onPainted(int x, int y);

    void delete(ArrayList<PaintedElement> pe, int i);

    void outline();

}
