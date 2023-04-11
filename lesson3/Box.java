package lesson3;

public class Box<T extends Fruit> {
    private T contentsOfBox;

    public Box(T contentsOfBox) {
        this.contentsOfBox = contentsOfBox;
    }

    public T getContentsOfBox() {
        return contentsOfBox;
    }

    public void setContentsOfBox(T contentsOfBox) {
        this.contentsOfBox = contentsOfBox;
    }

    @Override
    public String toString() {
        return "Box{" +
                "contentsOfBox=" + contentsOfBox +
                '}';
    }

}
