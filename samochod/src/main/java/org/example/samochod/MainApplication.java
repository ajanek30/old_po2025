package org.example.samochod;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import symulator.*;

import java.io.IOException;
import java.util.ArrayList;

//tutaj kontrolujemy powstawanie kontrolera i wstepna inicjalizacje
public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        ArrayList<Samochod> listaSamochodow = new ArrayList<>();


        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainGUI.fxml"));

        fxmlLoader.setControllerFactory(controllerClass -> {
            if (controllerClass == MainController.class) {
                return new MainController(listaSamochodow);
            } else {
                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Nie udało się utworzyć kontrolera", e);
                }
            }
        });
//        ma konkretny cel: pozwala na stworzenie instancji kontrolera MainController z własnym konstruktorem, który przyjmuje listę samochodów (ArrayList<Samochod> listaSamochodow).
//
//                Normalnie, JavaFX przy ładowaniu FXML tworzy kontroler przy pomocy domyślnego konstruktora bez parametrów. Problem jest taki: Twój MainController nie ma domyślnego konstruktora – on wymaga listy samochodów.
//
//        Dlatego musisz użyć setControllerFactory, żeby powiedzieć FXMLLoaderowi:
//
//        jeśli chcemy MainController, to stwórz go z tą konkretną listą samochodów;
//
//        jeśli inny kontroler, użyj standardowego konstruktora.
//
//        Bez tego JavaFX rzuciłby wyjątek typu InstantiationException, bo nie umie stworzyć kontrolera bezpłatnie.
//
//💡 W skrócie: pozwala wstrzyknąć własną listę samochodów do kontrolera przy starcie aplikacji.
//
//        Mogę pokazać Ci też prostszy sposób, jeśli chcesz, żeby kontroler był tworzony domyślnie i potem tylko inicjalizował listę w initialize(). Chcesz, żebym to zrobił?

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Car2025");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setWidth(900);
        stage.setHeight(650);
        stage.centerOnScreen();
        stage.setFullScreen(false);
        stage.setFullScreenExitHint("Naciśnij ESC, aby wyjść z pełnego ekranu");
        stage.setResizable(true);
        scene.setFill(Color.web("#1e1e1e"));
        stage.setScene(scene);

        stage.show();
    }
}
