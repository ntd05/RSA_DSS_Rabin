import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerDSS extends ControllerRSA{

    @FXML private TextField m_text;
    @FXML private TextField p_text;
    @FXML private TextField q_text;
    @FXML private TextField a_text;
    @FXML private TextField b_text;
    @FXML private TextField r_text;
    @FXML private TextField s_text;
    @FXML private TextField x_text;
    @FXML private TextField k_text;
    @FXML private TextField s_check_text;
    @FXML private Label confirm_text;
    @FXML private Label message_text;
    @FXML private Label warning;
    @FXML private Label warning1;
    @FXML private Button back;


    public void Ok(ActionEvent event) throws IOException {
        try {
            int q = Integer.parseInt(q_text.getText());
            int m = Integer.parseInt(m_text.getText());
            int a = Integer.parseInt(a_text.getText());
            int b = Integer.parseInt(b_text.getText());
            int x = Integer.parseInt(x_text.getText());
            int k = Integer.parseInt(k_text.getText());
            if(prime(q) == false){
                warning.setText("Số q không phải số nguyên tố.");
                p_text.setText("");
                s_text.setText("");
                r_text.setText("");
                message_text.setText("");
                s_check_text.setText("");
                confirm_text.setText("");
            } else {
                int p = b * q + 1;
                warning.setText("");
                if(prime(p)== false){
                    warning1.setText("Số p không phải nguyên tố.");
                    p_text.setText("");
                    s_text.setText("");
                    r_text.setText("");
                    message_text.setText("");
                    s_check_text.setText("");
                    confirm_text.setText("");
                } else {
                    warning1.setText("");
                    p_text.setText(Integer.toString(p));
                    int g = mod(a, b, p);
                    int y = mod(g, x, p);
                    int r = mod(g, k, p) % q;
                    int s = (modulo_inverse(k, q) * (m + x * r)) % q;
                    s_text.setText(Integer.toString(s));
                    r_text.setText(Integer.toString(r));
                    message_text.setText("(" + m + ", " + r + ", " + s +")");
                    int w = modulo_inverse(s, q);
                    System.out.println(w);
                    int e1 = (m * w) % q;
                    int e2 = (r * w) % q;
                    System.out.println(e1 +" " + e2);
                    int tam = mod(g, e1, p) * mod(y, e2, p);
                    int s_check =(tam % p) % q;
                    s_check_text.setText(Integer.toString(s_check));
                    if( s_check == r) {
                        confirm_text.setText("Đúng chữ ký.");
                    } else {
                        confirm_text.setText("Sai chữ ký.");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
            Parent root = loader.load();
            back.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
