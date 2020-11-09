package Principal;

import Principal.Model.Car;
import Principal.Model.Config;
import Principal.Model.Gate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main extends Application {

    private static Car car;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/Estacionamiento.fxml"));
        primaryStage.setTitle("Estacionamiento");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws InterruptedException {
      /*  int cantHilos =2 ;
        Semaphore mutex = new Semaphore(0);
        //Semaphore acceso_carro = new Semaphore(0);
        Semaphore door = new Semaphore(1);
        Gate gate = new Gate();
        Random random = new Random(System.currentTimeMillis());
        //Thread [] hilo = new Thread[Config.total_General];

        for(int i=0; i < cantHilos; i++){
            car = new Car(mutex,door,gate);
            new Thread(car,"Carro "+ i).start();
            Thread.sleep(random.nextInt(400)+100);

        }*/

        //System.out.println("-----ENTRARON----  "+Config.countEntrada);
       // System.out.println("-----SALIERON----  "+Config.countSalida);



        launch(args);
    }
}
