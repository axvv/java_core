package lesson3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        задача 1

        Array<Integer> integerArray = new Array<>(1, 2, 56, 444, 6363);
        Array<String> stringArray = new Array<>("djvj", "jdjjdj", "zxcc", "ppppp", "qqqqq");
//        числовой массив
        System.out.println(integerArray);
        swap(integerArray.getArray(),0,4);
        System.out.println(integerArray);



//        строковый массив
        System.out.println(stringArray);
        swap(stringArray.getArray(),0,4);
        System.out.println(stringArray);


        //    задача про ящики с фруктами
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();
        orangeBox1.addFruit(new Orange(1));
        orangeBox1.addFruit(new Orange(1));
        orangeBox1.addFruit(new Orange(1));
        orangeBox1.addFruit(new Orange(1));
        orangeBox1.addFruit(new Orange(1));
        System.out.println( orangeBox1.getWeight()+" вес апельсинов");
        Box<Apple>appleBox1 = new Box<>();
        Box<Apple>appleBox2 = new Box<>();
        appleBox1.addFruit(new Apple(1));
        appleBox1.addFruit(new Apple(1));
        appleBox1.addFruit(new Apple(1));
        appleBox1.addFruit(new Apple(1));
        appleBox1.addFruit(new Apple(1));
        System.out.println( appleBox1.getFruitList()+" вес яблок");

        System.out.println(appleBox1.getWeight()+" вес яблок");
//        appleBox2.pourOver(orangeBox1); - не компилируется из-за разных типов данных коллекций
        appleBox2.pourOver(appleBox1);
        System.out.println(orangeBox1.getWeight()+" вес апельсинов");
        orangeBox2.pourOver(orangeBox1);
        //Сравним коробки
        System.out.println(appleBox1.isEqualBoxes(orangeBox1));
//        System.out.println(appleBox2.isEqualBoxes(orangeBox2));

    }

    public static void swap(Object[] array, int index1, int index2) { // меняет местами элементы в массиве
        Object tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

}