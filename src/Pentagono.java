import java.util.List;

public class Pentagono extends Figura {
    private double lado;

    public Pentagono(List<Punto> puntos, double lado) {
        super(puntos);
        if (puntos.size() != 5) {
            throw new IllegalArgumentException("El pentágono debe tener 5 puntos");
        }
        if (lado <= 0) {
            throw new IllegalArgumentException("El lado del pentágono debe ser mayor que cero");
        }
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        double apotema = lado / (2 * Math.tan(Math.PI / 5));
        return (calcularPerimetro() * apotema) / 2;
    }

    @Override
    public double calcularPerimetro() {
        return 5 * lado;
    }

    @Override
    public double dimensionar() {
        double suma = 0;
        for (Punto p : puntos) {
            suma += p.getX();
        }
        return suma;
    }

    @Override
    public String getDimensiones() {
        return "lado = " + lado;
    }
}
