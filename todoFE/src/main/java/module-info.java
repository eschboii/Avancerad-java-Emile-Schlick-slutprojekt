module org.example.todofe {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires javafx.graphics;
    requires org.json;
    requires com.fasterxml.jackson.databind;

    opens org.example.todofe to javafx.fxml;
    exports org.example.todofe;
}