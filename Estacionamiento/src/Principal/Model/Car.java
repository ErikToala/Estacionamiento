package Principal.Model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Car extends Observable implements Runnable {

    private Semaphore mutex;
    private Semaphore door;
    private Gate gate;
    private Random random;
    private Color color;
    private Image image;


    public Car(Semaphore mutex, Semaphore door, Gate gate,Image image) {
        this.mutex = mutex;
        //this.access_car = access_car;
        this.gate = gate;
        this.door = door;
        this.image = image;
        random = new Random(System.currentTimeMillis());
    }

    public String getColorString(){
        String valor = " ";
        if(color.toString().equals("0xd2691eff")){
            valor= "Chocolate";
        }
        if(color.toString().equals("0x000000ff")){
            valor= "Negro";
        }
        if(color.toString().equals("0xffff00ff")){
            valor= "Amarillo";
        }
        if(color.toString().equals("0x008000ff")){
            valor= "Verde";
        }
        if(color.toString().equals("0x0000ffff")){
            valor= "Azul";
        }
        if(color.toString().equals("0x00ffffff")){
            valor= "Aqua";
        }
        if(color.toString().equals("0x8a2be2ff")){
            valor= "Blueviolet";
        }

        return valor;
    }

    @Override
    public void run() {
        try {
            door.acquire();
            //System.out.println("-----Door Bloqueado  =" + door.availablePermits());
            if(Config.numberCars == Config.total_cars){
                    Config.espacios=0;
                try {
                    //System.out.println("-----Se lleno---------" );
                    mutex.acquire();
                    //System.out.println("MUTEX BLOQUE TODOS"+ + mutex.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(Config.numberCars < Config.total_cars){
                door.release();
                Config.numberCars++;
                gate.entrada("Entro ");
                Config.espacios--;
                //System.out.println("-----Door Desbloqueado  =" + door.availablePermits());
                gate.arriveCar();
                setChanged();
                notifyObservers(1);
                try {
                    Thread.sleep(random.nextInt(3000+1000)+1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gate.leaveCar();
                door.release();
                gate.entrada("Salio ");
                mutex.release();
                Config.numberCars--;
                Config.espacios++;
                mutex.acquire();
                setChanged();
                notifyObservers(0);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public Color getColor() {
        return color;
    }

    public Image getImage() {
        return image;
    }
}


