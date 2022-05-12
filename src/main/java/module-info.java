module com.example.amble {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.bootstrapfx.core;

    opens uk.ac.cam.cl.group15.amble to javafx.fxml;
    exports uk.ac.cam.cl.group15.amble;
}