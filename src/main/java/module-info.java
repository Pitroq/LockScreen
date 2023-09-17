module com.pitroq.lockscreen {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dustinredmond.fxtrayicon;


    opens com.pitroq.lockscreen to javafx.fxml;
    exports com.pitroq.lockscreen;
}