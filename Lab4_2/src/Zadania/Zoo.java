package Zadania;

import Animals.Animal;
import Animals.Dog;
import Animals.Parrot;
import Animals.Snake;

import java.util.Random;

public class Zoo
{
    public int sumaNog = 0;
    Animal[] animalsTab = new Animal[100];

    public void wypelnijTablice()
    {
        Random rand = new Random();
        for(int i = 0 ; i < animalsTab.length ; i++)
        {

            int opcja = rand.nextInt(3);
            switch(opcja)
            {
                case 0:
                    animalsTab[i] = new Dog();
                    animalsTab[i].getDescription();
                    animalsTab[i].makeSound();
                    break;
                case 1:
                    animalsTab[i] = new Parrot();
                    animalsTab[i].getDescription();
                    animalsTab[i].makeSound();
                    break;
                case 2:
                    animalsTab[i] = new Snake();
                    animalsTab[i].getDescription();
                    animalsTab[i].makeSound();
                    break;
                default:
                    System.out.println("Cos poszlo nie tak");
                    break;
            }
            animalsTab[i].getLegs();
        }

    }
    public int policzNogi()
    {
        for(int i = 0 ; i < animalsTab.length ; i++)
        {
            sumaNog += animalsTab[i].legs;
        }
        return sumaNog;
    }


}


