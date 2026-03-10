package Animals;

public class Snake extends Animal
{
    public Snake()
        {
            legs = 0;

        }
    @Override
    public String getDescription()
    {
        return "Snejku2115";
    }
    @Override
    public void makeSound()
    {
        System.out.println("Dzwiek weza");
    }
}
