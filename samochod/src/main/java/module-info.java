module org.example.samochod {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.samochod to javafx.fxml;
    exports org.example.samochod;
    requires BackEND;//
    requires java.desktop;
}