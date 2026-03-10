package symulator;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;


public class Samochod implements Runnable {
    private String nrRejest;
    private String model;
    private int predkoscMax;
    private int predkosc;
    private Pozycja aktualnaPozycja;
    private Pozycja cel;
    //pola funkcjonalne
    private Thread watek;
    private Silnik silnik;
    private SkrzyniaBiegow skrzynia;
    private Sprzeglo sprzeglo;
    private int waga;
    private ImageView carImageView;
    private List<Listener> listeners = new ArrayList<>();


    public Samochod(String nrRejest, String model,int predkoscMax, int waga, Pozycja aktualnaPozycja, Silnik silnik, SkrzyniaBiegow skrzynia, Sprzeglo sprzeglo) {
        this.nrRejest = nrRejest;
        this.model = model;
        this.predkoscMax = predkoscMax;
        this.aktualnaPozycja = aktualnaPozycja;
        this.silnik = silnik;
        this.skrzynia = skrzynia;
        this.waga = waga;
        this.sprzeglo = sprzeglo;

        //watek
        watek = new Thread(this);
        watek.setDaemon(true);
        watek.start();

    }

    //gettery
    public String getNrRejest() {
        return nrRejest;
    }
    public Sprzeglo getSprzeglo()
    {
        return sprzeglo;
    }
    public int getWaga()
    {
        return waga;
    }

    public String getModel() {
        return model;
    }

    public int getPredkosc()
    {
        return predkosc;
    }

    public Pozycja getAktualnaPozycja() {
        return aktualnaPozycja;
    }

    public Silnik getSilnik() {
        return silnik;
    }

    public SkrzyniaBiegow getSkrzynia() {
        return skrzynia;
    }

    public ImageView getCarImageView() {
        return carImageView;
    }

    //settery

    public void setCarImageView(ImageView carImageView)
    {
        this.carImageView = carImageView;
    }

    //metody funkcjonalne

    //listener
    public void addListener(Listener listener)
    {
        listeners.add(listener);
    }
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }
    private void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();

        }
    }


    @Override
    public String toString() {
        return model + " (" + nrRejest + ")";
    }

    public void jedzDo(Pozycja cel) {
        this.cel = cel;
        notifyListeners();
    }

    public void przyspiesz(int przyrostObrotow)
    {
        silnik.zwiekszObroty(przyrostObrotow);
        aktualizujPredkosc();
        notifyListeners();
    }
    public void zwolnij(int przyrostObrotow)
    {
        silnik.zmniejszObroty(przyrostObrotow);
        aktualizujPredkosc();
        notifyListeners();
    }

    public void aktualizujPredkosc()
    {
        int obrotyMinimalne = 800;
        int przyrostObrotowDoMax = 5200; // czyli 800 -> 6000 obrotów - to max
        int maxBieg = 6;
        int aktualnyBieg = skrzynia.getAktualnyBieg();
        int obroty = silnik.getObroty();

        // ograniczamy obroty do minimalnych
        if (obroty < obrotyMinimalne) obroty = obrotyMinimalne;

        //wzor obliczajacy predkosc
        double skala = (double) (obroty - obrotyMinimalne) / przyrostObrotowDoMax;
        predkosc = (int) (skala * predkoscMax * aktualnyBieg / maxBieg);
        notifyListeners();
    }
    //imageview obrazek
    public void stworzCarImage() {
        ImageView carImg = new ImageView(new Image(
                getClass().getResource("/Anti.png").toExternalForm()
        ));
        carImg.setFitWidth(180);
        carImg.setFitHeight(110);
        this.carImageView = carImg;
        // Ustawiamy początkową pozycję
        carImg.setTranslateX(getAktualnaPozycja().getX());
        carImg.setTranslateY(getAktualnaPozycja().getY());
        notifyListeners();
    }

    //watek cd
    @Override
    public void run() {
        double deltat = 0.1;

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // jeśli predkosc = 0, auto stoi, ale cel może być ustawiony
            if (predkosc <= 0) continue;

            Pozycja lokalnyCel = cel; // kopiujemy cel na wypadek NullPointer
            if (lokalnyCel == null) continue; // nic do zrobienia

            double dx = lokalnyCel.getX() - aktualnaPozycja.getX();
            double dy = lokalnyCel.getY() - aktualnaPozycja.getY();
            double odleglosc = Math.sqrt(dx*dx + dy*dy);

            double krok = predkosc * deltat;
            if (krok > odleglosc) krok = odleglosc;

            if (odleglosc < 0.5) {
                aktualnaPozycja.setX(lokalnyCel.getX());
                aktualnaPozycja.setY(lokalnyCel.getY());
                cel = null; // osiągnęliśmy cel
                continue;
            }

            double vx = krok * dx / odleglosc;
            double vy = krok * dy / odleglosc;

            aktualnaPozycja.setX(aktualnaPozycja.getX() + vx);
            aktualnaPozycja.setY(aktualnaPozycja.getY() + vy);

            //do synchronizacji watku
            if (carImageView != null) {
                Platform.runLater(() -> {
                    carImageView.setTranslateX(aktualnaPozycja.getX());
                    carImageView.setTranslateY(aktualnaPozycja.getY());
                });
                notifyListeners();
            }
        }
    }

    public void wlacz()
    {
        silnik.uruchom();
    }
    public void wylacz()
    {
        silnik.zatrzymaj();
        predkosc = 0;  // zatrzymujemy auto natychmiast
        cel = null;    // opcjonalnie anulujemy cel
        notifyListeners();
    }




}

