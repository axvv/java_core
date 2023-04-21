package lesson1;

public class Maze extends CourseElement{
    public Maze(String courseElementName, int energyCosts) {
        super(courseElementName, energyCosts);
    }

    @Override
    public String toString() {
        return "Maze{}";
    }
}
