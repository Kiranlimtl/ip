package walle;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import walle.ui.MainWindow;
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private WallE wallE = new WallE();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.getController();
            controller.setWallE(wallE);
            showInitialMessages(controller);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showInitialMessages(MainWindow controller) {
        String welcomeMessage = wallE.getWelcomeMessage();
        String reminderMessage = wallE.getReminderMessage();
        controller.showMessage(welcomeMessage);
        controller.showMessage(reminderMessage);
    }
}
