package lesson1;

public class Player implements CanJump,CanRun,CanPullUp,CanSwim {
    private String namePlayer;
    private int age;
    private int storedEnergy;
    private int number;


    public Player(String namePlayer, int age, int storedEnergy, int number) {
        this.namePlayer = namePlayer;
        setAge(age);
        this.storedEnergy = storedEnergy;
        this.number = number;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public double getStoredEnergy() {
        return storedEnergy;
    }

    public void setStoredEnergy(int storedEnergy) {
        this.storedEnergy = storedEnergy;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        if (age <= 2 || age > 65) {
            System.out.println("По правилам наших соревнований, мы не можем зарегистрировать юных и пожилых участников!");
        } else {
            this.age = age;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public String toString() {
        return "\nPlayer{" +
                "number='" + number + '\'' +
                "namePlayer='" + namePlayer + '\'' +
                "age='" + age + '\'' +
                ", storedEnergy=" + storedEnergy +
                '}';
    }


    @Override
    public void jump(int energyCosts) {

    }

    @Override
    public void pullUpp(int energyCosts) {

    }

    @Override
    public void run(int energyCosts) {

    }

    @Override
    public void swim(int energyCosts) {

    }
}
