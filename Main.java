package lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static CourseUniversity cultureOfSpeech = new CourseUniversity("Культура речи");
    static CourseUniversity basicsOfProgramming = new CourseUniversity("Основы программирования");
    static CourseUniversity finance = new CourseUniversity("Финансы");
    static CourseUniversity computerLiteracy = new CourseUniversity("Компьютерная грамотность");
    static CourseUniversity fundamentalsOfNeuralnetworks = new CourseUniversity("Основы нейросетей");
    static CourseUniversity physics = new CourseUniversity("Математические основы физики");
    static CourseUniversity graphicDesign = new CourseUniversity("Графический дизайн");

    public static void main(String[] args) {

        List<StudentUniversity> studentUniversityList = new ArrayList<>();
        studentUniversityList.add(new StudentUniversity("Барабанов Алексей", Arrays.asList(basicsOfProgramming,
                finance)));
        studentUniversityList.add(new StudentUniversity("Бараш Антон", Arrays.asList(basicsOfProgramming,
                computerLiteracy)));
        studentUniversityList.add(new StudentUniversity("Владау Алёна", Arrays.asList(fundamentalsOfNeuralnetworks,
                cultureOfSpeech)));
        studentUniversityList.add(new StudentUniversity("Катару Антон", Arrays.asList(basicsOfProgramming,
                computerLiteracy, physics, graphicDesign
        )));
        studentUniversityList.add(new StudentUniversity("Малютин Анатолий", Arrays.asList(basicsOfProgramming,fundamentalsOfNeuralnetworks,
                cultureOfSpeech
        )));
        studentUniversityList.add(new StudentUniversity("Малютина Аделаида", Arrays.asList(computerLiteracy,finance, cultureOfSpeech
        )));
        studentUniversityList.add(new StudentUniversity("Малышева Аглая", Arrays.asList(basicsOfProgramming,computerLiteracy,physics,graphicDesign, cultureOfSpeech
        )));
        System.out.println(getUniqueCourses(studentUniversityList));
        System.out.println(getThreeCurious(studentUniversityList));
        System.out.println(getStudentsOnCourse(studentUniversityList,computerLiteracy));
    }

    public static List<Course> getUniqueCourses(List<StudentUniversity> studentUniversityList) {
        return studentUniversityList.stream()
                .flatMap(s -> s.getAllCourses().stream())
                .distinct()
                .collect(Collectors.toList());

    }

    public static List<Student> getThreeCurious(List<StudentUniversity> studentUniversityList) {
        return studentUniversityList.stream()
                .sorted((s1, s2) -> s2.getAllCourses().size() - s1.getAllCourses().size())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List<String> getStudentsOnCourse(List<StudentUniversity> studentUniversityList, CourseUniversity selectedCourse) {
        return studentUniversityList.stream()
                .filter(p -> p.getAllCourses().contains(selectedCourse))
                .map(StudentUniversity::getName)
                .collect(Collectors.toList());
    }
}

