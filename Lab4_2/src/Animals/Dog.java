package Animals;

public class Dog extends Animal
{
    public Dog()
    {
        legs = 4;

    }
    @Override
    public String getDescription()
    {
        return "Psiarz";
    }
    @Override
    public void makeSound()
    {
        System.out.println("Dzwiek psa");
    }
}
