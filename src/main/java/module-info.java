module com.artur {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.artur to javafx.fxml;

    exports com.artur;
}
