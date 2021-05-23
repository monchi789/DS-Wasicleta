package Controlador.Vista.CRUDControllers;

import Controlador.DAO.TipoBicicletaDAO;
import Modelo.TipoBicicleta;
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

public class TipoBicicletaCRUDController implements Initializable {

    @FXML
    private Button btnCloseWindow;
    @FXML
    private JFXTextField txtIdTipoBicicleta;
    @FXML
    private JFXTextField txtNombreTipoBicicleta;
    @FXML
    private JFXTextField txtPrecioTipoBicicleta;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnCancelar;

    private TipoBicicleta tipoBicicleta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtIdTipoBicicleta.setDisable(true);
        btnAceptar.disableProperty().bind(
                txtNombreTipoBicicleta.textProperty().isEmpty().or(
                        txtPrecioTipoBicicleta.textProperty().isEmpty()
        ));
    }

    @FXML
    void Aceptar(ActionEvent event) {
        if (tipoBicicleta == null){
            tipoBicicleta = new TipoBicicleta();
            tipoBicicleta.setNombreTipoBicicleta(txtNombreTipoBicicleta.getText());
            tipoBicicleta.setPrecioTipoBicicleta(Double.valueOf(txtPrecioTipoBicicleta.getText()));
            TipoBicicletaDAO.crear(tipoBicicleta);
        } else {
            tipoBicicleta.setNombreTipoBicicleta(txtNombreTipoBicicleta.getText());
            tipoBicicleta.setPrecioTipoBicicleta(Double.valueOf(txtPrecioTipoBicicleta.getText()));
            TipoBicicletaDAO.editar(tipoBicicleta);
        }
        Cancelar(event);
    }

    public void setTipoBicicleta(TipoBicicleta tipoBicicleta) {
        this.tipoBicicleta = tipoBicicleta;
        mostrarDatos();
    }

    private void mostrarDatos(){
        //Cargo los datos del objeto en el editar y eliminar
        txtIdTipoBicicleta.setText(String.valueOf(tipoBicicleta.getIdTipoBicicleta()));
        txtNombreTipoBicicleta.setText(tipoBicicleta.getNombreTipoBicicleta());
        txtPrecioTipoBicicleta.setText(String.valueOf(tipoBicicleta.getPrecioTipoBicicleta()));
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
