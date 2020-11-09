package Principal.Model;
import java.util.Random;


public class Gate {

    private String [] bufferCar;
    private Random random;


    public Gate() {
        Config.status = false;
        bufferCar = new String[Config.total_cars];
        for(int i=0; i<Config.total_cars; i++){
            bufferCar[i] ="vacio";
        }
        random = new Random(System.currentTimeMillis());
    }
    public synchronized void entrada(String dato){
        System.out.println(dato + Thread.currentThread().getName());
    }

    public synchronized void arriveCar()  {
            for(int i=0; i<bufferCar.length; i++){
                if(bufferCar[i] == "vacio") {
                    System.out.println("Estacionado "+ Thread.currentThread().getName() + " E"+ i  );
                    bufferCar[i] = Thread.currentThread().getName();
                    Config.verificarPuerta = i;
                    Config.countEntrada++;
                    break;
                }

            }
    }

    public synchronized void leaveCar(){
            for(int i=0; i<bufferCar.length; i++){
                if(bufferCar[i].compareTo(Thread.currentThread().getName()) == 0){
                    bufferCar[i]="vacio";
                    System.out.println("--------------------------------SALIO "+ Thread.currentThread().getName() + " E"+ i);
                    Config.verificarPuerta = i;
                    Config.countSalida++;
                    break;
                }
            }
    }

}
