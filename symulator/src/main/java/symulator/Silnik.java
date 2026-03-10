package symulator;

public class Silnik extends Komponent
{
    private int maxObroty;
    private int obroty;
    protected boolean stanWlaczenia;

    public Silnik(String producent,String model,int maxObroty,String nazwa,int waga,int cena) {
        super(producent,model,nazwa,waga,cena);
        this.maxObroty = maxObroty;
        stanWlaczenia = false;
        this.obroty = 0;
    }
    //gettery
    public boolean getStanWlaczenia()
    {
        return stanWlaczenia;
    }
    public int getObroty()
    {
        return this.obroty;
    }
    public int getMaxObroty()
    {
        return maxObroty;
    }
    //settery
    public void setStanWlaczenia(boolean stanWlaczenia)
    {
        this.stanWlaczenia = stanWlaczenia;
    }
    //metody funkcjonalne
    public void uruchom()
    {
        setStanWlaczenia(true);
        this.obroty = 800;
    }
    public void zatrzymaj()
    {
        setStanWlaczenia(false);
        obroty = 0;
    }
    public void zwiekszObroty(int przyrostObrotow) {
        if (!getStanWlaczenia()) return; // nic nie rób, jeśli silnik wyłączony

        obroty += przyrostObrotow;
        if (obroty > maxObroty) obroty = maxObroty; // ograniczenie do max

    }
    public void zmniejszObroty(int przyrostObrotow) {
        if (!getStanWlaczenia()) return;

        obroty -= przyrostObrotow;
        if (obroty < 800) obroty = 800; // minimalne obroty jałowe
    }
}

