package Controlador.Vista;

import Controlador.DAO.TipoBicicletaDAO;
import Controlador.Vista.CRUDControllers.TipoBicicletaCRUDController;
import Modelo.TipoBicicleta;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class TipoBicicletaController implements Initializable {

    @FXML
    private Button btnCloseWindow;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<TipoBicicleta> tblTipoBicicleta;
    @FXML
    private TableColumn<TipoBicicleta, Long> colIdTipoBicicleta;
    @FXML
    private TableColumn<TipoBicicleta, String> colNombreTipoBicicleta;
    @FXML
    private TableColumn<TipoBicicleta, Double> colPrecioBicicleta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        //Asignar los datos en las columnas
        colIdTipoBicicleta.setCellValueFactory(new PropertyValueFactory<>("idTipoBicicleta"));
        colNombreTipoBicicleta.setCellValueFactory(new PropertyValueFactory<>("nombreTipoBicicleta"));
        colPrecioBicicleta.setCellValueFactory(new PropertyValueFactory<>("precioTipoBicicleta"));
        //Lockeamos los botones de editar y eliminar si no hay ningun objeto seleccionado
        btnEdit.disableProperty().bind(tblTipoBicicleta.getSelectionModel().selectedItemProperty().isNull());
        btnDelete.disableProperty().bind(btnEdit.disableProperty());
    }

    private void cargarDatos() {
        tblTipoBicicleta.getItems().clear();
        tblTipoBicicleta.getItems().addAll(TipoBicicletaDAO.listarTipoBicicletas());
    }

    @FXML
    void NewT(ActionEvent event) {
        Map datos = escenario("/Vista/CRUD/tipoBicicletaCRUD.fxml");
        Stage stage = (Stage)datos.get("escenario");
        stage.showAndWait();
        cargarDatos();
    }

    @FXML
    void EditT(ActionEvent event) {
        Map datos = escenario("/Vista/CRUD/tipoBicicletaCRUD.fxml");
        TipoBicicletaCRUDController controlador = (TipoBicicletaCRUDController) datos.get("controlador");
        controlador.setTipoBicicleta(tblTipoBicicleta.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)datos.get("escenario");
        stage.showAndWait();
        cargarDatos();
    }

    @FXML
    void DeleteT(ActionEvent event) {
        Alert diaglo = new Alert(Alert.AlertType.CONFIRMATION);
        diaglo.setTitle("Eliminar");
        diaglo.setHeaderText(null);
        diaglo.setContentText("¿Esta seguro que desea eliminar al usuario de manera permanente?");
        Optional<ButtonType> resultado = diaglo.showAndWait();
        if (resultado.get() == ButtonType.OK){
            TipoBicicletaDAO.eliminar(tblTipoBicicleta.getSelectionModel().getSelectedItem());
        }
        cargarDatos();
    }

    public Map escenario(String path){
        Stage stage = new Stage();
        TipoBicicletaCRUDController controlador = null;
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

    @FXML
    void CloseWindow(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

}