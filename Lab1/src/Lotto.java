public class Lotto
{

    private int[] tablicaLiczb = new int[6];
    private int min = 1;
    private int max = 49;
    private boolean jestDuplikat = false;

    public void uzupelnijTablice()
    {
        for(int i = 0 ; i < tablicaLiczb.length ; i++)
        {
            int num;

            do
            {
                num = min + (int)(Math.random() * ((max-min) + 1));
                tablicaLiczb[i] = num;



            }while(jestDuplikat);
            System.out.println(tablicaLiczb[i]);
        }
    }

    public static void main(String[] args)
    {
        Lotto lotto = new Lotto();
        lotto.uzupelnijTablice();


    }
}
