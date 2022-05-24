module com.example.amble {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;
    requires com.sothawo.mapjfx;
    requires org.jsoup;

    opens uk.ac.cam.cl.group15.amble to javafx.fxml;
    exports uk.ac.cam.cl.group15.amble;
}