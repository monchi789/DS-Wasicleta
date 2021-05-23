package Controlador.Vista.CRUDControllers;

import Controlador.DAO.BicicletaDAO;
import Controlador.DAO.RolDAO;
import Controlador.DAO.TipoBicicletaDAO;
import Modelo.Bicicleta;
import Modelo.TipoBicicleta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

public class BicicletaCRUDController implements Initializable {

    @FXML
    private Button btnCloseWindow;
    @FXML
    private JFXTextField txtIdBicicleta;
    @FXML
    private JFXTextField txtCodigoBicicleta;
    @FXML
    private JFXTextField txtEstadoBicicleta;
    @FXML
    private JFXComboBox<TipoBicicleta> cmbTipoBicicleta;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnCancelar;

    private Bicicleta bicicleta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtIdBicicleta.setDisable(true);
        btnAceptar.disableProperty().bind(
                txtCodigoBicicleta.textProperty().isEmpty().or(
                        txtEstadoBicicleta.textProperty().isEmpty().or(
                                cmbTipoBicicleta.valueProperty().isNull()
                        )
                )
        );
        cargarDatos();
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
    }

    @FXML
    void Aceptar(ActionEvent event) {
        if (bicicleta == null){
            bicicleta = new Bicicleta();
            bicicleta.setCodigoBicicleta(txtCodigoBicicleta.getText());
            bicicleta.setStatusBicicleta(txtEstadoBicicleta.getText());
            bicicleta.setTieneTipoBicicleta(cmbTipoBicicleta.getValue());
            BicicletaDAO.crear(bicicleta);
        } else {
            bicicleta.setCodigoBicicleta(txtCodigoBicicleta.getText());
            bicicleta.setStatusBicicleta(txtEstadoBicicleta.getText());
            bicicleta.setTieneTipoBicicleta(cmbTipoBicicleta.getValue());
            BicicletaDAO.editar(bicicleta);
        }
        Cancelar(event);
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
        mostrarDatos();
    }

    private void mostrarDatos() {
        //Cargo los datos del objeto en el editar y eliminar
        txtIdBicicleta.setText(String.valueOf(bicicleta.getIdBicicleta()));
        txtCodigoBicicleta.setText(bicicleta.getCodigoBicicleta());
        txtEstadoBicicleta.setText(bicicleta.getStatusBicicleta());
        cmbTipoBicicleta.getSelectionModel().select(bicicleta.getTieneTipoBicicleta());
    }

    private void cargarDatos() {
        cmbTipoBicicleta.getItems().addAll(TipoBicicletaDAO.listarTipoBicicletas());
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
