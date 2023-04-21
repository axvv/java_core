package lesson1;

public class Kid extends Player implements CanJump,CanRun,CanPullUp,CanSwim {
    public Kid(String namePlayer, int age, int storedEnergy, int number) {
        super(namePlayer, age, 800, 3);
    }

    @Override
    public void jump(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");
        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я ребёнок, я перепрыгнул стену!");
        }

    }


    @Override
    public void pullUpp(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");

        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я ребёнок, я прополз на руках по веревочной лестнице!");
        }
    }


    @Override
    public void run(int energyCosts) {
        if (getStoredEnergy() < energyCosts) {
            System.out.println("Не получилось!");

        } else {
            setStoredEnergy((int) (getStoredEnergy() - energyCosts));
            System.out.println("Я ребёнок, я пробежал лабиринт!");
        }
    }

    @Override
    public void swim(int energyCosts) {
        System.out.println("Я ребёнок, я не умею пока плавать!");
    }
}

