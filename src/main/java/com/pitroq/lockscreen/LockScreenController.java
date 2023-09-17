package com.pitroq.lockscreen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LockScreenController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label infoLabel;

    private final Config config = new Config("config.cfg");

    @FXML
    private void unlockPC(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            if (passwordField.getText().equals(config.get("password"))) {
                LockScreen.closeLockScreen();
            }
            else {
                infoLabel.setText("Bad password!");
            }
        }
    }
}
