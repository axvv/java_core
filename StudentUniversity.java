package lesson9;

import java.util.List;

public class StudentUniversity implements Student {
    private String name;
    private List<Course> courses;

    public StudentUniversity(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "StudentUniversity{" +
                "name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }
}
