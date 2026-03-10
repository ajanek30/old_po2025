package Animals;

public class Dog extends Animal
{
    //public int legs = 4;
    public Dog()
    {
        super("Borys",4);
        //this.legs = 4;
    }


    @Override
    public String getDescription() {
        return "Dog";
    }
    @Override
    public void makeSound()
    {
        System.out.println(getDescription());
    }

}
