package Animals;

public class Parrot extends Animal
{
    public Parrot()
    {
        //name = "Marek";
        legs = 2;

    }
    @Override
    public void makeSound()
    {
        System.out.println("Dzwiek papugi");
    }

    @Override
    public String getDescription()
    {
        return "To jest papuga kra kra!";


    }

}
