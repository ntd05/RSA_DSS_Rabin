import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerHome {

    @FXML private Button RSA;
    @FXML private Button DSA;
    @FXML private Button Rabin;

    public void RSA_UI(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RSA_UI.fxml"));
            Parent root = loader.load();
            RSA.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Rabin_UI(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Rabin_UI.fxml"));
            Parent root = loader.load();
            Rabin.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DSA_UI(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DSA_UI.fxml"));
            Parent root = loader.load();
            DSA.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
