public class Cuadrilatero implements Figura {
    float L1;
    float L2;
    float L3;
    float L4;

    public Cuadrilatero(float l1, float l2, float l3, float l4) {
        L1 =l1;
        L2=l2;
        L3=l3;
        L4=l4;

    }


    public float getL2() {
        return L2;
    }

    public void setL2(float l2) {
        L2 = l2;
    }

    public float getL1() {
        return L1;
    }

    public void setL1(float l1) {
        L1 = l1;
    }

    public float getL3() {
        return L3;
    }

    public void setL3(float l3) {
        L3 = l3;
    }

    public float getL4() {
        return L4;
    }

    public void setL4(float l4) {
        L4 = l4;
    }
    public void calcularArea() {
        System.out.println("el area del cuadrilatero es: "+ L1*L1);
    }

    public void calcularPerimetro() {
        System.out.println("el perimetro del cuadrilatero es: "+ L1+L2+L3+L4);
    }
}
