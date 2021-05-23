package Controlador.Vista.CRUDControllers;

import Controlador.DAO.RolDAO;
import Controlador.DAO.TrabajadoresDAO;
import Controlador.Utilidad.Encripta;
import Modelo.Rol;
import Modelo.Trabajador;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class TrabajadorCRUDController implements Initializable {

    @FXML
    private Button btnCloseWindow;
    @FXML
    private JFXTextField txtIdTrabajador;
    @FXML
    private JFXTextField txtNombreTrabajador;
    @FXML
    private JFXTextField txtApellidoTrabajador;
    @FXML
    private JFXTextField txtNumeroDocumentoTrabajador;
    @FXML
    private JFXTextField txtCelularTrabajador;
    @FXML
    private JFXTextField txtCorreoTrabajador;
    @FXML
    private JFXTextField txtUsuarioTrabajador;
    @FXML
    private JFXPasswordField txtContraseñaTrabajador;
    @FXML
    private JFXComboBox<Rol> cmbRolTrabajador;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnCancelar;

    private Trabajador trabajador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtIdTrabajador.setDisable(true);
        btnAceptar.disableProperty().bind(
                txtNombreTrabajador.textProperty().isEmpty().or(
                        txtApellidoTrabajador.textProperty().isEmpty().or(
                                txtNumeroDocumentoTrabajador.textProperty().isEmpty().or(
                                        txtCelularTrabajador.textProperty().isEmpty().or(
                                                txtCorreoTrabajador.textProperty().isEmpty().or(
                                                        txtUsuarioTrabajador.textProperty().isEmpty().or(
                                                                txtContraseñaTrabajador.textProperty().isEmpty().or(
                                                                        cmbRolTrabajador.valueProperty().isNull()
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        cargarDatos();
        cmbRolTrabajador.setConverter(new StringConverter<Rol>() {
            @Override
            public String toString(Rol rol) {
                if (rol == null){
                    return null;
                }
                return rol.getTipoRol();
            }

            @Override
            public Rol fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    void Aceptar(ActionEvent event) {
        if (trabajador == null){
            trabajador = new Trabajador();
            trabajador.setNombreTrabajador(txtNombreTrabajador.getText());
            trabajador.setApellidosTrabajador(txtApellidoTrabajador.getText());
            trabajador.setNroDocumentoTrabajador(txtNumeroDocumentoTrabajador.getText());
            trabajador.setCelularTrabajador(txtCelularTrabajador.getText());
            trabajador.setCorreoTrabajador(txtCorreoTrabajador.getText());
            trabajador.setUsuarioTrabajador(txtUsuarioTrabajador.getText());
            trabajador.setContraseñaTrabajador(Encripta.encripta(txtContraseñaTrabajador.getText()));
            trabajador.setTieneRol(cmbRolTrabajador.getValue());
            TrabajadoresDAO.crear(trabajador);
        } else {
            trabajador.setNombreTrabajador(txtNombreTrabajador.getText());
            trabajador.setApellidosTrabajador(txtApellidoTrabajador.getText());
            trabajador.setNroDocumentoTrabajador(txtNumeroDocumentoTrabajador.getText());
            trabajador.setCelularTrabajador(txtCelularTrabajador.getText());
            trabajador.setCorreoTrabajador(txtCorreoTrabajador.getText());
            trabajador.setUsuarioTrabajador(txtUsuarioTrabajador.getText());
            trabajador.setContraseñaTrabajador(Encripta.encripta(txtContraseñaTrabajador.getText()));
            trabajador.setTieneRol(cmbRolTrabajador.getValue());
            TrabajadoresDAO.editar(trabajador);
        }
        Cancelar(event);
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
        mostrarDatos();
    }

    private void mostrarDatos() {
        //Cargo los datos del objeto en el editar y eliminar
        txtIdTrabajador.setText(String.valueOf(trabajador.getIdTrabajador()));
        txtNombreTrabajador.setText(trabajador.getNombreTrabajador());
        txtApellidoTrabajador.setText(trabajador.getApellidosTrabajador());
        txtNumeroDocumentoTrabajador.setText(trabajador.getNroDocumentoTrabajador());
        txtCelularTrabajador.setText(trabajador.getCelularTrabajador());
        txtCorreoTrabajador.setText(trabajador.getCorreoTrabajador());
        txtUsuarioTrabajador.setText(trabajador.getUsuarioTrabajador());
        txtContraseñaTrabajador.setText(trabajador.getContraseñaTrabajador());
        cmbRolTrabajador.getSelectionModel().select(trabajador.getTieneRol());
    }

    private void cargarDatos() {
        cmbRolTrabajador.getItems().addAll(RolDAO.listarRoles());
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
