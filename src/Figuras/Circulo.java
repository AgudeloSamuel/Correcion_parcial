package Figuras;
import Modelo.Punto;
import Modelo.Figura;
import java.util.List;

public class Circulo extends Figura {
    private double radio;

    public Circulo(Punto centro, double radio) {
        super(List.of(centro));
        setRadio(radio);
    }

    public void setRadio(double radio) {
        if (radio <= 0) {
            throw new IllegalArgumentException("El radio debe ser mayor que cero");
        }
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    @Override
    public double dimensionar() {
        return calcularArea();
    }

    @Override
    public String getDimensiones() {
        return "radio = " + radio;
    }
}
