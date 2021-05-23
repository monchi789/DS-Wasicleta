package Controlador.Vista;

import Controlador.DAO.AlquilerDAO;
import Modelo.Alquiler;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistorialController implements Initializable {

    @FXML
    private Button btnBuscar;
    @FXML
    private JFXTextField txtNombreBuscar;
    @FXML
    private Button btnActualizar;
    @FXML
    private TableView<Alquiler> tblHistorial;
    @FXML
    private TableColumn<Alquiler, Long> colIdAlquiler;
    @FXML
    private TableColumn<Alquiler, String> colCodigoAlquiler;
    @FXML
    private TableColumn<Alquiler, String> colFechaAlquiler;
    @FXML
    private TableColumn<Alquiler, String> coltrabajadorAlquiler;
    @FXML
    private TableColumn<Alquiler, String> colBicicletaAlquiler;
    @FXML
    private TableColumn<Alquiler, String> colNombreAlquiler;
    @FXML
    private TableColumn<Alquiler, String> colApellidoAlquiler;
    @FXML
    private TableColumn<Alquiler, String> colNumeroDocumentoAlquiler;
    @FXML
    private TableColumn<Alquiler, String> colCelularAlquiler;
    @FXML
    private TableColumn<Alquiler, String> colCorreoAlquiler;
    @FXML
    private TableColumn<Alquiler, String> colMontoAlquiler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarDatos();
        //Asignar los datos en las columnas
        colIdAlquiler.setCellValueFactory(new PropertyValueFactory<>("idAlquiler"));
        colCodigoAlquiler.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colFechaAlquiler.setCellValueFactory(new PropertyValueFactory<>("fechaAlquiler"));
        colNombreAlquiler.setCellValueFactory(new PropertyValueFactory<>("nombreAlquiler"));
        colApellidoAlquiler.setCellValueFactory(new PropertyValueFactory<>("apellidoAlquiler"));
        colNumeroDocumentoAlquiler.setCellValueFactory(new PropertyValueFactory<>("numeroDocumentoAlquiler"));
        colCelularAlquiler.setCellValueFactory(new PropertyValueFactory<>("celularAlquiler"));
        colCorreoAlquiler.setCellValueFactory(new PropertyValueFactory<>("correoAlquiler"));
        colMontoAlquiler.setCellValueFactory(new PropertyValueFactory<>("montoAlquiler"));
        coltrabajadorAlquiler.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Alquiler, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Alquiler, String> alquilerStringCellDataFeatures) {
                ReadOnlyObjectWrapper salida = new ReadOnlyObjectWrapper(
                        alquilerStringCellDataFeatures.getValue().getPerteneceTrabajador().getUsuarioTrabajador()
                );
                return salida;
            }
        });
        colBicicletaAlquiler.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Alquiler, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Alquiler, String> alquilerStringCellDataFeatures) {
                ReadOnlyObjectWrapper salida = new ReadOnlyObjectWrapper(
                        alquilerStringCellDataFeatures.getValue().getTieneBicicleta().getCodigoBicicleta()
                );
                return salida;
            }
        });
    }

    private void cargarDatos() {
        tblHistorial.getItems().clear();
        tblHistorial.getItems().addAll(AlquilerDAO.listarAlquiler());
    }

    @FXML
    void Buscar(ActionEvent event) {
        List<Alquiler> datosbuscar = AlquilerDAO.buscarXNombre("%"+txtNombreBuscar.getText().trim()+"%");
        cargarBusqueda(datosbuscar);
    }

    private void cargarBusqueda(List<Alquiler> datosbuscar) {
        tblHistorial.getItems().clear();
        tblHistorial.getItems().addAll(datosbuscar);
    }

    @FXML
    void Actualizar(ActionEvent event) {
        cargarDatos();
        txtNombreBuscar.setText(null);
    }
}
