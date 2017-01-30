﻿[![Build Status](https://travis-ci.org/marinchenkova/Project.svg?branch=master)](https://travis-ci.org/marinchenkova/Project)
[![codecov](https://codecov.io/gh/marinchenkova/Project/branch/master/graph/badge.svg)](https://codecov.io/gh/marinchenkova/Project)

# Графический редактор на Java + проект Arduino

###Текущее состояние проекта:
- отображение координат мыши, размера картинки
- изменение ширины линии
- Инструменты: Кисть, Линия, Прямоугольник, режим редактирования
- Кэш объектов
- Отмена действий над объектами


![alt text](images/Versions/11.gif)


##Описание
Приложение для создания и обработки двумерных графических изображений.

Сохранение и загрузка файлов,

Минимальный набор инструментов для рисования:
- кисть,
- простые фигуры: прямые линия, прямоугольник,
- инструмент редактирования объектов.

##Дополнительно
  Построитель плоской карты окружения на Arduino, его основные части: 
  - плата Arduino                                    ![alt text](images/README/arduino-uno.png)
  - ультразвуковой дальномер (возвращает расстояние) ![alt text](images/README/hc-sr04.png)
  - шаговый двигатель (возвращает угол поворота оси) ![alt text](images/README/motor.png)
  
Эти два параметра (расстояние и угол поворота оси двигателя) записываются в текстовый файл, который используется графическим редактором для построения плоской карты (вид сверху), например, комнаты.
Вид сверху:
![alt text](images/README/Arduino.gif)

"Ar" - устройство на Arduino.

##В будущем
  - Модернизация устройства на Arduino для реализации трехмерной карты окружения,
  - Добавление возможности работать с 3d графикой в редакторе.
