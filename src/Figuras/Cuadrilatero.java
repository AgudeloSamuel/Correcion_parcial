package Figuras;
import Modelo.Punto;
import Modelo.Figura;
import java.util.List;

public class Cuadrilatero extends Figura {
    private double lado1;
    private double lado2;
    private double lado3;
    private double lado4;

    public Cuadrilatero(List<Punto> puntos, double lado1, double lado2, double lado3, double lado4) {
        super(puntos);
        if (puntos.size() != 4) {
            throw new IllegalArgumentException("El cuadrilátero debe tener 4 puntos");
        }
        if (lado1 <= 0 || lado2 <= 0 || lado3 <= 0 || lado4 <= 0) {
            throw new IllegalArgumentException("Los lados del cuadrilátero deben ser mayores que cero");
        }
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        this.lado4 = lado4;
    }

    // Fórmula de Brahmagupta (exacta para rectángulos, cuadrados y cuadriláteros cíclicos)
    @Override
    public double calcularArea() {
        double s = calcularPerimetro() / 2;
        return Math.sqrt((s - lado1) * (s - lado2) * (s - lado3) * (s - lado4));
    }

    @Override
    public double calcularPerimetro() {
        return lado1 + lado2 + lado3 + lado4;
    }

    // Suma de las distancias de sus cuatro puntos al centro del plano
    @Override
    public double dimensionar() {
        double suma = 0;
        for (Punto p : puntos) {
            suma += Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());
        }
        return suma;
    }

    @Override
    public String getDimensiones() {
        return "lado1 = " + lado1 + ", lado2 = " + lado2 + ", lado3 = " + lado3 + ", lado4 = " + lado4;
    }
}
