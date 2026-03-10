package symulator;

public class Komponent {
    private String nazwa;
    private int waga;
    private int cena;
    private String producent;
    private String model;

    public Komponent(String producent, String model, String nazwa, int waga, int cena) {
        this.producent = producent;
        this.model = model;
        this.nazwa = nazwa;
        this.waga = waga;
        this.cena = cena;
    }

    //gettery

    public String getNazwa() {
        return nazwa;
    }

    public int getWaga() {
        return waga;
    }

    public int getCena() {
        return cena;
    }

    public String getProducent() {
        return producent;
    }

    public String getModel() {
        return model;
    }

}
