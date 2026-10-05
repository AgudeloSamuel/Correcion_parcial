package Interface;

import Modelo.Punto;
import java.util.ArrayList;
import java.util.List;

public class LectorPuntos {

    public static List<Punto> leer(String texto, int cantidad) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar los puntos con el formato x,y; x,y");
        }
        String[] partes = texto.split(";");
        if (partes.length != cantidad) {
            throw new IllegalArgumentException("Debe ingresar exactamente " + cantidad + " punto(s) con el formato x,y; x,y");
        }
        List<Punto> puntos = new ArrayList<>();
        for (String parte : partes) {
            String[] xy = parte.trim().split(",");
            if (xy.length != 2) {
                throw new IllegalArgumentException("Punto con formato inválido: " + parte.trim());
            }
            double x = leerNumero(xy[0], "La coordenada X");
            double y = leerNumero(xy[1], "La coordenada Y");
            puntos.add(new Punto(x, y));
        }
        return puntos;
    }

    public static double leerNumero(String texto, String nombre) {
        try {
            return Double.parseDouble(texto.trim());
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException(nombre + " debe ser un número válido (use punto para decimales)");
        }
    }
}
