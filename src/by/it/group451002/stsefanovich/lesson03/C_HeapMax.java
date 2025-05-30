package by.it.group451002.stsefanovich.lesson03;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lesson 3. C_Heap.
// Задача: построить max-кучу = пирамиду = бинарное сбалансированное дерево на массиве.
// ВАЖНО! НЕЛЬЗЯ ИСПОЛЬЗОВАТЬ НИКАКИЕ КОЛЛЕКЦИИ, КРОМЕ ARRAYLIST (его можно, но только для массива)

//      Проверка проводится по данным файла
//      Первая строка входа содержит число операций 1 ≤ n ≤ 100000.
//      Каждая из последующих nn строк задают операцию одного из следующих двух типов:

//      Insert x, где 0 ≤ x ≤ 1000000000 — целое число;
//      ExtractMax.

//      Первая операция добавляет число x в очередь с приоритетами,
//      вторая — извлекает максимальное число и выводит его.

//      Sample Input:
//      6
//      Insert 200
//      Insert 10
//      ExtractMax
//      Insert 5
//      Insert 500
//      ExtractMax
//
//      Sample Output:
//      200
//      500


public class C_HeapMax {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_HeapMax.class.getResourceAsStream("dataC.txt");
        C_HeapMax instance = new C_HeapMax();
        System.out.println("MAX=" + instance.findMaxValue(stream));
    }

    //эта процедура читает данные из файла, ее можно не менять.
    Long findMaxValue(InputStream stream) {
        Long maxValue = 0L;
        MaxHeap heap = new MaxHeap();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(stream);
        Integer count = scanner.nextInt();
        for (int i = 0; i < count; ) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("extractMax")) {
                Long res = heap.extractMax();
                if (res != null && res > maxValue) maxValue = res;
                System.out.println();
                i++;
            }
            if (s.contains(" ")) {
                String[] p = s.split(" ");
                if (p[0].equalsIgnoreCase("insert"))
                    heap.insert(Long.parseLong(p[1]));
                i++;
                //System.out.println(heap); //debug
            }
        }
        return maxValue;
    }

    private class MaxHeap {
        private List<Long> heap = new ArrayList<>();

        int siftDown(int i) { // просеивание вниз
            while (2 * i + 1 < heap.size()) {
                int leftChild = 2 * i + 1;
                int rightChild = 2 * i + 2;
                int largest = leftChild;

                if (rightChild < heap.size() && heap.get(rightChild) > heap.get(leftChild)) {
                    largest = rightChild;
                }

                if (heap.get(i) >= heap.get(largest)) {
                    break;
                }

                // меняем местами
                swap(i, largest);
                i = largest;
            }
            return i;
        }

        int siftUp(int i) { // просеивание вверх
            while (i > 0) {
                int parent = (i - 1) / 2;
                if (heap.get(i) <= heap.get(parent)) {
                    break;
                }

                // меняем местами
                swap(i, parent);
                i = parent;
            }
            return i;
        }

        void insert(Long value) { // вставка
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        Long extractMax() { // извлечение максимума
            if (heap.isEmpty()) {
                return null;
            }
            Long result = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                siftDown(0);
            }
            return result;
        }

        private void swap(int i, int j) {
            Long temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }
}