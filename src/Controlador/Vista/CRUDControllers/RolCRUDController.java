package Controlador.Vista.CRUDControllers;

import Controlador.DAO.RolDAO;
import Modelo.Rol;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class RolCRUDController implements Initializable {

    @FXML
    private JFXTextField txtIdRol;
    @FXML
    private JFXTextField txtTipoRol;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private Button btnCloseWindow;

    private Rol rol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtIdRol.setDisable(true);
        btnAceptar.disableProperty().bind(txtTipoRol.textProperty().isEmpty());
    }

    @FXML
    void Aceptar(ActionEvent event) {
        if (rol == null){
            rol = new Rol();
            rol.setTipoRol(txtTipoRol.getText());
            RolDAO.crear(rol);
        } else {
            rol.setTipoRol(txtTipoRol.getText());
            RolDAO.editar(rol);
        }
        Cancelar(event);
    }

    public void setRol(Rol rol) {
        this.rol = rol;
        mostrarDatos();
    }

    private void mostrarDatos() {
        //Cargo los datos del objeto en el editar y eliminar
        txtIdRol.setText(String.valueOf(rol.getIdRol()));
        txtTipoRol.setText(rol.getTipoRol());
    }

    @FXML
    void Cancelar(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void CloseWindow(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
}