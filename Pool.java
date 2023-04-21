package lesson1;

public class Pool extends CourseElement{
    public Pool(String courseElementName, int energyCosts) {
        super(courseElementName, energyCosts);
    }

    @Override
    public String toString() {
        return "Pool{}";
    }
}
