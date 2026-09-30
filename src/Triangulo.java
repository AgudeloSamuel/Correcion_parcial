public class Triangulo implements Figura {
    float base ;
    float altura;
    float L1;
    float L2;
    float L3;

    public Triangulo(float base, float altura, float L1, float L2, float L3) {
        this.base = base;
        this.altura= altura;
        this.L1=L1;
        this.L2=L2;
        this.L3=L3;

    }

    public float getBase() {
        return base;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getL1() {
        return L1;
    }

    public void setL1(float l1) {
        L1 = l1;
    }

    public float getL2() {
        return L2;
    }

    public void setL2(float l2) {
        L2 = l2;
    }

    public float getL3() {
        return L3;
    }

    public void setL3(float l3) {
        L3 = l3;
    }
    @Override
    public void calcularPerimetro() {
        System.out.println("el perimetro del triangulo es: "+ L1+L2+L3);
    }
    @Override
    public void calcularArea() {
        System.out.println("el area del triangulo es: " + (base*altura)/2 );
    }
}

