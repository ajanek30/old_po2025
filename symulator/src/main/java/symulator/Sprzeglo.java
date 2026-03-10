package symulator;

import javax.swing.*;

public class Sprzeglo extends Komponent {
    private boolean stanSprzegla;
    private Silnik silnik;

    public Sprzeglo(Silnik silnik,String producent, String model, String nazwa, int waga, int cena) {
        super(producent, model, nazwa, waga, cena);
        this.stanSprzegla = false;
        this.silnik = silnik;
    }

    //gettery

    public boolean isStanSprzegla()
    {
        return stanSprzegla;
    }

    //settery
    //metody funkcjonalne

    public void wcisnij() {
        if (silnik.getStanWlaczenia()) {
            stanSprzegla = true;
            System.out.println("Sprzęgło wciśnięte");
        } else {
            System.out.println("Nie można wcisnąć sprzęgła – komponent wyłączony");
        }
    }

    public void zwolnij() {
        if (silnik.getStanWlaczenia()) {
            stanSprzegla = false;
            System.out.println("Sprzęgło zwolnione");
        }
        else
        {
            System.out.println("Nie można wcisnąć sprzęgła – komponent wyłączony");
        }
    }


}
