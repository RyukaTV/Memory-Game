module com.sio.memory {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sio.memory to javafx.fxml;
	
    exports com.sio.memory;
}
