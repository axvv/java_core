package lesson9;

public class CourseUniversity implements Course{
    private String nameCourse;


    public CourseUniversity(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }



    public String toString() {
        return nameCourse ;
    }
}
