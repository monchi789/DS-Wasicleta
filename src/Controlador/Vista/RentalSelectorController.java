package Controlador.Vista;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RentalSelectorController implements Initializable {

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
}
