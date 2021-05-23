package Controlador.Vista;

import Controlador.DAO.BicicletaDAO;
import Controlador.Vista.CRUDControllers.BicicletaCRUDController;
import Modelo.Bicicleta;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class BicycleController implements Initializable {

    @FXML
    private JFXButton btnTipos;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<Bicicleta> tblBicicletas;
    @FXML
    private TableColumn<Bicicleta, Long> colIdBicicleta;
    @FXML
    private TableColumn<Bicicleta, String> colCodigoBicicleta;
    @FXML
    private TableColumn<Bicicleta, String> colTipoBicicleta;
    @FXML
    private TableColumn<Bicicleta, String> colEstadoBicicleta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        //Asignar los datos en las columnas
        colIdBicicleta.setCellValueFactory(new PropertyValueFactory<>("idBicicleta"));
        colCodigoBicicleta.setCellValueFactory(new PropertyValueFactory<>("codigoBicicleta"));
        colEstadoBicicleta.setCellValueFactory(new PropertyValueFactory<>("statusBicicleta"));
        colTipoBicicleta.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Bicicleta, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Bicicleta, String> bicicletaStringCellDataFeatures) {
                ReadOnlyObjectWrapper salida = new ReadOnlyObjectWrapper(
                        bicicletaStringCellDataFeatures.getValue().getTieneTipoBicicleta().getNombreTipoBicicleta()
                );
                return salida;
            }
        });
        //Lockeamos los botones de editar y eliminar si no hay ningun objeto seleccionado
        btnEdit.disableProperty().bind(tblBicicletas.getSelectionModel().selectedItemProperty().isNull());
        btnDelete.disableProperty().bind(btnEdit.disableProperty());
    }

    private void cargarDatos() {
        tblBicicletas.getItems().clear();
        tblBicicletas.getItems().addAll(BicicletaDAO.listarBicicletas());
    }

    @FXML
    void NewBicycle(ActionEvent event) throws IOException {
        Map datos = escenario("/Vista/CRUD/bicicletaCRUD.fxml");
        Stage stage = (Stage)datos.get("escenario");
        stage.showAndWait();
        cargarDatos();
    }

    @FXML
    void EditBicycle(ActionEvent event) throws IOException {
        Map datos = escenario("/Vista/CRUD/bicicletaCRUD.fxml");
        BicicletaCRUDController controlador = (BicicletaCRUDController) datos.get("controlador");
        controlador.setBicicleta(tblBicicletas.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)datos.get("escenario");
        stage.showAndWait();
        cargarDatos();
    }

    @FXML
    void DeleteBicycle(ActionEvent event) throws IOException {
        Alert diaglo = new Alert(Alert.AlertType.CONFIRMATION);
        diaglo.setTitle("Eliminar");
        diaglo.setHeaderText(null);
        diaglo.setContentText("¿Esta seguro que desea eliminar la bicicleta de manera permanente?");
        Optional<ButtonType> resultado = diaglo.showAndWait();
        if (resultado.get() == ButtonType.OK){
            BicicletaDAO.eliminar(tblBicicletas.getSelectionModel().getSelectedItem());
        }
        cargarDatos();
    }

    public Map escenario(String path){
        Stage stage = new Stage();
        BicicletaCRUDController controlador = null;
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

    double Xval, Yval;
    @FXML
    void Tipos(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/Vista/tipoBicicleta.fxml"));
            Parent root= (Parent)fxmlLoader.load();
            Stage tipos= new Stage();
            tipos.setResizable(false);
            tipos.initStyle(StageStyle.TRANSPARENT);
            tipos.setScene(new Scene(root));
            tipos.show();

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Xval = event.getSceneX();
                    Yval = event.getSceneY();
                }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tipos.setX(event.getScreenX() - Xval);
                    tipos.setY(event.getScreenY() - Yval);
                }
            });
        }catch (IOException e){}
    }
}