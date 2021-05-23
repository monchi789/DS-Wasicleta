package Controlador.Vista.Rental;

import Controlador.DAO.*;
import Modelo.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

public class NewUserController implements Initializable {

    @FXML
    private Button btnCloseWindow;
    @FXML
    private JFXTextField txtNombreAlquiler;
    @FXML
    private JFXTextField txtNumeroDocumentoAlquiler;
    @FXML
    private JFXTextField txtApellidoAlquiler;
    @FXML
    private JFXTextField txtCelularAlquiler;
    @FXML
    private JFXTextField txtCorreoAlquiler;
    @FXML
    private VBox VboxA;
    @FXML
    private JFXComboBox<TipoBicicleta> cmbTipoBicicleta;
    @FXML
    private JFXComboBox<Bicicleta> cmbBicicleta;
    @FXML
    private JFXComboBox<Trabajador> cmbUsuarioAlquiler;
    @FXML
    private JFXTextField txtFechaAlquiler;
    @FXML
    private HBox VboxB;
    @FXML
    private JFXTextField txtMontoAlquiler;
    @FXML
    private Button btnRental;

    private Alquiler alquiler;

    public String codigoautogenerado = UUID.randomUUID().toString().toUpperCase(Locale.ROOT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnRental.disableProperty().bind(
                txtNombreAlquiler.textProperty().isEmpty().or(
                        txtApellidoAlquiler.textProperty().isEmpty().or(
                                txtNumeroDocumentoAlquiler.textProperty().isEmpty().or(
                                        txtCelularAlquiler.textProperty().isEmpty().or(
                                                txtCorreoAlquiler.textProperty().isEmpty().or(
                                                        cmbTipoBicicleta.valueProperty().isNull().or(
                                                                cmbBicicleta.valueProperty().isNull().or(
                                                                        cmbUsuarioAlquiler.valueProperty().isNull().or(
                                                                                txtFechaAlquiler.textProperty().isEmpty().or(
                                                                                        txtMontoAlquiler.textProperty().isEmpty()
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        cargarDatos();
        //Cargamos a los tipos de Bicicleta
        cmbTipoBicicleta.setConverter(new StringConverter<TipoBicicleta>() {
            @Override
            public String toString(TipoBicicleta tipoBicicleta) {
                if (tipoBicicleta == null){
                    return null;
                }
                return tipoBicicleta.getNombreTipoBicicleta();
            }

            @Override
            public TipoBicicleta fromString(String s) {
                return null;
            }
        });
        //Cargamos a las Bicicleta
        cmbBicicleta.setConverter(new StringConverter<Bicicleta>() {
            @Override
            public String toString(Bicicleta bicicleta) {
                if (bicicleta == null){
                    return null;
                }
                return bicicleta.getCodigoBicicleta();
            }

            @Override
            public Bicicleta fromString(String s) {
                return null;
            }
        });
        //Cargamos a las Bicicleta
        cmbUsuarioAlquiler.setConverter(new StringConverter<Trabajador>() {
            @Override
            public String toString(Trabajador trabajador) {
                if (trabajador == null){
                    return null;
                }
                return trabajador.getUsuarioTrabajador();
            }

            @Override
            public Trabajador fromString(String s) {
                return null;
            }
        });
    }

    @FXML
    void Rental(ActionEvent event) {
        Cliente cliente = ClienteDAO.buscarCliente("General");

        alquiler = new Alquiler();
        alquiler.setPerteneceCliente(cliente);
        alquiler.setNombreAlquiler(txtNombreAlquiler.getText());
        alquiler.setApellidoAlquiler(txtApellidoAlquiler.getText());
        alquiler.setNumeroDocumentoAlquiler(txtNumeroDocumentoAlquiler.getText());
        alquiler.setCelularAlquiler(txtCelularAlquiler.getText());
        alquiler.setCorreoAlquiler(txtCorreoAlquiler.getText());
        alquiler.setFechaAlquiler(txtFechaAlquiler.getText());
        alquiler.setCodigo(codigoautogenerado);
        alquiler.setTieneBicicleta(cmbBicicleta.getValue());
        alquiler.setPerteneceTrabajador(cmbUsuarioAlquiler.getValue());
        alquiler.setMontoAlquiler(txtMontoAlquiler.getText());
        AlquilerDAO.crear(alquiler);
        CloseWindow(event);
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    private void cargarDatos() {
        cmbTipoBicicleta.getItems().addAll(TipoBicicletaDAO.listarTipoBicicletas());
        cmbUsuarioAlquiler.getItems().addAll(TrabajadoresDAO.listarTrabajadores());
    }

    @FXML
    void SeleccionaTipo(ActionEvent event) {
        TipoBicicleta seleccionado = cmbTipoBicicleta.getSelectionModel().getSelectedItem();
        cmbBicicleta.getItems().clear();
        cmbBicicleta.getItems().addAll(BicicletaDAO.listarTipoBicicleta(seleccionado));
    }

    @FXML
    void CloseWindow(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
}
