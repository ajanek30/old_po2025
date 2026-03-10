package org.example.samochod;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import symulator.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainController implements javafx.fxml.Initializable,Listener {

    private final ArrayList<Samochod> flota;
    private Samochod aktualnySamochod;

    @FXML
    private TextField obrotySilnikTextField;
    @FXML
    private TextField wagaSamochodTextField;
    @FXML
    private TextField nrRejTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField predkoscTextField;
    @FXML
    private TextField nazwaSkrzyniaTextField;
    @FXML
    private TextField cenaSkrzyniaTextField;
    @FXML
    private TextField wagaSkrzyniaTextField;
    @FXML
    private TextField biegSkrzyniaTextField;
    @FXML
    private TextField nazwaSilnikTextField;
    @FXML
    private TextField cenaSilnikTextField;
    @FXML
    private TextField wagaSilnikTextField;
    @FXML
    private TextField nazwaSprzegloTextField;
    @FXML
    private TextField cenaSprzegloTextField;
    @FXML
    private TextField wagaSprzegloTextField;
    @FXML
    private TextField stanSprzegloTextField;
    @FXML
    private ComboBox<Samochod> samochodComboBox;
    @FXML
    private Pane mapa;

    public MainController(ArrayList<Samochod> flota) {
        this.flota = flota;
    }

    //czysci stare pozycje i ustawia te z getterow
    public void refresh() {
        if (aktualnySamochod == null) {
            wagaSamochodTextField.clear();
            nrRejTextField.clear();
            modelTextField.clear();
            predkoscTextField.clear();
            nazwaSkrzyniaTextField.clear();
            cenaSkrzyniaTextField.clear();
            wagaSkrzyniaTextField.clear();
            biegSkrzyniaTextField.clear();
            nazwaSilnikTextField.clear();
            cenaSilnikTextField.clear();
            wagaSilnikTextField.clear();
            obrotySilnikTextField.clear();
            nazwaSprzegloTextField.clear();
            cenaSprzegloTextField.clear();
            wagaSprzegloTextField.clear();
            stanSprzegloTextField.clear();
            return;
        }

        wagaSamochodTextField.setText(String.valueOf(aktualnySamochod.getWaga()));
        nrRejTextField.setText(aktualnySamochod.getNrRejest());
        modelTextField.setText(aktualnySamochod.getModel());
        predkoscTextField.setText(String.valueOf(aktualnySamochod.getPredkosc()));

        nazwaSkrzyniaTextField.setText(aktualnySamochod.getSkrzynia().getNazwa());
        cenaSkrzyniaTextField.setText(String.valueOf(aktualnySamochod.getSkrzynia().getCena()));
        wagaSkrzyniaTextField.setText(String.valueOf(aktualnySamochod.getSkrzynia().getWaga()));
        biegSkrzyniaTextField.setText(String.valueOf(aktualnySamochod.getSkrzynia().getAktualnyBieg()));

        nazwaSilnikTextField.setText(aktualnySamochod.getSilnik().getNazwa());
        cenaSilnikTextField.setText(String.valueOf(aktualnySamochod.getSilnik().getCena()));
        wagaSilnikTextField.setText(String.valueOf(aktualnySamochod.getSilnik().getWaga()));
        obrotySilnikTextField.setText(String.valueOf(aktualnySamochod.getSilnik().getObroty()));

        nazwaSprzegloTextField.setText(aktualnySamochod.getSprzeglo().getNazwa());
        cenaSprzegloTextField.setText(String.valueOf(aktualnySamochod.getSprzeglo().getCena()));
        wagaSprzegloTextField.setText(String.valueOf(aktualnySamochod.getSprzeglo().getWaga()));
        stanSprzegloTextField.setText(String.valueOf(aktualnySamochod.getSprzeglo().isStanSprzegla()));

        //do synchronizacji obrazka
        Platform.runLater(() -> {
            if (aktualnySamochod.getCarImageView() != null) {
                ImageView img = aktualnySamochod.getCarImageView();
                img.setTranslateX(aktualnySamochod.getAktualnaPozycja().getX());
                img.setTranslateY(aktualnySamochod.getAktualnaPozycja().getY());
            }
        });
    }

    //obsluga nowego okna dodania wraz z zaladowaniem nowego fxmla
    public void openAddCarWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddNewCarGUI.fxml"));
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            pokazBlad("Nie udało się załadować sceny dodawania samochodu");
        }
        AddNewCarController controller = loader.getController();
        controller.setMainController(this);
        stage.setTitle("Dodaj nowy samochód");
        stage.show();
    }

    public void addCarToList(Samochod nowySamochod) {
        if (nowySamochod == null) return;

        flota.add(nowySamochod);
        aktualnySamochod = nowySamochod;
        aktualnySamochod.addListener(this); //dodanie listenera
        samochodComboBox.getItems().setAll(flota);
        samochodComboBox.getSelectionModel().select(nowySamochod);
        aktualnySamochod.stworzCarImage();

        // Dodajemy na mapę
        try
        {
            mapa.getChildren().add(aktualnySamochod.getCarImageView());
        } catch(Exception e)
        {
            pokazBlad("Nie udało się dodać ikony samochodu na plansze");
        }
        update();
    }

    @FXML
    private void onWlaczButton()
    {
        if (aktualnySamochod == null) {
            pokazBlad("BŁĄD: Nie wybrano żadnego samochodu lub lista jest pusta.");
            return;
        }
        aktualnySamochod.wlacz();
        System.out.println("Samochód uruchomiony!");
        refresh();
    }

    @FXML
    private void onWylaczButton() {
        if (aktualnySamochod == null) {
            pokazBlad("Nie wybrano samochodu!");
            return;
        }
        aktualnySamochod.wylacz();
        System.out.println("Samochód wyłączony!");
        refresh();
    }

    @FXML
    private void onZwiekszBiegButtonButton() {
        if (aktualnySamochod == null) {
            pokazBlad("Nie wybrano samochodu!");
            return;
        }

        if (!aktualnySamochod.getSilnik().getStanWlaczenia()) {
            aktualnySamochod.wlacz(); // automatyczne włączenie silnika
        }
        aktualnySamochod.getSkrzynia().zwiekszBieg();
        refresh();
    }

    @FXML
    private void onZmniejszBiegButtonButton() {
        if (aktualnySamochod == null)
        {
            pokazBlad("Nie wybrano samochodu!");
            return;
        }

        aktualnySamochod.getSkrzynia().zmniejszBieg();
        refresh();
    }

    @FXML
    private void onDodajGazuButton()
    {
        if (aktualnySamochod == null)
        {
            pokazBlad("Nie wybrano samochodu!");
            return;
        }

        if (!aktualnySamochod.getSilnik().getStanWlaczenia()) {
            aktualnySamochod.wlacz(); // automatyczne włączenie silnika
        }

        aktualnySamochod.przyspiesz(100);
        predkoscTextField.setText(String.valueOf(aktualnySamochod.getPredkosc()));
        refresh();
    }

    @FXML
    private void onUjmijGazuButton()
    {
        if (aktualnySamochod == null)
        {
            pokazBlad("Nie wybrano samochodu!");
            return;
        }

        if (!aktualnySamochod.getSilnik().getStanWlaczenia()) {
            aktualnySamochod.wlacz(); // automatyczne włączenie silnika
        }

        aktualnySamochod.zwolnij(100);
        predkoscTextField.setText(String.valueOf(aktualnySamochod.getPredkosc()));
        refresh();
    }

    @FXML
    private void onNacisnijButton()
    {
        if (aktualnySamochod == null) {
            pokazBlad("Nie wybrano samochodu!");
            return;
        }
        aktualnySamochod.getSprzeglo().wcisnij();
        refresh();
    }

    @FXML
    private void onZwolnijButton()
    {
        if (aktualnySamochod == null)
        {
            pokazBlad("Nie wybrano samochodu!");
            return;
        }
        aktualnySamochod.getSprzeglo().zwolnij();
        refresh();
    }

    @FXML
    private void onDodajNowyButton()
    {
        openAddCarWindow();
        System.out.println("Dodany Button!");
        refresh();
    }

    @FXML
    private void onUsunButton() {
        if (aktualnySamochod != null) {

            // 1. Usuń obrazek samochodu z mapy, jeśli istnieje
            //jesli aktualny samochod czyms jest to przechodzimy do 248 i usuwamy z listy/jesli nie to usuwamy obrazek, ustawiamy na nowo combo boxa
            // jesli flota jest pusta czyli usunelismy juz wszystkie to czyscimy comboboxa, jesli usunelismy tylko np jednp autko to ustawiamy
            // jako obecne pierwsze z floty i combobxa aktualizujemy
            aktualnySamochod.removeListener(this);
            ImageView img = aktualnySamochod.getCarImageView();
            if (img != null) {
                mapa.getChildren().remove(img);
                aktualnySamochod.setCarImageView(null);
            }
            // 2. Usuń samochód z listy
            try
            {
                flota.remove(aktualnySamochod);
            }
            catch(Exception e)
            {
                pokazBlad("Nie udalo sie usunac samochodu z listy");
            }

            // 3. Odśwież ComboBox
            samochodComboBox.getItems().setAll(flota);

            // 4. Ustaw nowy aktualny samochód, jeśli lista nie jest pusta
            if (!flota.isEmpty()) {
                aktualnySamochod = flota.get(0);
                samochodComboBox.getSelectionModel().select(aktualnySamochod);
            } else {
                // lista pusta
                pokazBlad("Lista została wyczyszczona!");
                aktualnySamochod = null;
                samochodComboBox.getSelectionModel().clearSelection();
            }
            // 5. Odśwież pola w UI
            refresh();
        }
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        samochodComboBox.getItems().addAll(flota);

        samochodComboBox.setOnAction(event -> {
            aktualnySamochod = samochodComboBox.getSelectionModel().getSelectedItem();
            refresh();
        });
        samochodComboBox.getSelectionModel().clearSelection();


        mapa.setOnMouseClicked(event -> {
            if (aktualnySamochod == null)
            {
                pokazBlad("Nie wybrano samochodu");
                return;
            }

            aktualnySamochod.jedzDo(new Pozycja(event.getX(), event.getY()));
        });

    }

    @Override
    public void update() {
            Platform.runLater(() -> refresh());
    }

    public void pokazBlad(String wiadomosc) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }
}

