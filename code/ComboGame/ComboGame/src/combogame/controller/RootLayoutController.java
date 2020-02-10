package combogame.controller;

import combogame.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * @Auther: rando
 * @Date: 2019/10/25 22:48
 * @Description:
 */
public class RootLayoutController {
    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Randolf\nWebsite: https://randolfly.github.io");

        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

    /**
     * 刷新地图
     */
    @FXML
    private void handleRefresh() {
        mainApp.centerLayout.initMap();
    }
}
