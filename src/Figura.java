import java.util.ArrayList;
import java.util.List;

public abstract class Figura {
    public static List<Figura> lista = new ArrayList<>();
    protected List<Punto> puntos;

    public Figura(List<Punto> puntos) {
        if (puntos == null || puntos.isEmpty()) {
            throw new IllegalArgumentException("La figura debe tener al menos un punto");
        }
        this.puntos = new ArrayList<>(puntos);
    }

    public void desplazar(double x, double y) {
        for (Punto p : puntos) {
            p.desplazar(x, y);
        }
    }

    public abstract double calcularArea();

    public abstract double calcularPerimetro();

    public abstract double dimensionar();

    public String getTipo() {
        return getClass().getSimpleName();
    }

    public abstract String getDimensiones();
}
