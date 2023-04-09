package lesson2;

public class SumArrayElements {
    public static int sumArrayElements(String[][] stringArray) throws MyArraySizeException, MyArrayDataException {
        int sumoOfArrayElements = 0;
        for (int i = 0; i < stringArray.length; i++) {
            if (4 != stringArray.length) {
                throw new MyArraySizeException("двумерный строковый массив должен быть размером 4х4!");

            }
            for (int j = 0; j < stringArray.length; j++) {
                if (4 != stringArray.length) {
                    throw new MyArraySizeException("двумерный строковый массив должен быть размером 4х4!");
                }
                try {
                    sumoOfArrayElements += Integer.parseInt(stringArray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sumoOfArrayElements;
    }
}

