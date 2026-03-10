package org.example.samochod;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import symulator.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddNewCarController implements Initializable {

    @FXML private TextField modelTextField;
    @FXML private TextField nrRejTextField;
    @FXML private TextField wagaSamochodTextField;
    @FXML private TextField predkoscMaksymalnaTextField;

    @FXML private ComboBox<Silnik> silnikComboBox;
    @FXML private ComboBox<SkrzyniaBiegow> skrzyniaComboBox;
    @FXML private ComboBox<Sprzeglo> sprzegloComboBox;
    @FXML private ComboBox<Pozycja> pozycjaComboBox;

    @FXML private Button confirmButton;
    @FXML private Button cancelButton;

    @FXML
    private MainController mainController;


    // Listy komponentów do wyboru
    private List<Silnik> silniki = new ArrayList<>();
    private List<SkrzyniaBiegow> skrzynie = new ArrayList<>();
    private List<Sprzeglo> sprzegla = new ArrayList<>();
    private List<Pozycja> pozycje = new ArrayList<>();

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Tworzymy przykładowe obiekty
        Silnik silnik1 = new Silnik("Tesla", "3.6EV", 4300, "3.6EV", 350, 12999);
        Silnik silnik2 = new Silnik("Volkswagen","2.0TDI",6666,"2.0TDI",329,4959);
        Silnik silnik3 = new Silnik("BMW","4.2TDC",5000,"4.2TDC",493,8999);

        Sprzeglo sprzeglo1 = new Sprzeglo(silnik1, "Yomaha", "FuryX", "sprzeglo", 344, 2137);
        Sprzeglo sprzeglo2 = new Sprzeglo(silnik2,"Daiwoon","32453DD","32453DD",321,3943);
        Sprzeglo sprzeglo3 = new Sprzeglo(silnik3,"Takuraku","9F","9F",245,2444);

        SkrzyniaBiegow skrzynia1 = new SkrzyniaBiegow("Hashimoto", "322234", 1, 6, 3, "skrzynia", 150, 1499, sprzeglo1);
        SkrzyniaBiegow skrzynia2 = new SkrzyniaBiegow("Berkimo","2.4",1,5,1,"2.4",150,2343,sprzeglo2);
        SkrzyniaBiegow skrzynia3 = new SkrzyniaBiegow("DieseWiegen","Fulf",1,6,1,"Fulf",423,1549,sprzeglo3);


        Pozycja pozycja1 = new Pozycja(1.0, 1.0);
        Pozycja pozycja2 = new Pozycja(200.0, 200.0);
        Pozycja pozycja3 = new Pozycja(400.0, 400.0);


        // Dodajemy je do list
        silniki.add(silnik1);
        silniki.add(silnik2);
        silniki.add(silnik3);
        sprzegla.add(sprzeglo1);
        sprzegla.add(sprzeglo2);
        sprzegla.add(sprzeglo3);
        skrzynie.add(skrzynia1);
        skrzynie.add(skrzynia2);
        skrzynie.add(skrzynia3);

        pozycje.add(pozycja1);
        pozycje.add(pozycja2);
        pozycje.add(pozycja3);

        // Przypisujemy do ComboBoxów
        silnikComboBox.getItems().setAll(silniki);
        skrzyniaComboBox.getItems().setAll(skrzynie);
        sprzegloComboBox.getItems().setAll(sprzegla);
        pozycjaComboBox.getItems().setAll(pozycje);

        // Wyświetlanie nazw zamiast adresów obiektów w ComboBox
        silnikComboBox.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Silnik item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNazwa());
            }
        });
        silnikComboBox.setButtonCell(silnikComboBox.getCellFactory().call(null));

        skrzyniaComboBox.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(SkrzyniaBiegow item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNazwa());
            }
        });
        skrzyniaComboBox.setButtonCell(skrzyniaComboBox.getCellFactory().call(null));

        sprzegloComboBox.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Sprzeglo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNazwa());
            }
        });
        sprzegloComboBox.setButtonCell(sprzegloComboBox.getCellFactory().call(null));

        pozycjaComboBox.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Pozycja item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : "X:" + item.getX() + " Y:" + item.getY());
            }
        });
        pozycjaComboBox.setButtonCell(pozycjaComboBox.getCellFactory().call(null));

        // Zaznaczamy pierwsze elementy
        silnikComboBox.getSelectionModel().selectFirst();
        skrzyniaComboBox.getSelectionModel().selectFirst();
        sprzegloComboBox.getSelectionModel().selectFirst();
        pozycjaComboBox.getSelectionModel().selectFirst();
    }


    @FXML
    private void onConfirmButton() {

        String model = modelTextField.getText();
        String nrRej = nrRejTextField.getText();
        int waga;
        int predkoscMaksymalna;

        try{
            waga = Integer.parseInt(wagaSamochodTextField.getText());
            predkoscMaksymalna = Integer.parseInt(predkoscMaksymalnaTextField.getText());
        } catch (NumberFormatException e) {
            mainController.pokazBlad("Wpisano niepoprawne dane!(waga/predkosc_max)");
            return;
        }

        // Tworzymy nowe instancje komponentów, niezależne od tych w ComboBox
        //silnik>sprzeglo>skrzynia
        Silnik nowySilnik = new Silnik(
                silnikComboBox.getValue().getProducent(),
                silnikComboBox.getValue().getModel(),
                silnikComboBox.getValue().getMaxObroty(),
                silnikComboBox.getValue().getNazwa(),
                silnikComboBox.getValue().getWaga(),
                silnikComboBox.getValue().getCena()
        );

        Sprzeglo noweSprzeglo = new Sprzeglo(
                nowySilnik, // powiązane z nowym silnikiem
                sprzegloComboBox.getValue().getProducent(),
                sprzegloComboBox.getValue().getModel(),
                sprzegloComboBox.getValue().getNazwa(),
                sprzegloComboBox.getValue().getWaga(),
                sprzegloComboBox.getValue().getCena()
        );

        SkrzyniaBiegow nowaSkrzynia = new SkrzyniaBiegow(
                skrzyniaComboBox.getValue().getProducent(),
                skrzyniaComboBox.getValue().getModel(),
                skrzyniaComboBox.getValue().getAktualnyBieg(),
                skrzyniaComboBox.getValue().getIloscBiegow(),
                skrzyniaComboBox.getValue().getAktualnePrzelozenie(),
                skrzyniaComboBox.getValue().getNazwa(),
                skrzyniaComboBox.getValue().getCena(),
                skrzyniaComboBox.getValue().getWaga(),
                noweSprzeglo // powiązanie ze sprzęgłem
        );

        Pozycja nowaPozycja = new Pozycja(
                pozycjaComboBox.getValue().getX(),
                pozycjaComboBox.getValue().getY()
        );

        // Tworzymy samochód z nowymi komponentami
        Samochod nowySamochod = new Samochod(
                nrRej,
                model,
                predkoscMaksymalna,
                waga,
                nowaPozycja,
                nowySilnik,
                nowaSkrzynia,
                noweSprzeglo);


        // Dodajemy samochód do głównej listy w MainController
        if (mainController != null) {
            mainController.addCarToList(nowySamochod);
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.close();
        }

        // Zamykamy okno dodawania
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancelButton() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
