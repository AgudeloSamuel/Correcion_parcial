package Interface;

import Modelo.Figura;
import Modelo.Punto;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Lienzo extends JPanel {
    private static final int ESCALA = 25;

    private final List<Figura> figuras;
    private List<Figura> seleccionadas = new ArrayList<>();

    public Lienzo(List<Figura> figuras) {
        this.figuras = figuras;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(450, 450));
    }

    public void setSeleccionadas(List<Figura> seleccionadas) {
        this.seleccionadas = seleccionadas;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        dibujarPlano(g2);
        for (Figura figura : figuras) {
            dibujarFigura(g2, figura, seleccionadas.contains(figura));
        }
    }

    private int pantallaX(double x) {
        return getWidth() / 2 + (int) Math.round(x * ESCALA);
    }

    private int pantallaY(double y) {
        return getHeight() / 2 - (int) Math.round(y * ESCALA);
    }

    private void dibujarPlano(Graphics2D g) {
        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;

        g.setColor(new Color(230, 230, 230));
        for (int x = centroX % ESCALA; x < getWidth(); x += ESCALA) {
            g.drawLine(x, 0, x, getHeight());
        }
        for (int y = centroY % ESCALA; y < getHeight(); y += ESCALA) {
            g.drawLine(0, y, getWidth(), y);
        }

        g.setColor(Color.GRAY);
        g.drawLine(0, centroY, getWidth(), centroY);
        g.drawLine(centroX, 0, centroX, getHeight());
    }

    private void dibujarFigura(Graphics2D g, Figura figura, boolean resaltada) {
        List<Punto> puntos = figura.getPuntos();
        Color relleno = resaltada ? new Color(255, 140, 0, 110) : new Color(70, 130, 230, 70);
        Color borde = resaltada ? new Color(220, 100, 0) : new Color(40, 90, 200);
        g.setStroke(new BasicStroke(resaltada ? 3f : 1.5f));

        if (puntos.size() == 1) {
            Punto centro = puntos.get(0);
            double radio = Math.sqrt(figura.calcularArea() / Math.PI);
            int r = (int) Math.round(radio * ESCALA);
            int x = pantallaX(centro.getX()) - r;
            int y = pantallaY(centro.getY()) - r;
            g.setColor(relleno);
            g.fillOval(x, y, 2 * r, 2 * r);
            g.setColor(borde);
            g.drawOval(x, y, 2 * r, 2 * r);
        } else {
            Polygon poligono = new Polygon();
            for (Punto p : puntos) {
                poligono.addPoint(pantallaX(p.getX()), pantallaY(p.getY()));
            }
            g.setColor(relleno);
            g.fillPolygon(poligono);
            g.setColor(borde);
            g.drawPolygon(poligono);
        }

        for (Punto p : puntos) {
            g.fillOval(pantallaX(p.getX()) - 3, pantallaY(p.getY()) - 3, 6, 6);
        }
        Punto primero = puntos.get(0);
        g.setColor(Color.BLACK);
        g.drawString(figura.getTipo(), pantallaX(primero.getX()) + 5, pantallaY(primero.getY()) - 5);
    }
}