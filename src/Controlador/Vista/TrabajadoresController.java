package Controlador.Vista;

import Controlador.DAO.ClienteDAO;
import Controlador.DAO.TrabajadoresDAO;
import Controlador.Vista.CRUDControllers.ClienteCRUDController;
import Controlador.Vista.CRUDControllers.TrabajadorCRUDController;
import Modelo.Trabajador;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class TrabajadoresController implements Initializable {

    public JFXButton btnReporteTrabajador;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnRol;
    @FXML
    private TableView<Trabajador> tblTrabajador;
    @FXML
    private TableColumn<Trabajador, Long> colIdTrabajdor;
    @FXML
    private TableColumn<Trabajador, String> colNombreTrabajador;
    @FXML
    private TableColumn<Trabajador, String> colApellidosTrabajador;
    @FXML
    private TableColumn<Trabajador, String> colNumeroDocumentoTrabajador;
    @FXML
    private TableColumn<Trabajador, String> colCelularTrabajador;
    @FXML
    private TableColumn<Trabajador, String> colCorreoTrabajador;
    @FXML
    private TableColumn<Trabajador, String> colUsuarioTrabajador;
    @FXML
    private TableColumn<Trabajador, String> colContrase??aTrabajador;
    @FXML
    private TableColumn<Trabajador, String> colRolTrabajador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        //Asignar los datos en las columnas
        colIdTrabajdor.setCellValueFactory(new PropertyValueFactory<>("idTrabajador"));
        colNombreTrabajador.setCellValueFactory(new PropertyValueFactory<>("nombreTrabajador"));
        colApellidosTrabajador.setCellValueFactory(new PropertyValueFactory<>("apellidosTrabajador"));
        colNumeroDocumentoTrabajador.setCellValueFactory(new PropertyValueFactory<>("nroDocumentoTrabajador"));
        colCelularTrabajador.setCellValueFactory(new PropertyValueFactory<>("celularTrabajador"));
        colCorreoTrabajador.setCellValueFactory(new PropertyValueFactory<>("correoTrabajador"));
        colUsuarioTrabajador.setCellValueFactory(new PropertyValueFactory<>("usuarioTrabajador"));
        colContrase??aTrabajador.setCellValueFactory(new PropertyValueFactory<>("contrase??aTrabajador"));
        colRolTrabajador.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Trabajador, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Trabajador, String> trabajadorStringCellDataFeatures) {
                ReadOnlyObjectWrapper salida = new ReadOnlyObjectWrapper(
                        trabajadorStringCellDataFeatures.getValue().getTieneRol().getTipoRol()
                );
                return salida;
            }
        });
        //Lockeamos los botones de editar y eliminar si no hay ningun objeto seleccionado
        btnEdit.disableProperty().bind(tblTrabajador.getSelectionModel().selectedItemProperty().isNull());
        btnDelete.disableProperty().bind(btnEdit.disableProperty());
    }

    private void cargarDatos() {
        tblTrabajador.getItems().clear();
        tblTrabajador.getItems().addAll(TrabajadoresDAO.listarTrabajadores());
    }

    @FXML
    void NewT(ActionEvent event) {
        Map datos = escenario("/Vista/CRUD/trabajadorCRUD.fxml");
        Stage stage = (Stage)datos.get("escenario");
        stage.showAndWait();
        cargarDatos();
    }

    @FXML
    void EditT(ActionEvent event) {
        Map datos = escenario("/Vista/CRUD/trabajadorCRUD.fxml");
        TrabajadorCRUDController controlador = (TrabajadorCRUDController) datos.get("controlador");
        controlador.setTrabajador(tblTrabajador.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)datos.get("escenario");
        stage.showAndWait();
        cargarDatos();
    }

    @FXML
    void DeleteT(ActionEvent event) {
        Alert diaglo = new Alert(Alert.AlertType.CONFIRMATION);
        diaglo.setTitle("Eliminar");
        diaglo.setHeaderText(null);
        diaglo.setContentText("??Esta seguro que desea eliminar al trabajador de manera permanente?");
        Optional<ButtonType> resultado = diaglo.showAndWait();
        if (resultado.get() == ButtonType.OK){
            TrabajadoresDAO.eliminar(tblTrabajador.getSelectionModel().getSelectedItem());
        }
        cargarDatos();
    }

    public Map escenario(String path){
        Stage stage = new Stage();
        TrabajadorCRUDController controlador = null;
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
    void Roles(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/Vista/rol.fxml"));
            Parent root= (Parent)fxmlLoader.load();
            Stage roles= new Stage();
            roles.setResizable(false);
            roles.initStyle(StageStyle.TRANSPARENT);
            roles.setScene(new Scene(root));
            roles.show();

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
                    roles.setX(event.getScreenX() - Xval);
                    roles.setY(event.getScreenY() - Yval);
                }
            });
        }catch (IOException e){}
    }

    public void reporteTrabajador(ActionEvent actionEvent) {

        try {
            JasperReport reporteTrabajadores = (JasperReport) JRLoader.loadObject(Objects.requireNonNull(getClass().getResource("/Vista/Reporte/ReporteTrabajador.jasper")));
            JRBeanCollectionDataSource datos = new JRBeanCollectionDataSource(TrabajadoresDAO.listarReporteTrabajador());
            JasperPrint jprint = JasperFillManager.fillReport(reporteTrabajadores,null, datos);
            JasperViewer jviewer = new JasperViewer(jprint);
            jviewer.setVisible(true);
        }catch (JRException e){
            e.printStackTrace();
        }

    }
}
