package com.pitroq.lockscreen;

import javafx.application.Application;
import javafx.stage.Stage;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setWidth(0);
        stage.setHeight(0);
        stage.initStyle(StageStyle.UTILITY);
        stage.setOpacity(0);

        FXTrayIcon trayIcon = new FXTrayIcon(stage, getClass().getResource("img/lock_icon.png"));
        trayIcon.show();
        trayIcon.setTooltip("Lock your system");
        LockScreen lockScreen = new LockScreen();
        Config config = new Config("config.cfg");

        if (Boolean.parseBoolean(config.get("lockScreenOnStart"))) {
            lockScreen.createLockScreen();
        }

        trayIcon.setOnAction(event -> {
            try {
                lockScreen.createLockScreen();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}