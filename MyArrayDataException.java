package lesson2;

public class MyArrayDataException extends Exception {
    public MyArrayDataException(int i, int j) {
        super(String.format("в ячейке %d-%d лежит символ или текст вместо числа", i, j));

    }
}
