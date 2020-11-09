module Estacionamiento {
    requires javafx.fxml;
    requires javafx.controls;

    opens Principal.View to javafx.fxml;
    opens Principal.Model to javafx.base;

    exports Principal;
}