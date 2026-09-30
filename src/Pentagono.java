public class Pentagono implements Figura {
    float lado;
    float a;
    float Perimetro;

    public Pentagono(float lado, float a, float Perimetro) {
        this.lado = lado;
        this.a=a ;
        this.Perimetro=5*lado;
    }

    public float getLado() {
        return lado;
    }

    public void setLado(float lado) {
        this.lado = lado;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getPerimetro() {
        return Perimetro;
    }

    public void setPerimetro(float perimetro) {
        Perimetro = perimetro;
    }
    @Override
    public void calcularPerimetro() {
        System.out.println("el perimetro del Pentagono es: "+ 5*lado);
    }
    @Override
    public void calcularArea() {
        System.out.println("el area del Pentagono es: " + ( Perimetro+ a) / 2);
    }
}
