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

public class ControllerRSA {

    @FXML private TextField M_text;
    @FXML private TextField p_text;
    @FXML private TextField q_text;
    @FXML private TextField e_text;
    @FXML private TextField d_text;
    @FXML private TextField EKp_text;
    @FXML private TextField DKp_text;
    @FXML private Label warning;
    @FXML private Label warning1;
    @FXML private Button back;

    public boolean prime(int num) {
        int i;
        for (i = 2; i< Math.sqrt(num); ++i)
            if (num % i == 0)
                return false;
        return true;
    }

    public int USCLN(int a, int b) {
        if (b == 0) return a;
        return USCLN(b, a % b);
    }

    public int modulo_inverse(int n, int m) {
        for (int i=1; i < m; i++) {
            if ((long)( n * i) % m == 1) {
                return i;
            }
        }
        return -1; // not exist
    }

    public int mod(int base, int expo, int num) {
        int res = 1;
        int i;
        for (i = 1; i <= expo; ++i)
            res = (res * base) % num;
        return res;
    }

    public void OK(ActionEvent event) throws IOException {
        try {
            int M = Integer.parseInt(M_text.getText());
            int p = Integer.parseInt(p_text.getText());
            int q = Integer.parseInt(q_text.getText());
            int e = Integer.parseInt(e_text.getText());
            int n = (p - 1) * (q - 1);
            int N = p * q;
            if(M > N || prime(p) == false || prime(q) == false) {
                warning.setText("Hai số p và q không hợp lệ");
                d_text.setText("");
                EKp_text.setText("");
                DKp_text.setText("");
            }
            else {
                warning.setText("");
                if(USCLN(e,n) != 1 || e > n) {
                    warning1.setText("e không hợp lệ");
                    d_text.setText("");
                    EKp_text.setText("");
                    DKp_text.setText("");
                }
                else {
                    warning1.setText("");
                    int d = modulo_inverse(e, n);
                    d_text.setText(Integer.toString(d));
                    System.out.println(M+" "+ e+" "+N);
                    int C = mod(M, e, N);
                    EKp_text.setText(Integer.toString(C));
                    DKp_text.setText(Integer.toString(mod(C, d, N)));
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
