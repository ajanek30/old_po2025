import java.util.ArrayList;
import java.util.Scanner;

public class LottoArrayList
{
    private static final int MIN = 1;
    private static final int MAX = 49;
    Scanner scanner = new Scanner(System.in);
    private int elementyWTablicy;
    protected int liczbaTrafien = 0;
    protected ArrayList<Integer> wylosowaneLiczby = new ArrayList<>();
    protected ArrayList<Integer> wpisaneLiczby = new ArrayList<>();


    public void wpiszLiczby()
    {
        elementyWTablicy = 0;

        for(int i = 0 ; i < 6 ; i++)
        {
            if(scanner.hasNextInt())
            {
                wpisaneLiczby.add(scanner.nextInt());
                elementyWTablicy++;
            }
            else if(elementyWTablicy == 6)
            {
                break;
            }
        }
        System.out.println(wpisaneLiczby);

    }

    public void wylosuj()
    {
        elementyWTablicy = 0;
        wylosowaneLiczby.trimToSize();
        wylosowaneLiczby.clear();
        while(elementyWTablicy < 6)
        {
            int num = (MIN +  (int)(Math.random() * ((MAX-MIN) + 1)));
            if(!wylosowaneLiczby.contains(num))
            {
                wylosowaneLiczby.add(num);
                elementyWTablicy++;
            }
        }
        System.out.println(wylosowaneLiczby);
    }
    public void policzTrafienia()
    {

        elementyWTablicy = 0;
        while(elementyWTablicy < wylosowaneLiczby.size())
        {
            if(wylosowaneLiczby.contains(wpisaneLiczby.get(elementyWTablicy)))
            {
                liczbaTrafien++;
            }
            elementyWTablicy++;
            elementyWTablicy++.
        }
        System.out.println("Liczba trafień: " + liczbaTrafien);
    }



}
