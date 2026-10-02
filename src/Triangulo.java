import java.util.List;

public class Triangulo extends Figura {
    private double altura;
    private double lado1;
    private double lado2;
    private double base;

    public Triangulo(List<Punto> puntos, double base, double altura, double lado1, double lado2) {
        super(puntos);
        if (puntos.size() != 3) {
            throw new IllegalArgumentException("El triángulo debe tener 3 puntos");
        }
        if (base <= 0 || altura <= 0 || lado1 <= 0 || lado2 <= 0) {
            throw new IllegalArgumentException("Las dimensiones del triángulo deben ser mayores que cero");
        }
        this.base = base;
        this.altura = altura;
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }

    @Override
    public double calcularPerimetro() {
        return base + lado1 + lado2;
    }

    @Override
    public double dimensionar() {
        return calcularPerimetro();
    }

    @Override
    public String getDimensiones() {
        return "base = " + base + ", altura = " + altura + ", lado1 = " + lado1 + ", lado2 = " + lado2;
    }
}

