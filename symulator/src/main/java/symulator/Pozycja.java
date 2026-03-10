package symulator;

public class Pozycja
{
    private double x;
    private double y;

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //gettery
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    //settery
    public void setX(double x)
    {
        this.x= x;
    }
    public void setY(double y)
    {
        this.y = y;
    }

}
