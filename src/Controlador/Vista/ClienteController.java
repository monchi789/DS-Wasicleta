package Controlador.Vista;

import Controlador.DAO.ClienteDAO;
import Controlador.DAO.TipoBicicletaDAO;
import Controlador.Vista.CRUDControllers.ClienteCRUDController;
import Controlador.Vista.CRUDControllers.TipoBicicletaCRUDController;
import Modelo.Cliente;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn<Cliente, Long> colIdCliente;
    @FXML
    private TableColumn<Cliente, String> colNombreCliente;
    @FXML
    private TableColumn<Cliente, String> colApellidoCliente;
    @FXML
    private TableColumn<Cliente, String> colNumeroDocumentoCliente;
    @FXML
    private TableColumn<Cliente, String> colCelularCliente;
    @FXML
    private TableColumn<Cliente, String> colCorreoCliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatos();
        //Asignar los datos en las columnas
        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colApellidoCliente.setCellValueFactory(new PropertyValueFactory<>("apellidosCliente"));
        colNumeroDocumentoCliente.setCellValueFactory(new PropertyValueFactory<>("nroDocumentoCliente"));
        colCelularCliente.setCellValueFactory(new PropertyValueFactory<>("celularCliente"));
        colCorreoCliente.setCellValueFactory(new PropertyValueFactory<>("correoCliente"));
        //Lockeamos los botones de editar y eliminar si no hay ningun objeto seleccionado
        btnEdit.disableProperty().bind(tblClientes.getSelectionModel().selectedItemProperty().isNull());
        btnDelete.disableProperty().bind(btnEdit.disableProperty());
    }

    private void cargarDatos() {
        tblClientes.getItems().clear();
        tblClientes.getItems().addAll(ClienteDAO.listarClientes());
    }

    @FXML
    void NewUF(ActionEvent event) {
        Map datos = escenario("/Vista/CRUD/clienteCRUD.fxml");
        Stage stage = (Stage)datos.get("escenario");
        stage.showAndWait();
        cargarDatos();
    }

    @FXML
    void EditUF(ActionEvent event) {
        Map datos = escenario("/Vista/CRUD/clienteCRUD.fxml");
        ClienteCRUDController controlador = (ClienteCRUDController) datos.get("controlador");
        controlador.setCliente(tblClientes.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)datos.get("escenario");
        stage.showAndWait();
        cargarDatos();
    }

    @FXML
    void DeleteUF(ActionEvent event) {
        Alert diaglo = new Alert(Alert.AlertType.CONFIRMATION);
        diaglo.setTitle("Eliminar");
        diaglo.setHeaderText(null);
        diaglo.setContentText("¿Esta seguro que desea eliminar al usuario de manera permanente?");
        Optional<ButtonType> resultado = diaglo.showAndWait();
        if (resultado.get() == ButtonType.OK){
            ClienteDAO.eliminar(tblClientes.getSelectionModel().getSelectedItem());
        }
        cargarDatos();
    }

    public Map escenario(String path){
        Stage stage = new Stage();
        ClienteCRUDController controlador = null;
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource(path));
            Parent root = cargador.load();
            controlador = cargador.getController();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
        }catch (IOException e){}
        Map salida = new HashMap();
        salida.put("escenario",stage);
        salida.put("controlador",controlador);
        return salida;
    }
}
