package walle.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import walle.WallE;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private WallE wallE;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/WallE.png"));
    private Image wallEmage = new Image(this.getClass().getResourceAsStream("/images/wallE.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    /**
     * Sets WallE for the controller.
     * @param wallE WallE object to be set.
     */
    public void setWallE(WallE wallE) {
        this.wallE = wallE;
    }
    /**
     * Shows a message in the dialog container.
     * @param message The message to be shown.
     */
    @FXML
    public void showMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getWallEDialog(message, wallEmage));
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = wallE.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getWallEDialog(response, wallEmage)
        );
        userInput.clear();
        if (wallE.isExitCommand(input)) {
            System.exit(0);
        }
    }
}

