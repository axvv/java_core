package lesson1;

import java.util.Arrays;

public class Team {
    private String nameTeam;
    //    private Player[] players;
    Man man = new Man("Евгений", 20, 1000, 1);
    Kangaroo kangaroo = new Kangaroo("Филомена", 5, 1100, 2);
    Kid kid = new Kid("Олежка", 10, 1000, 3);
    Dog dog = new Dog("Мальчик", 3, 400, 4);
    Player[] players = new Player[]{man, kangaroo, kid, dog};

    public Team() {
        nameTeam = "Dreamteam";
        players = players;
    }

    public void showResults() {
        System.out.printf("%s info:\n", nameTeam);
        for (Player player : players) {
            System.out.println(player.getNumber()+"  "+player.getNamePlayer()+"  "+player.getStoredEnergy()+" баллов");
        }
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public Man getMan() {
        return man;
    }

    public Kangaroo getKangaroo() {
        return kangaroo;
    }

    public Kid getKid() {
        return kid;
    }

    public Dog getDog() {
        return dog;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "nameTeam='" + nameTeam + '\'' +
                ", players=" + Arrays.toString(players) +
                '}';
    }
}




