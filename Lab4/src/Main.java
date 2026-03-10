import Animals.Animal;
import Animals.Snake;
import Zadania.Zoo;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {
        Animal animal = new Animal("Zoo", 1) {
            @Override
            protected String getDescription() {
                return "";
            }
        };
        Zoo obiekt = new Zoo();
        //boolean checker = animal instanceof Snake;
        obiekt.fulfillArray();
        int num = obiekt.countLegs();
        System.out.println(num);


    }
}