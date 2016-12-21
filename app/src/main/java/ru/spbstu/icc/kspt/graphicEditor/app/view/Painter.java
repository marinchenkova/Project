package ru.spbstu.icc.kspt.graphicEditor.app.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import ru.spbstu.icc.kspt.graphicEditor.app.control.PaintController;
import ru.spbstu.icc.kspt.graphicEditor.core.model.*;
import ru.spbstu.icc.kspt.graphicEditor.core.util.*;
import static ru.spbstu.icc.kspt.graphicEditor.core.util.Cache.cloned;

import java.util.ArrayList;

/**
 * Класс управляет процессом рисования: обработка событий мыши и нажатия управляющих клавиш, отрисовка
 * элементов {@link PaintedElement} и запись состояний в кэш {@link Cache}.
 */
public class Painter extends PaintController{

    private Cache cache = new Cache();

    private GraphicsContext deskGC;

    private PaintedElement elementToEdit = null;

    private Point refPoint = new Point(0, 0);
    private Point curPoint;

    /**
     * Конструктор связывает переменную канваса {@link Painter#deskGC} с такой же переменной контроллера
     * рисования {@link PaintController}.
     * @param controller контроллер рисования
     */
    public Painter(PaintController controller){
        deskGC = controller.getDeskCanvas().getGraphicsContext2D();
    }

    /**
     * Обработка нажатия кнокпи мыши. В режиме рисования создается и отрисовывается новый элемент
     * {@link PaintedElement}, при этом он не записывается на доску, а содержится в переменной
     * активного инструмента {@link ru.spbstu.icc.kspt.graphicEditor.core.model.Instrument#pe}.
     * В режиме редактирования найденный (или нет) элемент записывается в переменную
     * {@link Painter#elementToEdit}.
     * @param event событие мыши
     */
    public void onMousePressed(MouseEvent event){
        //Точка отсчета для действий в режиме редактирования
        refPoint = new Point(event.getX() , event.getY());

        //В режиме редактирования
        if(editing){
            elementToEdit = desk.findPainted(refPoint);

        //В режиме рисования
        } else {
            activeInstrument.onPressed(refPoint, activeWidth);
            paintElement(activeInstrument.getPE());
        }
    }

    /**
     * Обработка перемещения мыши с зажатой кнопкой. В режиме рисования созданный элемент
     * {@link PaintedElement} дополняется точками, при этом он не записывается на доску.
     * В режиме редактирования найденный в методе {@link Painter#onMousePressed(MouseEvent)}
     * элемент редактируется и записывается в переменную {@link Painter#elementToEdit}.
     * @param event событие мыши
     */
    public void onMouseDragged(MouseEvent event){
        double x = event.getX();
        double y = event.getY();

        //В режиме редактирования
        if(elementToEdit != null && editing) {
            //Левая кнопка мыши: перемещение
            if (event.getButton() == MouseButton.PRIMARY) {
                elementToEdit.translate(x - refPoint.getX(), y - refPoint.getY());

                repaint();
                paintElement(elementToEdit);
                refPoint = new Point(x ,y);
            }

            //Средняя кнопка мыши: вращение
            else if (event.getButton() == MouseButton.MIDDLE) {
                elementToEdit.rotate(Math.PI / 100 * (refPoint.getX() - x));

                repaint();
                paintElement(elementToEdit);
                refPoint = new Point(x ,y);
            }

            //Правая кнопка мыши: масштабирование
            else if (event.getButton() == MouseButton.SECONDARY) {
                double cx = elementToEdit.getCenter().getX();
                double cy = elementToEdit.getCenter().getY();

                double kx = Math.abs(x - cx) / Math.abs(refPoint.getX() - cx);
                double ky = Math.abs(y - cy) / Math.abs(refPoint.getY() - cy);

                elementToEdit.scale(kx, ky);

                repaint();
                paintElement(elementToEdit);
                refPoint = new Point(x ,y);
            }

        //В режиме рисования
        } else if(!editing) {
            repaint();
            activeInstrument.onDragged(new Point(x, y));
            paintElement(activeInstrument.getPE());
        }
    }

    /**
     * Обработка отпускания кнопки мыши. В режиме рисования созданный элемент
     * {@link PaintedElement} закрывается для добавления точек с помощью метода активного инструмента
     * {@link Instrument#onReleased()}, при этом он записывается на доску. В режиме редактирования
     * найденный в методе {@link Painter#onMousePressed(MouseEvent)} и отредактированный в методе
     * {@link Painter#onMouseDragged(MouseEvent)} элемент записывается на доску, переменной
     * {@link Painter#elementToEdit} присваивается значение null. Копия состояния доски записывается
     * в кэш {@link Cache}.
     */
    public void onMouseReleased(){
        //В режиме редактирования: завершить действие и вернуть измененный объект в список
        if(editing && elementToEdit != null){
            desk.addElement(elementToEdit);

            //Запись в кэш
            cache.add(cloned(desk.getElements()));

        //В режиме рисования: добавить объект в список
        } else if(!editing){
            activeInstrument.onReleased();

            desk.addElement(activeInstrument.getPE());

            //Запись в кэш
            cache.add(cloned(desk.getElements()));

            elementToEdit = null;
        }
    }

    /**
     * Обработка движения мыши: координаты записываются в переменную текущих координат
     * {@link Painter#curPoint}.
     * @param event событие мыши
     */
    public void onMouseMoved(MouseEvent event){ curPoint = new Point(event.getX(), event.getY()); }

    /**
     * Удаление элемента с доски: метод вызывает поиск элемента {@link PaintedElement} и если находит,
     * происходит удаление. Состояние записывается в кэш.
     */
    public void deleteElement(){
        //Поиск и удаление элемента
        desk.findPainted(curPoint);

        repaint();

        //Запись в кэш
        cache.add(cloned(desk.getElements()));
    }

    /**
     * Отмена действия: предпоследнее состояние в кэше записывается в состояние доски.
     */
    public void undo(){
        desk.setElements(cloned(cache.getPrevState()));
        repaint();
    }

    /**
     * Отрисовка элемента: поочередно извлекаются точки из списка {@link PaintedElement#points} и
     * соединяются линией с шириной {@link PaintedElement#width}.
     * @param element элемент
     */
    public void paintElement(PaintedElement element){
        deskGC.setLineWidth(element.getWidth());
        ArrayList<Point> p = element.getPoints();

        if(p.size() == 1) deskGC.fillRect(p.get(0).getX(), p.get(0).getY(),
                                          element.getWidth(), element.getWidth());

        for(int i = 1; i < p.size(); i++){
            deskGC.strokeLine(p.get(i-1).getX(), p.get(i-1).getY(), p.get(i).getX(), p.get(i).getY());
        }

        deskGC.setLineWidth(activeWidth);
    }

    /**
     * Отрисовка текущего состояния доски с помощью метода {@link Painter#paintElement(PaintedElement)},
     * вызываемого для каждого элемента доски.
     */
    public void repaint(){
        clearBackground();

        if(desk.getElements().size() > 0){
            for(PaintedElement e : desk.getElements()){
                paintElement(e);
            }
        }
    }

    /**
     * Очистка доски: восстанавливается цвет фона доски.
     */
    public void clearBackground(){
        deskGC.setFill((Color) desk.getBackgroundColor());
        deskGC.fillRect(0, 0, desk.getWidth(), desk.getHeight());
        deskGC.setFill(activeColor);
    }
}
