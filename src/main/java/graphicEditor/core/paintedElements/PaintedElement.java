package graphicEditor.core.paintedElements;

import graphicEditor.core.util.MouseEvent;

import java.util.ArrayList;

/**
 * Абстрактный класс для объектов, нарисованных одним действием инструмента рисования
 */
public abstract class PaintedElement{
    private int elementNumber = -1;

    /**
     * Поиск номера нарисованного элемента под курсором
     */
    public boolean findPainted(MouseEvent me){
        ArrayList<PaintedElement> pe = me.getPaintedEl();
        if(pe.size() > 0){
            for (int i = 0; i < pe.size(); i++){
                if (pe.get(i).onPaintedElement(me)){
                    elementNumber = i;
                    return true;
                }
            }
        }
        return false;
        //System.err.print(elementNumber);System.err.print(' ');System.err.println(isOnPainted);
    }

    public int getElementNumber() throws ArrayIndexOutOfBoundsException{
        return elementNumber;
    }

    /**
     * Нарисовать объект с заданной шириной линии
     */
    public abstract void paint(MouseEvent me);

    /**
     * Нарисовать весь элемент: метод используется при удалении одного из элементов.
     */
    public abstract void paintWhole();

    /**
     * Если курсор находится на объекте, возвращает true
     */
    public abstract boolean onPaintedElement(MouseEvent me);

    /**
     * Удаление графического элемента: элемент удаляется из списка нарисованных элементов данного типа,
     * а затем все оставшиеся элементы отрисовываются заново.
     */
    /*
    public abstract void delete();

    public abstract void outline();
    */
}
