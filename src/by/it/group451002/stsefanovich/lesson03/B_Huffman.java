package by.it.group451002.stsefanovich.lesson03;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Lesson 3. B_Huffman.
// Восстановите строку по её коду и беспрефиксному коду символов.

// В первой строке входного файла заданы два целых числа
// kk и ll через пробел — количество различных букв, встречающихся в строке,
// и размер получившейся закодированной строки, соответственно.
//
// В следующих kk строках записаны коды букв в формате "letter: code".
// Ни один код не является префиксом другого.
// Буквы могут быть перечислены в любом порядке.
// В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
// каждая из этих букв встречается в строке хотя бы один раз.
// Наконец, в последней строке записана закодированная строка.
// Исходная строка и коды всех букв непусты.
// Заданный код таков, что закодированная строка имеет минимальный возможный размер.
//
//        Sample Input 1:
//        1 1
//        a: 0
//        0

//        Sample Output 1:
//        a


//        Sample Input 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

//        Sample Output 2:
//        abacabad

public class B_Huffman {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = B_Huffman.class.getResourceAsStream("dataB.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(inputStream);
        System.out.println(result);
    }

    String decode(InputStream inputStream) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);

        // Чтение количества символов и длины закодированной строки
        int count = scanner.nextInt(); // Количество уникальных символов
        int length = scanner.nextInt(); // Длина закодированной строки
        scanner.nextLine(); // Пропустить оставшийся перенос строки

        // Чтение символов и их кодов
        Map<String, Character> codeToChar = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String[] line = scanner.nextLine().split(": ");
            char character = line[0].charAt(0); // Символ
            String code = line[1]; // Код
            codeToChar.put(code, character);
        }

        // Чтение закодированной строки
        String encodedString = scanner.nextLine();

        // Декодирование строки
        StringBuilder tempCode = new StringBuilder();
        for (char c : encodedString.toCharArray()) {
            tempCode.append(c);
            if (codeToChar.containsKey(tempCode.toString())) {
                result.append(codeToChar.get(tempCode.toString()));
                tempCode.setLength(0); // Очистить временный код
            }
        }

        return result.toString();
    }



}