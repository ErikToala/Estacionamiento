package Principal.Model;

import java.util.Random;

public class CreateCar implements Runnable {

    private Car[] carros;
    private Random random = new Random(System.currentTimeMillis());
    private int cantHilos = 100;

    public CreateCar(Car[] carros) {
        this.carros = carros;
    }

    @Override
    public void run() {
        Car car;
        for (int i = 0; i < cantHilos; i++) {
            car = carros[i];
            new Thread(car,"Carro "+ i).start();
            try {
                Thread.sleep(random.nextInt(300+100) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
