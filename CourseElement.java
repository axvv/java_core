package lesson1;

public class CourseElement {
    private String courseElementName;
    private int energyCosts;

    public CourseElement(String courseElementName, int energyCosts) {
        this.courseElementName = courseElementName;
        this.energyCosts = energyCosts;
    }



    public String getCourseElementName() {
        return courseElementName;
    }

    public void setCourseElementName(String courseElementName) {
        this.courseElementName = courseElementName;
    }

    public int getEnergyCosts() {
        return energyCosts;
    }

    public void setEnergyCosts(int energyCosts) {
        this.energyCosts = energyCosts;
    }




    public void overcome(Player player) {

    }

    @Override
    public String toString() {
        return "CourseElement{" +
                "courseElementName='" + courseElementName + '\'' +
                ", energyCosts=" + energyCosts +
                '}';
    }
}
