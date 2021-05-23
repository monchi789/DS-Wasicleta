package Controlador.Vista;

import Controlador.DAO.TrabajadoresDAO;
import Controlador.Utilidad.Encripta;
import Modelo.Trabajador;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXTextField txtUser;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Button btnCloseWindow;
    @FXML
    private Button btncrud;

    String mostrarUsuario;

    public static String nombreUsuario;

    public String setMostrarUsuario() {
        mostrarUsuario = txtUser.getText();
        return mostrarUsuario;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btncrud.setVisible(false);
    }

    @FXML
    void Login(ActionEvent event) throws IOException {
        Trabajador trabajador = TrabajadoresDAO.buscarUsuario(txtUser.getText().trim());

        Alert mensaje = new Alert(Alert.AlertType.WARNING);
        mensaje.setTitle("Autenticacion");
        mensaje.setHeaderText(null);
        mensaje.setContentText("El usuario o contraseña son incorrectos");

        if (trabajador != null){
            String rol = "Administrador";

            boolean validaTrabajador = trabajador.getContraseñaTrabajador().equals(Encripta.encripta(txtPassword.getText()));
            boolean validaRol = rol.equals(trabajador.getTieneRol().getTipoRol());
            if (validaTrabajador){
                nombreUsuario = setMostrarUsuario();
                if (validaRol) {
                    try {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/Vista/principalAdmin.fxml"));
                        stage.setMinHeight(750);
                        stage.setMinWidth(1300);
                        stage.setScene(new Scene(root));
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.show();
                        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
                    } catch (IOException e) {}
                } else  {
                    try {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/Vista/principalTrabajadores.fxml"));
                        stage.setMinHeight(750);
                        stage.setMinWidth(1300);
                        stage.setScene(new Scene(root));
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.show();
                        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
                    }catch (IOException e){}
                }
            } else {
                mensaje.show();
            }
        } else {
            mensaje.show();
        }
    }

    @FXML
    void Crud(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Vista/principalAdmin.fxml"));
            stage.setMinHeight(750);
            stage.setMinWidth(1300);
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (IOException e){}
    }

    @FXML
    void CloseWindow(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
}
