module com.example.calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires commons.math3;
    requires commons.jexl3;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
//    requires com.almasb.fxgl.all;

    opens com.example.calculator to javafx.fxml;
    exports com.example.calculator;
}