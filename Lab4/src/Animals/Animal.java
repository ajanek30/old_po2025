package Animals;

public abstract class Animal
{
    String name;
    public int legs;
    public int getLegs()
    {
        return this.legs;
    }

    public Animal(String name, int legs) {
        this.name = name;
        this.legs = legs;
    }

    protected abstract String getDescription();
    public void makeSound()
    {

    }
}
