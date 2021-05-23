package Controlador.Vista.CRUDControllers;

import Controlador.DAO.ClienteDAO;
import Modelo.Cliente;
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

public class ClienteCRUDController implements Initializable {

    @FXML
    private Button btnCloseWindow;
    @FXML
    private JFXTextField txtIdCliente;
    @FXML
    private JFXTextField txtNombreCliente;
    @FXML
    private JFXTextField txtApellidoCliente;
    @FXML
    private JFXTextField txtNumeroDocumentoCliente;
    @FXML
    private JFXTextField txtCelularCliente;
    @FXML
    private JFXTextField txtCorreoCliente;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnCancelar;

    private Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtIdCliente.setDisable(true);
        btnAceptar.disableProperty().bind(
                txtNombreCliente.textProperty().isEmpty().or(
                        txtApellidoCliente.textProperty().isEmpty().or(
                                txtNumeroDocumentoCliente.textProperty().isEmpty().or(
                                        txtCelularCliente.textProperty().isEmpty().or(
                                                txtCorreoCliente.textProperty().isEmpty()
                                        )
                                )
                        )
                )
        );
    }

    @FXML
    void Aceptar(ActionEvent event) {
        if (cliente == null){
            cliente = new Cliente();
            cliente.setNombreCliente(txtNombreCliente.getText());
            cliente.setApellidosCliente(txtApellidoCliente.getText());
            cliente.setNroDocumentoCliente(txtNumeroDocumentoCliente.getText());
            cliente.setCelularCliente(txtCelularCliente.getText());
            cliente.setCorreoCliente(txtCorreoCliente.getText());
            ClienteDAO.crear(cliente);
        } else {
            cliente.setNombreCliente(txtNombreCliente.getText());
            cliente.setApellidosCliente(txtApellidoCliente.getText());
            cliente.setNroDocumentoCliente(txtNumeroDocumentoCliente.getText());
            cliente.setCelularCliente(txtCelularCliente.getText());
            cliente.setCorreoCliente(txtCorreoCliente.getText());
            ClienteDAO.editar(cliente);
        }
        Cancelar(event);
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        mostrarDatos();
    }

    private void mostrarDatos() {
        //Cargo los datos del objeto en el editar y eliminar
        txtIdCliente.setText(String.valueOf(cliente.getIdCliente()));
        txtNombreCliente.setText(cliente.getNombreCliente());
        txtApellidoCliente.setText(cliente.getApellidosCliente());
        txtNumeroDocumentoCliente.setText(cliente.getNroDocumentoCliente());
        txtCelularCliente.setText(cliente.getCelularCliente());
        txtCorreoCliente.setText(cliente.getCorreoCliente());
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
