package ru.spbstu.icc.kspt.graphicEditor.app.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.spbstu.icc.kspt.graphicEditor.app.control.PaintController;
import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;
import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

import java.util.ArrayList;

/**
 * @author Marinchenko V. A.
 */
public class Painter extends PaintController{

    private PaintController controller;

    private GraphicsContext deskGC;

    private PaintedElement elementToEdit;

    private Point refPoint = new Point(0, 0);
    private Point curPoint;

    public Painter(PaintController controller){
        this.controller = controller;
        deskGC = controller.getDeskCanvas().getGraphicsContext2D();
    }

    //Нажатие кнопки мыши
    public void onMousePressed(MouseEvent event){
        //Точка отсчета для действий в режиме редактирования
        refPoint = new Point(event.getX() , event.getY());

        if(editing){
            elementToEdit = desk.findPainted(refPoint);

        } else {
            activeInstrument.onPressed(refPoint, activeWidth);
            paintElement(activeInstrument.getPE());
        }
    }

    //Перемещение с зажатой кнопкой мыши
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
                elementToEdit.rotate(0.031415 * (refPoint.getX() - x));
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

    //Отпускание кнопки мыши
    public void onMouseReleased(){
        //В режиме редактирования: завершить действие и вернуть измененный объект в список
        if(editing && elementToEdit != null){
            desk.addElement(elementToEdit);
            elementToEdit = null;

            //В режиме рисования: добавить объект в список
        } else if(!editing){
            activeInstrument.onReleased();
            desk.addElement(activeInstrument.getPE());
        }
    }

    public void onMouseMoved(MouseEvent event){
        curPoint = new Point(event.getX(), event.getY());
    }

    public void paintElement(PaintedElement element){
        ArrayList<Point> p = element.getPoints();
        if(p.size() == 1) deskGC.fillRect(p.get(0).getX(), p.get(0).getY(), activeWidth, activeWidth);
        for(int i = 1; i < p.size(); i++){
            deskGC.strokeLine(p.get(i-1).getX(), p.get(i-1).getY(), p.get(i).getX(), p.get(i).getY());
        }
    }

    public void repaint(){
        repaintBackground();
        if(desk.getPE().size() > 0){
            for(PaintedElement e : desk.getPE()){
                paintElement(e);
            }
        }
    }

    public void repaintBackground(){
        deskGC.setFill((Color) desk.getBackgroundColor());
        deskGC.fillRect(0, 0, desk.getWidth(), desk.getHeight());
        deskGC.setFill(activeColor);
    }

}
