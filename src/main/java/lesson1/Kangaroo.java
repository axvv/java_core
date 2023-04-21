package lesson1;

public class Kangaroo extends Player implements CanJump,CanRun,CanPullUp,CanSwim{

    public Kangaroo(String namePlayer, int age, int storedEnergy,int number) {
        super(namePlayer, age, 1100, 2);
    }


    @Override
    public void jump(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");

        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я кенгуру, я перепрыгнул стену!");
        }

    }

    @Override
    public void run(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");

        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я кенгуру, я пробежал лабиринт!");
        }
    }

    @Override
    public void pullUpp(int energyCosts) {
        System.out.println("Я кенгуру и не умею лазить по веревочной лестнице");
    }

    @Override
    public void swim(int energyCosts) {
        System.out.println("Я кенгуру и не умею плавать");
    }
}
