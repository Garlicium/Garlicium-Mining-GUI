package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import main.view.MainController;
import main.view.Settings;

import java.util.Map;

public class Main extends Application {
    public static String firstLaunch="true";

    @FXML
    VBox setupVBox;
    @FXML
    ImageView garlicImg;
    @FXML
    RadioButton NvidiaRadioButton, AMDRadioButton;
    @FXML
    TextField GRLCAddressTextField, poolAddressTextField, poolPwordTextField;
    @FXML
    TextField minerPathTextField, minerIntensityTextField, minerFlagsTextField;
    @FXML
    Hyperlink helpLink;
    @FXML
    Button goButton, configLogsButton;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Font.loadFont(getClass().getResource("resources/fonts/FiraSans-Regular.otf").toExternalForm(), 10);

        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/mainUI.fxml"));
        primaryStage.setTitle("Garlicium-Mining-GUI");
        primaryStage.setScene(new Scene(root, 900, 700));

        // Kill process when window closed (if "Stop mining" button not used)
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
        //System.out.println(GRLCAddressTextField.getHeight());
    }

    public static void main(String[] args) {
        // Starts the FX toolkit, instantiates this class,
        // and calls start(...) on the FX Application thread:
        launch(args);
    }

}
