module com.artur {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.apache.commons.lang3;

    opens com.artur.controller to javafx.fxml;
    opens com.artur.model to javafx.base;

    exports com.artur;
}
