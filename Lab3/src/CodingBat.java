public class CodingBat
{
    public String startOz(String str)
    {
        String result = "";

        if (str.length() >= 1 && str.charAt(0)=='o') {
            result = result + str.charAt(0);
           // result.replace
        }

        if (str.length() >= 2 && str.charAt(1)=='z') {
            result = result + str.charAt(1);
        }

        return result;

    }
    public String missingChar(String str, int n)
    {
        String result = "";
        if(n <= str.length() - 1 && n >= 0 )
        {


            result = str.substring(0,n) + str.substring(n+1,str.length());

            return result;

        }
        return "";

    }
    public int countEvens(int[] nums) {
        int numOfEven = 0;
        for(int i = 0 ; i < nums.length ; i++)
        {
            if(nums[i] % 2 == 0)
            {
                numOfEven += 1;
            }

        }
        return numOfEven;
    }
    public String lastChars(String a, String b)
    {
        String result = "";
        if(a.length() == 0 && b.length() == 0)
        {
            result = "@@";
        }
        else if(a.length() == 0)
        {
            result = "@" + b.substring(b.length()-1,b.length());
        }
        else if(b.length() == 0)
        {
            result = a.substring(0,1) + "@";
        }
        else
        {
            result = a.substring(0,1) + b.substring(b.length()-1,b.length());
        }


        return result;

    }


}
