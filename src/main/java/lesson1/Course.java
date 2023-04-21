package lesson1;

import java.util.Arrays;

public class Course {
    private String nameCourse;

    Pool pool = new Pool("Бассейн", 300);
    RopeLadder ropeLadder = new RopeLadder("Веревочная лестница", 400);
    Wall wall = new Wall("Стена", 200);
    Maze maze = new Maze("Лабиринт", 100);


    CourseElement[] courseElements = new CourseElement[]{pool, ropeLadder, wall, maze};

    public Course() {
        nameCourse = "Полоса";
        pool = pool;
        ropeLadder = ropeLadder;
        wall = wall;
        maze = maze;
    }

    public void overcome(Team team) {
        for (Player player : team.players) {
            for (CourseElement courseElement : courseElements) {
                courseElement.overcome(player);
                System.out.println("сейчас player"+player);
                System.out.println("сейчас courseElement"+courseElement);
            }

        }

    }

    @Override
    public String toString() {
        return "Course{" +
                "nameCourse='" + nameCourse + '\'' +
                ", pool=" + pool +
                ", ropeLadder=" + ropeLadder +
                ", wall=" + wall +
                ", maze=" + maze +
                ", courseElements=" + Arrays.toString(courseElements) +
                '}';
    }
}