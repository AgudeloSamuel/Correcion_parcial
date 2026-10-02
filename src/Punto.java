public class Punto {
    private double x;
    private double y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void desplazar(double x, double y) {
        this.x += x;
        this.y += y;
    }

    // En el diagrama retorna void, por eso imprime la distancia al origen
    public void distancia() {
        double d = Math.sqrt(x * x + y * y);
        System.out.printf("Distancia de (%.2f, %.2f) al origen: %.2f%n", x, y, d);
    }
}