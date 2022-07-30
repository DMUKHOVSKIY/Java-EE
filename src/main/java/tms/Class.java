package tms;

import java.util.LinkedList;
import java.util.List;

public class Class {
    //Любое программирование стоит на 2 вещах: алгоритмы и структуры данных
    //Структуры данных нужны для того, чтобы хранить данные (Если это ООП язык, то хранение объектов)
    //Сами структуры данных тоже объекты (например ArrayList)
    //Коллекции в JDK в Java - готовые реализации самых популярных структур данных
    //Разные структуры данных хранят объекты по разному(в разном представлении)
    //В зависимости от представления те или иные операции(алгоритмы) будут работать с разной скоростью
    //Коллекции: List, Set, Queue, Map, Stack
    //Есть конкретные структуры данных, а есть абстрактные
    //Интерфейс List - абстрактная структура данных, которая регламентирует работу и вид хранения информации в виде упорядоченного списка, либо списка (как массив)
    //Массив - часто нативная структура данных/линейная стр. данных с ограниченным размером, хотя есть ЯП, где мы можем менять размер массива
    //Доступ к элементам массива осуществляется по индексу. Индекс - целочисленный тип (Integer). Есть языки, где можно менять начальный индекс, например 1, а не 0
    //Лист - така же линейная структура данных, как и массив. Мы храним наши элементы в виде списка. Объекты идут друг за другом. Во всех листах мы можем сделать что-то по индексу(добавить, достать, удалить)
    //В джава есть 2 реализации листа (ArrayList, LinkedList). Старая реализация листа -  Vector(Такой же как и ArrayList, все те же методы, но потокобезопасный - медленный)
    //Доступ к элементам ArrayList осуществляется по индексу.
    //LinkedList - такой же, как и ArrayList, но внутри нет массива, а есть Node(нода)(нода это объект), внутри которой лежит объект.
    //LinkedList - двусторонний, двусвязный список. В самом объекте LinkedList есть 2 переменные(head, tail), которые хранят ссылки на начало и конец списка
    //(на 1 и последнюю ноду). В каждом объекте Node есть 3 переменные(1-ссылка на следующую ноду, 2 - ссылка на предыдущую, 3- сам объект)
    //В LinkedList мы можем тоже брать элементы по индексу
    //Считается, что LinkedList круче, когда мы часто меняем элементы или работаем с серединой списка, но на современных мощных ПК всегда выигрывает ArrayList,
    //LinkedList лучше при реализации двусторонней очереди или стека. Ещё LinkedList лучше, когда мы работаем с первыми 5-10 элементами в листе(любые операции).
    //Queue(очередь):
    //1)Односторонняя очередь:
    //Способ организации данных - FIFO (1 пришёл 1 ушёл). Используется, когда нам надо чтобы операции выполнялись последовательно
    //Очередь может быть основана на ArrayList и на LinkedList. Мы можем выполнять операции только с 1 и последним элементом очереди
    //2)Двусторонняя очередь:
    //Можем добавлять и доставать из начала и из конца. Если мы с одной стороны запрещаем операции, то получается стек
    //Set(список):
    //Set - набор уникальных элементов. HashSet(несортированный/основан на HashMap) TreeSet(сортированный/основан на TreeMap)

    //List, Queue, Set наследуются от интерфейса Collection, а Collection наследуется от интерфейса Iterable. Значит они все имеют метод итератор, который нужен,
    //чтобы итерироваться по любой из этих коллекций
    //Map(словарь):
    //Данные хранятся в виде пар: ключ-значение. Map не является Iterable
    //Map может итерироваться, но если нам надо итерироваться по мапе, то что-то не так.
    //Нам не следует запускать циклы в мапе. Мапа изначально создана, для того, чтобы там хранить элементы и доставать их одним разом по ключу
    //Основной смысл Map - скорость выдачи результатов. В реальности это О(1) - сложность достать элемент
    //Внутри HashMap - массив односвязных списков на 16 ячеек. Load factor - 0.75, который называется Table. Null может быть в качестве ключа
    //Мы берём ключ -> вычисляем хэш код ключа -> берём  модуль остатка от деления нашего хэша на размер массива -> например получилась 3 ->
    //3 ячейка(корзина/bucket) массива готова -> засовываем в 3 бакет значение.
    //Если хэш коды совпадают, то выходит коллизия -> создается ещё 1 нода и выходит LinkedList и дальше итерируемся с помощью метода equals
    //Когда массив расширяется, то хэш коды перевычисляются
    //Коллизия - у разных ключей совпадает индекс бакета в итоге преобразований
    //Следовательно в ArrayList LinkedList ключ - это индекс, но он integer, и мы ничего с ним сделать не можем. А в Map-ах
    //мы можем сами регулировать тип ключа, который мы хотим
    //Структура данных дерево: эффективность O(log2(n))
    //Бинарное дерево - у 1 родителя 2 потомка
    //Каждый узел дерева - нода. Он хранит ссылку на 2 других узла и объект
    //В джава если мы используем деревья, то обязательно используется Comparator
    //Дерево само круто использовать для поиска
    //Дерево не работает с null
    //HashTable - старая потокобезопасная HashMap
    //FIXME
    //TODO
}