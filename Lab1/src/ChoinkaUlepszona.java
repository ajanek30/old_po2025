public class ChoinkaUlepszona {

public static void main(String[] args)
{
    int n = 5;
    for(int i = 0 ; i < n ; i++)
    {

        for(int k = n - 1 ; k > i ; k--)
        {
            System.out.print(" ");
        }
        for(int j = 0 ; j <= (2*i) ; j++)
        {
            System.out.print("*");
        }
        for(int l = n - 1 ; l > i ; l--)
        {
            System.out.print(" ");
        }
        System.out.println();
    }
  }
}
