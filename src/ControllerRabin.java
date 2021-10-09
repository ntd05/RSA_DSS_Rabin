import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.io.IOException;

public class ControllerRabin extends ControllerRSA {

    @FXML private TextField m_text;
    @FXML private TextField M1_text;
    @FXML private TextField M2_text;
    @FXML private TextField M3_text;
    @FXML private TextField M4_text;
    @FXML private TextField p_text;
    @FXML private TextField q_text;
    @FXML private TextField c_text;
    @FXML private Label public_key;
    @FXML private Label private_key;
    @FXML private Button back;

    public static int[] Euclid_Extended(int a, int b){
        int x0=1, x1=0, y0=0, y1=1;
        int x= 0, y = 0;
        while (b>0) {
            int r = a % b;
            if (r==0)
                break;
            int q = a/b;
            x = x0-q*x1;
            y = y0-q*y1;
            a = b;
            b = r;
            x0 = x1;
            x1 = x;
            y0 = y1;
            y1 = y;
        }
        int[] arr = new int[2];
        arr[0] = x;
        arr[1] = y;
        return arr;
    }

    public void MH(ActionEvent event) throws IOException {
        try {
            int m = Integer.parseInt(m_text.getText());
            int p = Integer.parseInt(p_text.getText());
            int q = Integer.parseInt(q_text.getText());
            int n = p * q;
            public_key.setText(Integer.toString(n));
            private_key.setText("(" + p + ", " + q + ")");
            c_text.setText(Integer.toString(m * m % n));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GM(ActionEvent event) throws IOException {
        try {
            int c = Integer.parseInt(c_text.getText());
            int p = Integer.parseInt(p_text.getText());
            int q = Integer.parseInt(q_text.getText());
            int []arr = Euclid_Extended(p,q);
            int n = p * q;
            int mp1 = mod(c, (p+ 1)/4, p);
            int mq1 = mod(c, (q+ 1)/4, q);
            int mp2 = p - mp1;
            int mq2 = q - mq1;
            int m1 = (mp1 * q * modulo_inverse(q, p) + mq1 * p * modulo_inverse(p,q))% n;
            int m2 = (mp1 * q * modulo_inverse(q, p) + mq2 * p * modulo_inverse(p,q))% n;
            int m3 = (mp2 * q * modulo_inverse(q, p) + mq1 * p * modulo_inverse(p,q))% n;
            int m4 = (mp2 * q * modulo_inverse(q, p) + mq2 * p * modulo_inverse(p,q))% n;
            M1_text.setText(Integer.toString(m1));
            M2_text.setText(Integer.toString(m2));
            M3_text.setText(Integer.toString(m3));
            M4_text.setText(Integer.toString(m4));
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
