import java.lang.reflect.Array;
import java.util.ArrayList;



public class MainLottoArrayList
    {
        public static void main(String[] args)
        {
            LottoArrayList obiekt1 = new LottoArrayList();
            int liczbaLosowan = 0;

            obiekt1.wpiszLiczby();
            //odkomentuj by policzyc ilosc trafien
//            obiekt1.wylosuj();
//            obiekt1.policzTrafienia();
            long startTime = System.currentTimeMillis();
            while(!(obiekt1.wylosowaneLiczby.containsAll(obiekt1.wpisaneLiczby)))
            {
                obiekt1.wylosuj();
                liczbaLosowan++;
            }
            System.out.println("Wygrales po : " + liczbaLosowan + " próbach!");
            long endTime = System.currentTimeMillis();
            System.out.println("Losowanie zajęło : " + (endTime - startTime) + "ms");
        }
    }