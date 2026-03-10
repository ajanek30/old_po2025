package Animals;

public class Parrot extends Animal
{
    {legs = 4;}



    public Parrot()
    {
        super("test", 4);
    }
    @Override
    public String getDescription() {
        return "Parrot";
    }
    @Override
    public void makeSound()
    {
        System.out.println(getDescription());
    }
}
