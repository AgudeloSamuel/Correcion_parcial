
package Interface;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private JPanel panelPrincipal;
    private JButton btnCirculo;
    private JButton btnTriangulo;
    private JButton btnCuadrilatero;
    private JButton btnPentagono;
    private JList<String> listaFiguras;
    private JTextArea txtInformacion;
    private JTextField txtDx;
    private JTextField txtDy;
    private JButton btnDesplazar;
    private JButton btnComparar;
    private JPanel panelDibujo;

    private final DefaultListModel<String> modeloLista =
            new DefaultListModel<>();

    private final List<Figura> figuras = new ArrayList<>();
    private Lienzo lienzo;

    public VentanaPrincipal() {
        setTitle("Panel de Figuras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crearInterfaz();
        configurarEventos();

        setMinimumSize(new Dimension(950, 650));
        setSize(1100, 750);
        setLocationRelativeTo(null);



        listaFiguras.setSelectedIndex(0);
    }

    private void crearInterfaz() {

        panelPrincipal = new JPanel(new BorderLayout(12, 12));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        );

        setContentPane(panelPrincipal);


        btnCirculo = new JButton("Círculo");
        btnTriangulo = new JButton("Triángulo");
        btnCuadrilatero = new JButton("Cuadrilátero");
        btnPentagono = new JButton("Pentágono");

        JPanel panelBotones = new JPanel(
                new GridLayout(1, 4, 10, 0)
        );

        panelBotones.add(btnCirculo);
        panelBotones.add(btnTriangulo);
        panelBotones.add(btnCuadrilatero);
        panelBotones.add(btnPentagono);

        panelPrincipal.add(panelBotones, BorderLayout.NORTH);


        listaFiguras = new JList<>(modeloLista);
        listaFiguras.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );
        listaFiguras.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JScrollPane scrollLista = new JScrollPane(listaFiguras);
        scrollLista.setBorder(new TitledBorder("Figuras"));


        txtInformacion = new JTextArea();
        txtInformacion.setEditable(false);
        txtInformacion.setLineWrap(true);
        txtInformacion.setWrapStyleWord(true);
        txtInformacion.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtInformacion.setText(
                "Selecciona una figura para ver su información."
        );

        JScrollPane scrollInformacion =
                new JScrollPane(txtInformacion);

        scrollInformacion.setBorder(
                new TitledBorder("Información")
        );

        JPanel panelIzquierdo = new JPanel(
                new GridLayout(2, 1, 0, 10)
        );

        panelIzquierdo.setPreferredSize(
                new Dimension(280, 450)
        );

        panelIzquierdo.add(scrollLista);
        panelIzquierdo.add(scrollInformacion);


        panelDibujo = new JPanel(new BorderLayout());
        panelDibujo.setBorder(
                new TitledBorder("Área de dibujo")
        );
        panelDibujo.setPreferredSize(
                new Dimension(450, 450)
        );

        lienzo = new Lienzo();
        panelDibujo.add(lienzo, BorderLayout.CENTER);

        JPanel panelCentro = new JPanel(new BorderLayout(12, 0));
        panelCentro.add(panelIzquierdo, BorderLayout.WEST);
        panelCentro.add(panelDibujo, BorderLayout.CENTER);

        panelPrincipal.add(panelCentro, BorderLayout.CENTER);


        JLabel lblDx = new JLabel("Desplazar X:");
        JLabel lblDy = new JLabel("Desplazar Y:");

        txtDx = new JTextField("0", 6);
        txtDy = new JTextField("0", 6);

        btnDesplazar = new JButton("Desplazar");
        btnComparar = new JButton("Comparar");

        JPanel panelInferior = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 4)
        );

        panelInferior.add(lblDx);
        panelInferior.add(txtDx);
        panelInferior.add(lblDy);
        panelInferior.add(txtDy);
        panelInferior.add(btnDesplazar);
        panelInferior.add(btnComparar);

        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
    }

    private void configurarEventos() {

        btnCirculo.addActionListener(e ->
                agregarFigura(new Figura("Círculo", 0, 0)));

        btnTriangulo.addActionListener(e ->
                agregarFigura(new Figura("Triángulo", 0, 0)));

        btnCuadrilatero.addActionListener(e ->
                agregarFigura(new Figura("Cuadrilátero", 0, 0)));

        btnPentagono.addActionListener(e ->
                agregarFigura(new Figura("Pentágono", 0, 0)));

        listaFiguras.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                actualizarInformacion();
            }
        });

        btnDesplazar.addActionListener(e ->
                desplazarFigura());

        btnComparar.addActionListener(e ->
                compararFiguras());
    }

    private void agregarFigura(Figura figura) {

        figuras.add(figura);

        modeloLista.addElement(
                figura.nombre + " (X=" + figura.dx +
                        ", Y=" + figura.dy + ")"
        );

        listaFiguras.setSelectedIndex(
                modeloLista.size() - 1
        );

        actualizarInformacion();
        lienzo.repaint();
    }

    private void actualizarInformacion() {

        int indice = listaFiguras.getSelectedIndex();

        if (indice < 0 || indice >= figuras.size()) {
            txtInformacion.setText(
                    "Selecciona una figura para ver su información."
            );
            return;
        }

        Figura f = figuras.get(indice);

        txtInformacion.setText(
                "Figura: " + f.nombre + "\n" +
                        "Desplazamiento X: " + f.dx + "\n" +
                        "Desplazamiento Y: " + f.dy + "\n" +
                        "Área: " + String.format("%.2f", f.area()) + "\n" +
                        "Perímetro: " +
                        String.format("%.2f", f.perimetro())
        );
    }

    private void desplazarFigura() {

        int indice = listaFiguras.getSelectedIndex();

        if (indice < 0 || indice >= figuras.size()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona una figura de la lista."
            );
            return;
        }

        try {
            int dx = Integer.parseInt(txtDx.getText().trim());
            int dy = Integer.parseInt(txtDy.getText().trim());

            Figura f = figuras.get(indice);

            f.dx += dx;
            f.dy += dy;

            modeloLista.setElementAt(
                    f.nombre + " (X=" + f.dx +
                            ", Y=" + f.dy + ")",
                    indice
            );

            listaFiguras.setSelectedIndex(indice);

            actualizarInformacion();
            lienzo.repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Introduce números enteros válidos.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void compararFiguras() {

        if (figuras.size() < 2) {
            JOptionPane.showMessageDialog(
                    this,
                    "Agrega al menos dos figuras."
            );
            return;
        }

        Figura mayor = figuras.get(0);

        for (Figura f : figuras) {
            if (f.area() > mayor.area()) {
                mayor = f;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Figura con mayor área: " + mayor.nombre +
                        "\nÁrea: " +
                        String.format("%.2f", mayor.area()),
                "Comparación",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // MODELO DE FIGURA
    private static class Figura {

        String nombre;
        int dx;
        int dy;
        Color color;

        Figura(String nombre, int dx, int dy) {
            this.nombre = nombre;
            this.dx = dx;
            this.dy = dy;

            switch (nombre) {
                case "Círculo":
                    color = new Color(45, 105, 220);
                    break;
                case "Triángulo":
                    color = new Color(35, 160, 75);
                    break;
                case "Cuadrilátero":
                    color = new Color(220, 70, 65);
                    break;
                case "Pentágono":
                    color = new Color(230, 195, 30);
                    break;
                default:
                    color = Color.GRAY;
            }
        }

        double area() {
            switch (nombre) {
                case "Círculo":
                    return Math.PI * 50 * 50;
                case "Triángulo":
                    return Math.sqrt(3) / 4 * 100 * 100;
                case "Cuadrilátero":
                    return 10000;
                case "Pentágono":
                    return 5 * 50 * 50 *
                            Math.sin(2 * Math.PI / 5) / 2;
                default:
                    return 0;
            }
        }

        double perimetro() {
            switch (nombre) {
                case "Círculo":
                    return 2 * Math.PI * 50;
                case "Triángulo":
                    return 300;
                case "Cuadrilátero":
                    return 400;
                case "Pentágono":
                    return 10 * 50 * Math.sin(Math.PI / 5);
                default:
                    return 0;
            }
        }
    }


    private class Lienzo extends JPanel {

        Lienzo() {
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(450, 450));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int centroX = getWidth() / 2;
            int centroY = getHeight() / 2;

            for (Figura f : figuras) {

                int x = centroX + f.dx;
                int y = centroY + f.dy;

                Shape forma = crearForma(f.nombre, x, y);

                g2.setColor(f.color);
                g2.fill(forma);

                g2.setColor(f.color.darker());
                g2.setStroke(new BasicStroke(2));
                g2.draw(forma);
            }

            g2.dispose();
        }

        private Shape crearForma(String nombre, int x, int y) {

            switch (nombre) {

                case "Círculo":
                    return new Ellipse2D.Double(
                            x - 30, y - 30, 60, 60
                    );

                case "Triángulo":
                    Polygon triangulo = new Polygon();
                    triangulo.addPoint(x, y - 38);
                    triangulo.addPoint(x - 35, y + 30);
                    triangulo.addPoint(x + 35, y + 30);
                    return triangulo;

                case "Cuadrilátero":
                    return new Rectangle2D.Double(
                            x - 30, y - 30, 60, 60
                    );

                case "Pentágono":
                    Polygon pentagono = new Polygon();

                    for (int i = 0; i < 5; i++) {
                        double angulo =
                                -Math.PI / 2 + i * 2 * Math.PI / 5;

                        pentagono.addPoint(
                                x + (int) (35 * Math.cos(angulo)),
                                y + (int) (35 * Math.sin(angulo))
                        );
                    }

                    return pentagono;

                default:
                    return new Rectangle2D.Double(x, y, 1, 1);
            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
            }

            new VentanaPrincipal().setVisible(true);
        });
    }
}
