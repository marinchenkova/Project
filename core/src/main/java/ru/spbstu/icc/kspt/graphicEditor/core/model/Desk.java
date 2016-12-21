package ru.spbstu.icc.kspt.graphicEditor.core.model;

import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * Класс содержит данные о доске: размеры, цвет фона и находящиеся в ней объекты {@link PaintedElement}.
 */
public class Desk {

    private int width;
    private int height;

    private Object backgroundColor;

    private ArrayList<PaintedElement> elements = new ArrayList<>();

    /**
     * Конструктор доски задает размеры и цвет фона.
     * @param w ширина
     * @param h высота
     * @param backCol цвет фона
     */
    public Desk (int w, int h, Object backCol){
        width = w;
        height = h;
        backgroundColor = backCol;
    }

    /**
     * Поиск нарисованного элемента по заданным координатам.
     * @throws NullPointerException если элемент не найден
     */
    public PaintedElement findPainted(Point point) throws NullPointerException{
        if(elements.size() > 0){
            for (PaintedElement e : elements){
                if (e.findElement(point)){
                    elements.remove(e);
                    return e;
                }
            }
        }

        return null;
    }

    /**
     * Добавление элемента на доску: метод вызывается после завершения редактирования или рисования.
     * @param element новый элемент
     */
    public void addElement(PaintedElement element){ elements.add(element); }

    /**
     * Вовзрат списка элементов: вызывается при записи состояния в кэш.
     * @return список элементов
     * @throws NullPointerException если список пуст
     */
    public ArrayList<PaintedElement> getElements() throws NullPointerException{ return elements; }

    /**
     * Возврат цвета фона.
     * @return цвет фона
     */
    public Object getBackgroundColor(){ return backgroundColor; }

    /**
     * Возврат ширины доски.
     * @return ширина
     */
    public int getWidth(){ return width; }

    /**
     * Возврат высоты доски
     * @return высота
     */
    public int getHeight(){ return height; }

    /**
     * Возврат размера доски в виде строки: для выведения информации о размерах доски.
     * @return размер в виде "x, y"
     */
    public String getSizeString(){ return width + ", " + height; }

    /**
     * Установка нового списка элементов: используется при возврате состояния из кэша.
     * @param e новый список элементов
     */
    public void setElements(ArrayList<PaintedElement> e){ elements = e; }

    /**
     * Установка цвета фона.
     * @param backCol цвет фона
     */
    public void setBackgroundColor(Object backCol){ backgroundColor = backCol; }

    /**
     * Установка ширины доски.
     * @param w ширина
     */
    public void setWidth(int w){ width = w; }

    /**
     * Установка высоты доски.
     * @param h высота
     */
    public void setHeight(int h){ height = h; }

    /**
     * Установка размеров доски.
     * @param w ширина
     * @param h высота
     */
    public void setSize(int w, int h){
        setWidth(w);
        setHeight(h);
    }
}

