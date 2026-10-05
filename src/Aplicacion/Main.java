package Aplicacion;
import Modelo.Punto;
import Figuras.Circulo;
import Figuras.Cuadrilatero;
import Figuras.Pentagono;
import Figuras.Triangulo;
import Modelo.Figura;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Figura.lista.add(new Circulo(new Punto(0, 0), 3));

        Figura.lista.add(new Triangulo(
                List.of(new Punto(0, 0), new Punto(4, 0), new Punto(0, 3)),
                4, 3, 3, 5));

        Figura.lista.add(new Cuadrilatero(
                List.of(new Punto(1, 1), new Punto(5, 1), new Punto(5, 4), new Punto(1, 4)),
                4, 3, 4, 3));

        Figura.lista.add(new Pentagono(
                List.of(new Punto(2, 4), new Punto(3.9, 2.6), new Punto(3.2, 0.4),
                        new Punto(0.8, 0.4), new Punto(0.1, 2.6)),
                2.35));

        System.out.println("=== Información de las figuras ===");
        for (Figura f : Figura.lista) {
            mostrar(f);
        }

        System.out.println("=== Desplazamiento del cuadrilátero (+2, +2) ===");
        Figura cuadrilatero = Figura.lista.get(2);
        System.out.printf("Dimensionar antes: %.2f%n", cuadrilatero.dimensionar());
        cuadrilatero.desplazar(2, 2);
        System.out.printf("Dimensionar después: %.2f%n%n", cuadrilatero.dimensionar());

        System.out.println("=== Clase Modelo.Punto ===");
        Punto p = new Punto(3, 4);
        p.distancia();
        p.desplazar(1, 1);
        p.distancia();
        System.out.println();

        System.out.println("=== Dimensiones no válidas ===");
        try {
            new Circulo(new Punto(0, 0), -2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            Circulo c = (Circulo) Figura.lista.get(0);
            c.setRadio(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            new Pentagono(List.of(new Punto(0, 0)), 2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void mostrar(Figura f) {
        System.out.println("Tipo: " + f.getTipo());
        System.out.println("Dimensiones: " + f.getDimensiones());
        System.out.printf("Área: %.2f%n", f.calcularArea());
        System.out.printf("Perímetro: %.2f%n", f.calcularPerimetro());
        System.out.printf("Dimensionar: %.2f%n%n", f.dimensionar());
    }
}