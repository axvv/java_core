package lesson3;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruitList = new ArrayList<>();

    public Box() {
    }

    public ArrayList<T> getFruitList() {
        return fruitList;
    }

    public void setFruitList(ArrayList<T> fruitList) {
        this.fruitList = fruitList;
    }

    public void addFruit(T fruit) {
        fruitList.add(fruit);
    }

    public float getWeight() {
        return fruitList.size() * fruitList.get(0).getWeight();
    }


    public void pourOver(Box<T> box) {

        box.getFruitList().addAll(fruitList);
        fruitList.clear();

//        System.out.println(fruitList.get(0));

           /*  if(fruitList.get(0)==null){
            fruitList.add(0, Box<T> 0);
        } не получается записать ноль в нулевую ячейку, чтобы не было ошибки с подсчетом веса*/
        System.out.println("Пересыпал в другую коробку");
        if (fruitList.isEmpty()) {
            System.out.println("Коробка пустая" + fruitList);
            System.out.println("Другая коробка с фруктами" + box.getFruitList());
        }
    }

    public boolean isEqualBoxes(Box<?> boxToCompare) {
        if (this.getWeight() == boxToCompare.getWeight()) {
            System.out.println("Коробки равны по весу");
            return this.getWeight() == boxToCompare.getWeight();
        } else
            System.out.println("Коробки не равны по весу");
        return this.getWeight() == boxToCompare.getWeight();
    }



    @Override
    public String toString() {
        return "Box{" +
                "fruitList=" + fruitList +
                '}';
    }
}
