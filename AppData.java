package lesson5;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AppData {
    private String[] header = new String[]{"Price", "Speed", "Power"};
    private Integer[][] data = new Integer[][]{{10000, 250, 120}, {12000, 280, 150}, {15000, 280, 180}};

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public Integer[][] getData() {
        return data;
    }

    public void setData(Integer[][] data) {
        this.data = data;
    }


    public void save(String filename) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
//            записываем заголовок из строчного массива header
            bufferedWriter.write(rowToString(header));
//            не закрывая канал, в цикле записываем двумерный массив данных
            for (Integer[] row : data) {
                bufferedWriter.write(rowToString(row));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //принимаем массив обобщенного типа T и возвращаем строку,если это не последний элемент,то добавляется ";"
    private <T> String rowToString(T[] row) {
        String result = "";
        for (int i = 0; i < row.length; i++) {
            result = result + row[i].toString();
            if (i != row.length - 1) {
                result += ";";
            }
        }
//        перенос на следующую строку
        result += "\n";
//        возвращаем строку, приведенную к формату для csv-файла: Value 1;Value 2;Value 3
        return result;
    }

    public void load(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
// Чтение первой строки текстового файла и ее разбиение на подстроки, используя символ ";" в качестве разделителя.
// Полученные результаты сохраняются в массив header
            header = bufferedReader.readLine().split(";");
            ArrayList<Integer[]> dataList = new ArrayList<>();
            String tempString = null;
            while ((tempString = bufferedReader.readLine()) != null) {
         /*  чтение строки из файла и добавление ее в ArrayList dataList.
         используется метод stringToDataRow, который преобразует строку в массив Integer.*/
                dataList.add(stringToDataRow(tempString));
            }
// Преобразование dataList в массив двумерного массива Integer с помощью метода toArray, и сохранение этого массива в data
            data = dataList.toArray(new Integer[][]{{}});

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer[] stringToDataRow(String str) {
        String[] strings = str.split(";");

        Integer[] integers = new Integer[strings.length];
        for (int i = 0; i < strings.length; i++) {
            integers[i] = Integer.parseInt(strings[i]);
        }
        return integers;
    }
}