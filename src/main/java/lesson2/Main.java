package lesson2;

public class Main {


    public static void main(String[] args) {
        String[][] dimensialArray = {{"10", "20", "30", "40"}, {"10", "20", "30", "40"}, {"10", "20", "30", "40"}, {"10", "20", "30", "40"}};

        String[][] dimensialArrayWrongSize = {{"30"}, {"10", "20", "30"}, {"10", "20", "30"}, {"10", "20"}, {"10", "20", "30"}};
        String[][] dimensialArrayWrongData = {{"80", "lk", "30", "40"}, {"10", "20", "1", "40"}, {"6**", "20", "9", "40"}, {"10", "20", "30", "40"}};
        try {
            System.out.println(SumArrayElements.sumArrayElements(dimensialArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(SumArrayElements.sumArrayElements(dimensialArrayWrongSize));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(SumArrayElements.sumArrayElements(dimensialArrayWrongData));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}


