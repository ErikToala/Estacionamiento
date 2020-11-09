package Principal.Model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.Random;

public class ImageCar {

    private Random random = new Random();

    public Image getImagenes(){
        File file = new File("src/Principal/Recursos/carro.png");
        File file2 = new File("src/Principal/Recursos/CarroB.png");
        File file3 = new File("src/Principal/Recursos/carroC.png");
        File file4 = new File("src/Principal/Recursos/CarroD.png");
        File file5 = new File("src/Principal/Recursos/CarroE.png");
        File file6 = new File("src/Principal/Recursos/CarroF.png");
        File file7 = new File("src/Principal/Recursos/CarroG.png");
        Image[] imag = {new Image(file.toURI().toString()), new Image(file2.toURI().toString()), new Image(file3.toURI().toString()), new Image(file4.toURI().toString()), new Image(file5.toURI().toString())
                , new Image(file6.toURI().toString()), new Image(file7.toURI().toString())};
        Image imagenes = imag[random.nextInt(7)];
        return imagenes;
    }

    public Color getColor(){
        Color[] colores = { Color.CHOCOLATE,
                Color.BLUEVIOLET,
                Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.BLACK};

        Color selecColor = colores[random.nextInt(6)];
        return selecColor;
    }

}
