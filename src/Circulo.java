public class Circulo implements Figura {


    float radio;

    public Circulo( float radio ) {

        this.radio= radio;
    }


    public float getRadio() {
        return radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }


    @Override
    public void calcularArea() {
        System.out.println("el area del circulo es: "+ 180*(radio*radio));
    }
    @Override
    public void calcularPerimetro() {
        System.out.println("el perimetro del circulo es: "+ 2*180*radio);
    }
}
