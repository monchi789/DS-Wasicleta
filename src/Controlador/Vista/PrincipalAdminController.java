package Controlador.Vista;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalAdminController extends LoginController implements Initializable {

    @FXML
    private ToggleButton btnAlquiler;
    @FXML
    private ToggleGroup MenuButtons;
    @FXML
    private ToggleButton btnHistorial;
    @FXML
    private ToggleButton btnClientesFrecuentes;
    @FXML
    private ToggleButton btnTrabajadores;
    @FXML
    private ToggleButton btnBicicletas;
    @FXML
    private VBox vboxShow;
    @FXML
    private FontAwesomeIconView iconTitle;
    @FXML
    private Label lblTitle;
    @FXML
    private Button btnCloseWindow;
    @FXML
    private Button btnMaximizeWindow;
    @FXML
    private Button btnMinimizeWindow;
    @FXML
    private Button btnLogOut;
    @FXML
    private Label lblUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblUsuario.setText(nombreUsuario);
    }

    @FXML
    void Alquiler(ActionEvent event) {
        loadPage(event, "selectorAlquiler");
        lblTitle.setText(null);
        iconTitle.setVisible(false);
        if (vboxShow.getChildren().size() >2){
            lblTitle.setText("Alquiler");
            iconTitle.setVisible(true);
            iconTitle.setGlyphName("USD");
        }
    }

    @FXML
    void Historial(ActionEvent event) {
        loadPage(event, "historial");
        lblTitle.setText(null);
        iconTitle.setVisible(false);
        if (vboxShow.getChildren().size() >2){
            lblTitle.setText("Historial");
            iconTitle.setVisible(true);
            iconTitle.setGlyphName("BOOK");
        }
    }

    @FXML
    void ClientesFrecuentes(ActionEvent event) {
        loadPage(event, "cliente");
        lblTitle.setText(null);
        iconTitle.setVisible(false);
        if (vboxShow.getChildren().size() >2){
            lblTitle.setText("Clientes Frecuentes");
            iconTitle.setVisible(true);
            iconTitle.setGlyphName("GROUP");
        }
    }

    @FXML
    void Trabajadores(ActionEvent event) {
        loadPage(event, "trabajadores");
        lblTitle.setText(null);
        iconTitle.setVisible(false);
        if (vboxShow.getChildren().size() >2){
            lblTitle.setText("Trabajadores");
            iconTitle.setVisible(true);
            iconTitle.setGlyphName("WRENCH");
        }
    }

    @FXML
    void Bicicletas(ActionEvent event) {
        loadPage(event, "bicicleta");
        lblTitle.setText(null);
        iconTitle.setVisible(false);
        if (vboxShow.getChildren().size() >2){
            lblTitle.setText("Bicicleta");
            iconTitle.setVisible(true);
            iconTitle.setGlyphName("BICYCLE");
        }
    }

    public void loadPage(ActionEvent event, String page){
        if (vboxShow.getChildren().size() >2){
            vboxShow.getChildren().remove(2);
        }
        if (((ToggleButton)event.getSource()).isSelected()){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Vista/"+page+".fxml"));
                vboxShow.getChildren().add(root);
                ((VBox)root).prefHeightProperty().bind(vboxShow.heightProperty());
                ((VBox)root).prefWidthProperty().bind(vboxShow.widthProperty());
            }catch (IOException ioException){}
        }
    }

    @FXML
    void MinimizeWindow(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML
    void MaximizeWindow(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).setMaximized(true);
    }

    @FXML
    void CloseWindow(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    double Xval, Yval;
    @FXML
    void LogOut(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        Stage logout = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Vista/login.fxml"));
            Scene scene = new Scene(root);
            logout.initStyle(StageStyle.TRANSPARENT); //Quita la barra
            logout.setScene(scene);
            scene.setFill(Color.TRANSPARENT); //Se necesita para que la escena sea transparente y cargue el FXML
            logout.show();

            //PERMITE MOVER LA VENTANA
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
                    logout.setX(event.getScreenX() - Xval);
                    logout.setY(event.getScreenY() - Yval);
                }
            });
        } catch (Exception e){}
    }
}