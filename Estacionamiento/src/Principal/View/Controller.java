package Principal.View;

import Principal.Model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


import java.text.ParseException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.Semaphore;

import static Principal.Model.Config.countEntrada;


public class Controller implements Observer {

    private Car car;
    private int cantHilos = 100;
    private int a,b,c = 0;
    private ImageCar imageCar = new ImageCar();
    ImageView [] carritos = new ImageView[20];


    @FXML
    private AnchorPane canvas;


    @FXML
    private Label countSalida;

    @FXML
    private Label countEntrada;


    @FXML
    public void initialize(){
        for(int y=0; y<20; y++){

            carritos[y] = new ImageView();
            if(y<5){
                    carritos[y].setLayoutX(202+ (68*y)); carritos[y].setLayoutY(400);
                    carritos[y].setFitHeight(66); carritos[y].setFitWidth(46);
                    canvas.getChildren().add(carritos[y]);

            }
            if(y>4 && y<10){
                carritos[y].setLayoutX(202+ (68*a)); carritos[y].setLayoutY(275);
                carritos[y].setFitHeight(66); carritos[y].setFitWidth(46);
                canvas.getChildren().add(carritos[y]);
                a++;
            }
            if(y>9 && y <15){
                carritos[y].setLayoutX(198+ (68*b)); carritos[y].setLayoutY(185);
                carritos[y].setFitHeight(66); carritos[y].setFitWidth(46);
                canvas.getChildren().add(carritos[y]);
                b++;
            }
            if(y>14 && y <20){
                carritos[y].setLayoutX(198+ (68*c)); carritos[y].setLayoutY(60);
                carritos[y].setFitHeight(66); carritos[y].setFitWidth(46);
                canvas.getChildren().add(carritos[y]);
                c++;
            }
            carritos[y].setVisible(false);
        }


    }


    @FXML
    void OnMouseClicked(MouseEvent event){

        Semaphore mutex = new Semaphore(0);
        Semaphore door = new Semaphore(1);
        Gate gate = new Gate();
        Random random = new Random(System.currentTimeMillis());
        Car [] cars = new Car[cantHilos];

        for (int i = 0; i < cantHilos; i++) {
            car = new Car(mutex, door, gate, imageCar.getImagenes());
            car.addObserver(this);
            cars[i] = car;
        }
        Thread newCar = new Thread(new CreateCar(cars));
        newCar.start();

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Car){
           ImageView valor = carritos[Config.verificarPuerta];
           if((Integer)arg == 1){
               try {
                   Thread.sleep(100);
                   Platform.runLater(()->{
                       valor.setVisible(true);
                       valor.setImage(((Car)o).getImage());
                       countEntrada.setText(Integer.toString(Config.countEntrada));
                   });
                   //valor.setVisible(true);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           } else{
               Platform.runLater(() ->{

                   valor.setVisible(false);
                   countSalida.setText(Integer.toString(Config.countSalida));
               });

           }
        }
    }
}
