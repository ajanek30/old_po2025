package Animals;

public abstract class Animal
{
    protected String name;
    public int legs;
    public abstract String getDescription();
    public int getLegs()
    {
        return legs;
    }
    public void makeSound()
    {

    }

}
