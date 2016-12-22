package ru.spbstu.icc.kspt.graphicEditor.core.model;

import ru.spbstu.icc.kspt.graphicEditor.core.util.Point;

/**
 * Абстрактный класс для реализации инструментов рисования. Инструмент содержит объекты {@link Instrument#icon}
 * и {@link PaintedElement}.
 */
public abstract class Instrument {

    protected Object icon;
    protected PaintedElement pe;

    protected Instrument(){}

    /**
     * Метод должен создавать объект {@link PaintedElement}.
     * @param point точка
     * @param width ширина линии
     */
    public abstract void onPressed(Point point, double width);

    /**
     * Метод должен редактировать существующий объект {@link Instrument#pe}.
     * @param point новая точка
     * @throws NullPointerException если объекта {@link Instrument#pe} не существует
     */
    public void onDragged(Point point) throws NullPointerException{
        if(pe == null) throw new NullPointerException("PaintedElement does not exist");
        pe.addPoint(point);
    }

    /**
     * Этот метод завершает редактирование объекта {@link Instrument#pe}.
     * @throws NullPointerException если объекта {@link Instrument#pe} не существует
     */
    public void onReleased() throws NullPointerException{ pe.close(); }

    /**
     * Возврат объекта {@link PaintedElement}: метод должен быть вызыван после вызова метода
     * {@link Instrument#onPressed(Point, double)}.
     * @return обработанный элемент
     * @throws NullPointerException если объекта {@link Instrument#pe} не существует
     */
    public PaintedElement getPE() throws NullPointerException{ return pe; }

    /**
     * Возврат иконки инструмента.
     * @return иконка
     * @throws NullPointerException при отсутствии иконки
     */
    public Object getIcon() throws NullPointerException{ return icon; }
}

