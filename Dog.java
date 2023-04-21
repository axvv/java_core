package lesson1;

public class Dog extends Player implements CanSwim, CanJump, CanRun,CanPullUp {


    public Dog(String namePlayer, int age, int storedEnergy, int number) {
        super(namePlayer, age, 600, 4);
    }


    @Override
    public void jump(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");

        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я собака, я перепрыгнула стену!");
        }
    }


    @Override
    public void run(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");

        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я собака, я пробежала лабиринт!");
        }
    }


    @Override
    public void swim(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");

        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я собака, я переплыла бассейн!");
        }

    }

    @Override
    public void pullUpp(int energyCosts) {
        System.out.println("Я собака и не умею лазить по веревочной лестнице");
    }
}

