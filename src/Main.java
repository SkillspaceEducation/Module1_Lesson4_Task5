import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/*
1. Получить через Scanner размер тестового массива
2. Заполнить массив случайными целочисленными значениями
3. Написать метод, удаляющий из массива локальные максимумы.
4. Вывести на экран изначальный массив и массив после удаления локальных максимумов

Локальный максимум - элемент, который больше предыдущего и следующего.

Пример:
Изначальный массив: [5, 3, -10, 4, -4, 80, 20]
Поcле удаления локальных максимумов: [3, -10, -4, 20]
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Введите размер исходного массива");
        int arrayLen = scanner.nextInt();
        int[] arrayInit = new int[arrayLen];
        for (int i = 0; i < arrayLen; i++) {
            arrayInit[i] = random.nextInt(100);
        }
        System.out.println("Исхоный массив\n" + Arrays.toString(arrayInit));

        int[] array = new int[arrayInit.length];
        String result = getArray(arrayInit, array);
        System.out.println("Массив с удаленными локальными максимумами\n" + result);
    }

    private static String getArray(int[] arrayInit, int[] arrayChange) {
        int num = 0;
        for (int i = 0; i < arrayInit.length; i++) {
            if (i == 0) {                               // проверяем нулевой и первый индекс массива
                if (arrayInit[i] > arrayInit[i + 1]) {  // значение в нулевом индексе больше первого записываем в
                    arrayChange[num] = 0;                    // новый массив ноль
                    num++;                              // увеличиваем счетчик для записи в следующий индекс
                } else {
                    arrayChange[num] = arrayInit[i];        // значение в первом индексе больше нулевого записываем его в
                    // новый массив
                    num++;                             // увеличиваем счетчик для записи в следующий индекс
                }
            } else if (i != 0 && i < arrayInit.length - 2 && arrayChange[0] == 0) {     // проверям индексы от первого до предпоследнего
                if (arrayInit[i] > arrayInit[i - 1]   // если значение в текущем индексе больше предыдущего и
                        && arrayInit[i] > arrayInit[i + 1]) {    // последующего
                    arrayChange[num] = 0;                            // записываем ноль
                    num++;
                } else {
                    arrayChange[num] = arrayInit[i];                 // иначе записываем значние из этого индекса
                    num++;
                }
            } else if (i != 0 && i < arrayInit.length - 2 && arrayChange[0] == arrayInit[0]) {
                if (arrayInit[i + 1] > arrayInit[i + 2]    // если значение в текущем индексе больше предыдущего и
                        && arrayInit[i + 1] > arrayInit[i]) {    // последующего
                    arrayChange[num] = 0;                            // записываем ноль
                    num++;
                } else {
                    arrayChange[num] = arrayInit[i + 1];                 // иначе записываем значние из этого индекса
                    num++;
                }
            } else if (i == arrayInit.length - 1) {     // проверям значение в предпоследнем и последнем индексе
                if (arrayInit[i - 1] > arrayInit[i]) {  // если значение в предпоследнем индексе больше, чем в последнем
                    arrayChange[num] = arrayInit[i];    // записываем значение из последнего
                } else {
                    arrayChange[num] = 0; // иначе записываем 0
                }
            }
        }
        String result = Arrays.toString(arrayChange)
                .replace(" 0,", "")
                .replace("[0, ", "[")
                .replace(", 0", "");
        return result;
    }
}