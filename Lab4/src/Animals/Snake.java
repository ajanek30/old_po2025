package Animals;

public class Snake extends Animal
{
    public Snake()
    {
        super("Adam",3);
    }
//    public Snake()
//    {
//        this.legs = 4;
//    }
    @Override
    public String getDescription() {
        return "Snake";
    }
    @Override
    public void makeSound()
    {
        System.out.println(getDescription());
    }
}
