package symulator;

public class SkrzyniaBiegow extends Komponent {
    private int aktualnyBieg;
    private int iloscBiegow;
    private int aktualnePrzelozenie;
    private Sprzeglo sprzeglo;

    public SkrzyniaBiegow(String producent, String model, int aktualnyBieg, int iloscBiegow, int aktualnePrzelozenie,
                          String nazwa, int waga, int cena, Sprzeglo sprzeglo) {
        super(producent, model, nazwa, waga, cena);
        this.aktualnyBieg = aktualnyBieg;
        this.iloscBiegow = iloscBiegow;
        this.aktualnePrzelozenie = aktualnePrzelozenie;
        this.sprzeglo = sprzeglo;
    }

    //gettery

    public int getAktualnyBieg() {
        return aktualnyBieg;
    }

    public int getAktualnePrzelozenie() {
        return aktualnePrzelozenie;
    }
    public int getIloscBiegow()
    {
        return iloscBiegow;
    }

    //settery
    //metody funkcjonalne

    public void zwiekszBieg() {
        if (!sprzeglo.isStanSprzegla()) {
            System.out.println("Nie można zmienić biegu – sprzęgło nie wciśnięte");
            return;
        }
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg++;
            System.out.println("Bieg zwiększony: " + aktualnyBieg);
        } else {
            System.out.println("Najwyższy bieg już osiągnięty");
        }
    }

    public void zmniejszBieg() {
        if (!sprzeglo.isStanSprzegla()) {
            System.out.println("Nie można zmienić biegu – sprzęgło nie wciśnięte");
            return;
        }
        if (aktualnyBieg > 1) {
            aktualnyBieg--;
            System.out.println("Bieg zmniejszony: " + aktualnyBieg);
        } else {
            System.out.println("Najniższy bieg już osiągnięty");
        }
    }
}
