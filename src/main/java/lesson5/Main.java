package lesson5;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        AppData appData = new AppData();
//        создание файла из массива заголовок и массива данных
        appData.save("homework.csv");
//        создание массивов заголовок и массива данных из файла
        appData.load("homework.csv");
//      массив заголовок
        System.out.println(Arrays.toString(appData.getHeader())+"массив заголовок");
//      массив данных
        System.out.println(Arrays.deepToString(appData.getData())+"массив данных");
    }
}
