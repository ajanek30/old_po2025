package Zadania;

import Animals.Animal;
import Animals.Dog;
import Animals.Parrot;
import Animals.Snake;

import java.util.Random;


public class Zoo {
    Random rand = new Random();
    private Animal[] animals;
    private int result = 0;

    public Zoo() {
        animals = new Animal[100];
    }

    public void fulfillArray() {
        for (int i = 0; i < animals.length; i++) {
            int choice = rand.nextInt(3);

            switch (choice) {
                case 0:
                    animals[i] = new Dog();
                    animals[i].makeSound();
                    break;
                case 1:
                    animals[i] = new Snake();
                    animals[i].makeSound();
                    break;
                case 2:
                    animals[i] = new Parrot();
                    animals[i].makeSound();
                    break;
            }
        }

    }
    public int countLegs()
    {
        for(int i = 0 ; i < animals.length ; i++)
        {
            result += animals[i].getLegs();
        }
        return result;
    }

}
