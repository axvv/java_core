package lesson1;

public class Man extends Player implements CanSwim, CanJump, CanPullUp, CanRun {


    public Man(String namePlayer, int age, int storedEnergy, int number) {
        super(namePlayer, age, 2000, 1);
    }





    public void jump(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");

        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я мужчина, я перепрыгнул стену!");
        }

    }


    public void pullUpp(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");

        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я мужчина, я прополз на руках по веревочной лестнице!");
        }

    }


    public void run(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");

        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я мужчина, я пробежал лабиринт!");
        }

    }


    public void swim(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");

        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я мужчина, я переплыл бассейн!");
        }

    }
}

