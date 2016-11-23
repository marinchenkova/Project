package graphicEditor.core.paintedElements;

import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактный класс для объектов, нарисованных одним действием инструмента рисования
 */
public abstract class PaintedElement{
    protected static List<PaintedElement> paintedElements = new ArrayList<PaintedElement>();
    protected static int elementNumber = -1;

    /**
     * Поиск номера нарисованного элемента под курсором
     */
    public static boolean findPainted(int x, int y){
        if(paintedElements.size() > 0){
            for (int i = 0; i < paintedElements.size(); i++){
                if (paintedElements.get(i).onPaintedElement(x, y)){
                    elementNumber = i;
                    return true;
                }
            }
        }
        return false;
        //System.err.print(elementNumber);System.err.print(' ');System.err.println(isOnPainted);
    }

    public static int getElementNumber() throws ArrayIndexOutOfBoundsException{
        return elementNumber;
    }

    /**
     * Нарисовать объект с заданной шириной линии
     */
    public abstract void paint(int x, int y);

    /**
     * Нарисовать весь элемент: метод используется при удалении одного из элементов.
     */
    public abstract void paintWhole();

    /**
     * Если курсор находится на объекте, возвращает true
     */
    public abstract boolean onPaintedElement(int mx, int my);

    /**
     * Удаление графического элемента: элемент удаляется из списка нарисованных элементов данного типа,
     * а затем все оставшиеся элементы отрисовываются заново.
     */
    /*
    public abstract void delete();

    public abstract void outline();
    */
}
