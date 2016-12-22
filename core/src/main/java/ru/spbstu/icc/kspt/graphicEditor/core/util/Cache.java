package ru.spbstu.icc.kspt.graphicEditor.core.util;

import ru.spbstu.icc.kspt.graphicEditor.core.model.PaintedElement;

import java.util.ArrayList;

/**
 * Кэш списков объектов {@link PaintedElement}. В кэше всегда хранится "нулевое" состояние для возврата
 * пустого списка.
 */
public class Cache {

    private ArrayList<ArrayList<PaintedElement>> state = new ArrayList<>();

    /**
     * Конструктор: вызывается метод {@link Cache#clear()}, в котором инициируется нулевое состояние.
     */
    public Cache(){
        clear();
    }

    /**
     * Очистка кэша: инициализация нулевого состояния.
     */
    public void clear(){
        state = new ArrayList<>();
        state.add(new ArrayList<>());
    }

    /**
     * Добавление состояния в кэш.
     * @param elements состояние
     */
    public void add(ArrayList<PaintedElement> elements){
        state.add(elements);
    }

    /**
     * Удаление последнего состояния в кэше, при этом нулевое состояние не может быть удалено.
     */
    private void removeLast(){
        if(state.size() > 1){
            state.remove(state.size() - 1);
        } else clear();
    }

    /**
     * Возврат предпоследнего состояния: вызов метода {@link Cache#removeLast()} удаляет последнее состояние.
     * @return предпоследнее состояние
     */
    public ArrayList<PaintedElement> getPrevState(){
        removeLast();
        return state.get(state.size() - 1);
    }

    /**
     * Возврат размера списка состояний.
     * @return размер
     */
    public int getSize(){ return state.size(); }

    /**
     * Клонирование списка объектов {@link PaintedElement}.
     * @param elements клонируемый список
     * @return клонированный список
     */
    public static ArrayList<PaintedElement> cloned(ArrayList<PaintedElement> elements){
        ArrayList<PaintedElement> cloned = new ArrayList<>();

        try{
            for(PaintedElement element : elements){
                cloned.add((PaintedElement) element.clone());
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return cloned;
    }
}
