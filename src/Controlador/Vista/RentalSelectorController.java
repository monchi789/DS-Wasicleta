package Controlador.Vista;

import Controlador.DAO.AlquilerDAO;
import Controlador.DAO.TrabajadoresDAO;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RentalSelectorController implements Initializable {
    @FXML
    public JFXButton btnReporteAlquiler;

    @FXML
    private Pane panel1;

    @FXML
    private Pane icon1;

    @FXML
    private Pane panel2;

    @FXML
    private Pane icon2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void NewUser(MouseEvent event) throws IOException{
        loadPage("NewUser");
    }

    @FXML
    void FrequentUser(MouseEvent event) throws IOException {
        loadPage("FrequentUser");
    }

    //Movimiento
    double Xval, Yval;

    private  void loadPage(String page) throws IOException {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/Vista/Rental/"+page+".fxml"));
        Parent root= (Parent)fxmlLoader.load();
        Stage user= new Stage();
        user.setResizable(false);
        user.initStyle(StageStyle.TRANSPARENT);
        user.setScene(new Scene(root));
        user.show();

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
                user.setX(event.getScreenX() - Xval);
                user.setY(event.getScreenY() - Yval);
            }
        });
    }

    public void ReporteAlquiler(ActionEvent actionEvent) {

        try {
            JasperReport reporteAlquiler = (JasperReport) JRLoader.loadObject(Objects.requireNonNull(getClass().getResource("/Vista/Reporte/ReporteAlquiler.jasper")));
            JRBeanCollectionDataSource datos = new JRBeanCollectionDataSource(AlquilerDAO.listarReporteAlquiler());
            JasperPrint jprint = JasperFillManager.fillReport(reporteAlquiler,null, datos);
            JasperViewer jviewer = new JasperViewer(jprint);
            jviewer.setVisible(true);
        }catch (JRException e){
            e.printStackTrace();
        }
       

    }

}



