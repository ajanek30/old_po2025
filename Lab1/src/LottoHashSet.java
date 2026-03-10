import java.util.HashSet;

public class LottoHashSet
{
    private HashSet<Integer> hashTab = new HashSet<>(6);
    private int num;

    public void wypelnijTablice()
    {
        do {
            num = 1 + (int) (Math.random() * (49));
            if(hashTab.contains(num))
            {
                continue;
            }
            else
            {
                hashTab.add(num);
            }

        }while(hashTab.size() < 6);

        System.out.println(hashTab);
    }
    public static void main(String[] args)
    {

        LottoHashSet lotto = new LottoHashSet();
        lotto.wypelnijTablice();


    }


}
