package Controlador;

import Modelo.Repositorio.Persistencia;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        Persistencia.getInstancia();
        launch();
    }

    //Movimiento
    double Xval, Yval;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Vista/login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.initStyle(StageStyle.TRANSPARENT); //Quita la barra
            primaryStage.setScene(scene);
            scene.setFill(Color.TRANSPARENT); //Se necesita para que la escena sea transparente y cargue el FXML
            primaryStage.show();

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
                    primaryStage.setX(event.getScreenX() - Xval);
                    primaryStage.setY(event.getScreenY() - Yval);
                }
            });


        } catch (Exception e){

        }
    }
}
