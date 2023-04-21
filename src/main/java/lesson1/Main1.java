package lesson1;

public class Main1 {


    public static void main(String[] args) {

        Team team = new Team();
        System.out.println("Приветствуем участников соревнования!\n" + team);
        Course c = new Course();
        System.out.println("Полоса препятствия, которую им предстоит преодолеть состоит из \n" + c);

        c.overcome(team);
        team.showResults();
    }
}
