package gUILayer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

public class PantallaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private String title;// título de la ventana
    private JPanel contentPane;// panel principal de la ventana
    private CardLayout layout = new CardLayout();// layout de la ventana

    public PantallaPrincipal(String title) {
        this.title = title;
        this.setTitle(title);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // centrar la ventana en la pantalla
        inItComponents();
    }

    public void inItComponents() {
    // panel principal con BorderLayout
    this.setLayout(new BorderLayout());

    // panel de botones
    JPanel panelBotones = new JPanel();
    JButton btn1 = new JButton("REGISTRO DE USUARIOS");
    JButton btn2 = new JButton("REGISTRO DE LIBROS");
    JButton btn3 = new JButton("PRESTAMO DE LIBROS");
    JButton btn4 = new JButton("DEVOLUCIÓN DE LIBROS");
    JButton btn5 = new JButton("VISUALIZACIÓN DE DATOS");

    panelBotones.add(btn1);
    panelBotones.add(btn2);
    panelBotones.add(btn3);
    panelBotones.add(btn4);
    panelBotones.add(btn5);

    this.add(panelBotones, BorderLayout.NORTH); // muestraa los botones arriba

    // panel de contenido
    contentPane = new JPanel();
    layout = new CardLayout();
    contentPane.setLayout(layout);
    this.add(contentPane, BorderLayout.CENTER);

    // crea tarjetas
    JPanel tarjeta1 = crearTarjeta("REGISTRO DE USUARIOS", Color.CYAN);
    JPanel tarjeta2 = crearTarjeta("REGISTRO DE LIBROS", Color.PINK);
    JPanel tarjeta3 = crearTarjeta("PRESTAMO DE LIBROS", Color.BLUE);
    JPanel tarjeta4 = crearTarjeta("DEVOLUCIÓN DE LIBROS", Color.ORANGE);
    JPanel tarjeta5 = crearTarjeta("VISUALIZACIÓN DE DATOS", Color.MAGENTA);

    // añadir tarjetas al contentPane
    contentPane.add(tarjeta1, "Tarjeta 1");
    contentPane.add(tarjeta2, "Tarjeta 2");
    contentPane.add(tarjeta3, "Tarjeta 3");
    contentPane.add(tarjeta4, "Tarjeta 4");
    contentPane.add(tarjeta5, "Tarjeta 5");

    // actionListeners para cambiar tarjetas
    btn1.addActionListener(e -> layout.show(contentPane, "Tarjeta 1"));
    btn2.addActionListener(e -> layout.show(contentPane, "Tarjeta 2"));
    btn3.addActionListener(e -> layout.show(contentPane, "Tarjeta 3"));
    btn4.addActionListener(e -> layout.show(contentPane, "Tarjeta 4"));
    btn5.addActionListener(e -> layout.show(contentPane, "Tarjeta 5"));

    // mostrar una tarjeta defaultt
    layout.show(contentPane, "Tarjeta 1");
}

    //metodo para crear tarjetas
    private JPanel crearTarjeta(String texto, Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        panel.add(new JLabel(texto));
        return panel;

    }
}