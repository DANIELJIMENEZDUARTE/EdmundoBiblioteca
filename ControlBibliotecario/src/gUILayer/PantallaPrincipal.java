package gUILayer;

import java.awt.*;
import java.io.BufferedReader;
import java.util.Vector;
import javax.swing.*;
import modelos.Libro;
import modelos.Persona;
import utilerias.ManejoRegistros;

public class PantallaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private String title;
    private JPanel contentPane;
    private CardLayout layout = new CardLayout();

    public PantallaPrincipal(String title) {
        ManejoRegistros.asegurarArchivos();
        this.title = title;
        this.setTitle(title);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        inItComponents();
    }

    // Aquí se crean los botones y pantallas principales
    public void inItComponents() {
        this.setLayout(new BorderLayout());

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
        this.add(panelBotones, BorderLayout.NORTH);

        contentPane = new JPanel();
        layout = new CardLayout();
        contentPane.setLayout(layout);
        this.add(contentPane, BorderLayout.CENTER);

        JPanel tarjeta1 = crearRegistroUsuarios();
        JPanel tarjeta2 = crearRegistroLibros();
        JPanel tarjeta3 = crearPrestamo();
        JPanel tarjeta4 = crearDevolucion();
        JPanel tarjeta5 = crearVisualizacionUsuarios();

        contentPane.add(tarjeta1, "Tarjeta 1");
        contentPane.add(tarjeta2, "Tarjeta 2");
        contentPane.add(tarjeta3, "Tarjeta 3");
        contentPane.add(tarjeta4, "Tarjeta 4");
        contentPane.add(tarjeta5, "Tarjeta 5");

        btn1.addActionListener(e -> layout.show(contentPane, "Tarjeta 1"));
        btn2.addActionListener(e -> layout.show(contentPane, "Tarjeta 2"));
        btn3.addActionListener(e -> {
            contentPane.remove(2);
            JPanel nuevaTarjeta3 = crearPrestamo();
            contentPane.add(nuevaTarjeta3, "Tarjeta 3");
            layout.show(contentPane, "Tarjeta 3");
        });
        btn4.addActionListener(e -> {
            contentPane.remove(3);
            JPanel nuevaTarjeta4 = crearDevolucion();
            contentPane.add(nuevaTarjeta4, "Tarjeta 4");
            layout.show(contentPane, "Tarjeta 4");
        });
        btn5.addActionListener(e -> {
            contentPane.remove(4);
            JPanel nuevaTarjeta5 = crearVisualizacionUsuarios();
            contentPane.add(nuevaTarjeta5, "Tarjeta 5");
            layout.show(contentPane, "Tarjeta 5");
        });

        layout.show(contentPane, "Tarjeta 1");
    }

    // Tarjeta para registrar usuarios
    private JPanel crearRegistroUsuarios() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.CYAN);
        panel.add(new JLabel("REGISTRO DE USUARIOS", JLabel.CENTER));
        panel.setFont(new Font("Arial", Font.BOLD, 18));

        GridBagConstraints reglas = new GridBagConstraints();
        reglas.insets = new Insets(5, 5, 5, 5);
        reglas.fill = GridBagConstraints.HORIZONTAL;

        // Campos
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(15);
        JLabel lblApellidos = new JLabel("Apellidos:");
        JTextField txtApellidos = new JTextField(15);
        JLabel lblCurp = new JLabel("Curp:");
        JTextField txtCurp = new JTextField(15);
        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField(15);
        JLabel lblConfirmar = new JLabel("Confirmar Contraseña:");
        JPasswordField txtConfirmar = new JPasswordField(15);
        JLabel lblTelefono = new JLabel("Telefono:");
        JTextField txtTelefono = new JTextField(10);
        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField(15);
        JButton btnRegistrar = new JButton("Registrar");

        // Posición de los elementos
        reglas.gridx = 0;
        reglas.gridy = 1;
        panel.add(lblNombre, reglas);
        reglas.gridx = 1;
        panel.add(txtNombre, reglas);

        reglas.gridx = 0;
        reglas.gridy = 2;
        panel.add(lblApellidos, reglas);
        reglas.gridx = 1;
        panel.add(txtApellidos, reglas);

        reglas.gridx = 0;
        reglas.gridy = 3;
        panel.add(lblCurp, reglas);
        reglas.gridx = 1;
        panel.add(txtCurp, reglas);

        reglas.gridx = 0;
        reglas.gridy = 4;
        panel.add(lblPassword, reglas);
        reglas.gridx = 1;
        panel.add(txtPassword, reglas);

        reglas.gridx = 0;
        reglas.gridy = 5;
        panel.add(lblConfirmar, reglas);
        reglas.gridx = 1;
        panel.add(txtConfirmar, reglas);

        reglas.gridx = 0;
        reglas.gridy = 6;
        panel.add(lblTelefono, reglas);
        reglas.gridx = 1;
        panel.add(txtTelefono, reglas);

        reglas.gridx = 0;
        reglas.gridy = 7;
        panel.add(lblCorreo, reglas);
        reglas.gridx = 1;
        panel.add(txtCorreo, reglas);

        reglas.gridx = 0;
        reglas.gridy = 8;
        reglas.gridwidth = 2;
        reglas.anchor = GridBagConstraints.CENTER;
        panel.add(btnRegistrar, reglas);

        // Acción del botón
        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String curp = txtCurp.getText().toUpperCase();
            String pass = new String(txtPassword.getPassword());
            String confirmar = new String(txtConfirmar.getPassword());
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();

            if (curp.length() != 18 || !ManejoRegistros.validarCURP(curp)) {
                JOptionPane.showMessageDialog(null, "Curp no válido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (ManejoRegistros.curpExiste(curp)) {
                JOptionPane.showMessageDialog(null, "Curp ya existente", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!ManejoRegistros.validacionPersona(nombre, apellidos, pass, confirmar, telefono, correo)) {
                return;
            }

            Persona p = new Persona(nombre, apellidos, curp, pass, telefono, correo);

            try {
                ManejoRegistros.guardarPersona(p);
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // Tarjeta para registrar libros
    private JPanel crearRegistroLibros() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.PINK);
        panel.add(new JLabel("REGISTRO DE LIBROS", JLabel.CENTER));
        panel.setFont(new Font("Arial", Font.BOLD, 18));

        GridBagConstraints reglas = new GridBagConstraints();
        reglas.insets = new Insets(5, 5, 5, 5);
        reglas.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Nombre:");
        JTextField txtTitulo = new JTextField(15);
        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField(15);
        JLabel lblEditorial = new JLabel("Editorial:");
        JTextField txtEditorial = new JTextField(15);
        JButton btnRegistrar = new JButton("Registrar");

        reglas.gridx = 0;
        reglas.gridy = 1;
        panel.add(lblTitulo, reglas);
        reglas.gridx = 1;
        panel.add(txtTitulo, reglas);

        reglas.gridx = 0;
        reglas.gridy = 2;
        panel.add(lblAutor, reglas);
        reglas.gridx = 1;
        panel.add(txtAutor, reglas);

        reglas.gridx = 0;
        reglas.gridy = 3;
        panel.add(lblEditorial, reglas);
        reglas.gridx = 1;
        panel.add(txtEditorial, reglas);

        reglas.gridx = 0;
        reglas.gridy = 4;
        reglas.gridwidth = 2;
        panel.add(btnRegistrar, reglas);

        btnRegistrar.addActionListener(e -> {
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String editorial = txtEditorial.getText();
            if (!ManejoRegistros.validacionLibro(titulo, autor, editorial)) {
                return;
            }
            Libro libro = new Libro(titulo, autor, editorial, false, null);

            try {
                ManejoRegistros.guardarLibro(libro);
                JOptionPane.showMessageDialog(null, "Libro registrado correctamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al guardar el libro", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // Tarjeta para préstamo de libros
    private JPanel crearPrestamo() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints reglas = new GridBagConstraints();
        reglas.insets = new Insets(10, 10, 10, 10);
        reglas.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y combo de usuarios
        JLabel lblUsuario = new JLabel("Selecciona Usuario (CURP):");
        Vector<String> usuarios = new Vector<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader("personas.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length > 2) {
                    usuarios.add(campos[2]);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer usuarios", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        JComboBox<String> comboUsuarios = new JComboBox<>(usuarios);

        // Etiqueta y combo de libros disponibles
        JLabel lblLibro = new JLabel("Selecciona Libro (Título):");
        Vector<String> libros = new Vector<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader("libros.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length > 0 && campos[3].equalsIgnoreCase("false")) {
                    libros.add(campos[0]);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer libros", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        JComboBox<String> comboLibros = new JComboBox<>(libros);

        JButton btnPrestar = new JButton("Prestar Libro");

        // Posición de elementos
        reglas.gridx = 0;
        reglas.gridy = 0;
        panel.add(lblUsuario, reglas);
        reglas.gridx = 1;
        panel.add(comboUsuarios, reglas);

        reglas.gridx = 0;
        reglas.gridy = 1;
        panel.add(lblLibro, reglas);
        reglas.gridx = 1;
        panel.add(comboLibros, reglas);

        reglas.gridx = 0;
        reglas.gridy = 2;
        reglas.gridwidth = 2;
        panel.add(btnPrestar, reglas);

        // Lógica del botón prestar
        btnPrestar.addActionListener(e -> {
            String curp = (String) comboUsuarios.getSelectedItem();
            String titulo = (String) comboLibros.getSelectedItem();

            if (curp == null || titulo == null || curp.equals("Sin usuarios")
                    || titulo.equals("Sin libros disponibles")) {
                JOptionPane.showMessageDialog(null, "Selecciona usuario y libro válidos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean prestado = ManejoRegistros.prestarLibro(titulo, curp);
            if (prestado) {
                JOptionPane.showMessageDialog(null, "Libro prestado correctamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo prestar el libro", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    // Tarjeta para devolución de libros
    private JPanel crearDevolucion() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.ORANGE);

        GridBagConstraints reglas = new GridBagConstraints();
        reglas.insets = new Insets(10, 10, 10, 10);
        reglas.fill = GridBagConstraints.HORIZONTAL;
        // Etiqueta y combo de libros prestados
        JLabel lblLibro = new JLabel("Selecciona Libro a Devolver (Título):");
        Vector<String> libros = new Vector<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader("libros.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length > 0 && campos[3].equalsIgnoreCase("true")) {
                    libros.add(campos[0]);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer libros", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        JComboBox<String> comboLibros = new JComboBox<>(libros);
        JButton btnDevolver = new JButton("Devolver");

        reglas.gridx = 0;
        reglas.gridy = 0;
        panel.add(lblLibro, reglas);
        reglas.gridx = 1;
        panel.add(comboLibros, reglas);
        reglas.gridx = 0;
        reglas.gridy = 1;
        reglas.gridwidth = 2;
        panel.add(btnDevolver, reglas);

        // Lógica del botón devolver
        btnDevolver.addActionListener(e -> {
            String titulo = (String) comboLibros.getSelectedItem();
            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa un título válido", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean devuelto = ManejoRegistros.devolverLibro(titulo);
            if (devuelto) {
                JOptionPane.showMessageDialog(null, "Libro devuelto correctamente", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo devolver el libro", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        return panel;
    }

    // Tarjeta para visualizar usuarios y libros registrados
    private JPanel crearVisualizacionUsuarios() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints reglas = new GridBagConstraints();

        // Columnas de la tabla de usuarios
        String[] columnasUsuarios = { "CURP", "Nombre", "Apellidos", "Correo", "Contraseña", "Teléfono" };
        Vector<String[]> datosUsuarios = new Vector<>();

        // Leer usuarios desde el archivo personas.csv
        try (BufferedReader br = new BufferedReader(new java.io.FileReader("personas.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                datosUsuarios.add(campos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer usuarios", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Tabla de usuarios
        String[][] datosArrayUsuarios = datosUsuarios.toArray(new String[0][]);
        JTable tablaUsuarios = new JTable(datosArrayUsuarios, columnasUsuarios);
        JScrollPane scrollUsuarios = new JScrollPane(tablaUsuarios);

        // Botones para usuarios
        JButton btnModificar = new JButton("Modificar Usuario");
        JButton btnEliminar = new JButton("Eliminar Usuario");

        // Acción de eliminar usuario
        btnEliminar.addActionListener(e -> {
            int filaSeleccionada = tablaUsuarios.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String curp = (String) tablaUsuarios.getValueAt(filaSeleccionada, 0);
            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de eliminar al usuario con CURP: " + curp + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                ManejoRegistros.eliminarPersona(curp);
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");

                // Recargar pantalla
                contentPane.remove(4);
                contentPane.add(crearVisualizacionUsuarios(), "Tarjeta 5");
                layout.show(contentPane, "Tarjeta 5");
            }
        });

        // Acción de modificar usuario
        btnModificar.addActionListener(e -> {
            int fila = tablaUsuarios.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona una fila para modificar", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String curp = (String) tablaUsuarios.getValueAt(fila, 0);
            String nombre = JOptionPane.showInputDialog(null, "Nuevo nombre:",
                    tablaUsuarios.getValueAt(fila, 1));
            String apellidos = JOptionPane.showInputDialog(null, "Nuevos apellidos:",
                    tablaUsuarios.getValueAt(fila, 2));
            String correo = JOptionPane.showInputDialog(null, "Nuevo correo:",
                    tablaUsuarios.getValueAt(fila, 3));
            String contrasena = JOptionPane.showInputDialog(null, "Nueva contraseña:",
                    tablaUsuarios.getValueAt(fila, 4));
            String telefono = JOptionPane.showInputDialog(null, "Nuevo teléfono:",
                    tablaUsuarios.getValueAt(fila, 5));

            if (!ManejoRegistros.validacionPersona(nombre, apellidos, contrasena, contrasena, telefono, correo)) {
                return;
            }

            Persona personaModificada = new Persona(nombre, apellidos, curp, contrasena, telefono, correo);
            ManejoRegistros.modificarPersona(personaModificada);

            JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");

            // Recargar pantalla
            contentPane.remove(4);
            contentPane.add(crearVisualizacionUsuarios(), "Tarjeta 5");
            layout.show(contentPane, "Tarjeta 5");
        });

        // Panel con botones de usuario
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);

        // ----------------------------
        // Tabla de libros registrados
        // ----------------------------
        String[] columnasLibros = { "Título", "Autor", "Editorial", "Prestado", "CURP Prestador" };
        Vector<String[]> datosLibros = new Vector<>();

        // Leer libros desde libros.csv
        try (BufferedReader br = new BufferedReader(new java.io.FileReader("libros.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                datosLibros.add(campos);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer libros", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        String[][] datosArrayLibros = datosLibros.toArray(new String[0][]);
        JTable tablaLibros = new JTable(datosArrayLibros, columnasLibros);
        JScrollPane scrollLibros = new JScrollPane(tablaLibros);

        // Botones para libros
        JButton btnModificarLibro = new JButton("Modificar Libro");
        JButton btnEliminarLibro = new JButton("Eliminar Libro");

        // Acción eliminar libro
        btnEliminarLibro.addActionListener(e -> {
            int fila = tablaLibros.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un libro para eliminar", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String titulo = (String) tablaLibros.getValueAt(fila, 0);
            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de eliminar el libro: \"" + titulo + "\"?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                ManejoRegistros.eliminarLibro(titulo);
                JOptionPane.showMessageDialog(null, "Libro eliminado correctamente");

                contentPane.remove(4);
                contentPane.add(crearVisualizacionUsuarios(), "Tarjeta 5");
                layout.show(contentPane, "Tarjeta 5");
            }
        });

        // Acción modificar libro
        btnModificarLibro.addActionListener(e -> {
            int fila = tablaLibros.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un libro para modificar", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String tituloOriginal = (String) tablaLibros.getValueAt(fila, 0);
            String nuevoTitulo = JOptionPane.showInputDialog(null, "Nuevo título:", tituloOriginal);
            String autor = JOptionPane.showInputDialog(null, "Nuevo autor:",
                    tablaLibros.getValueAt(fila, 1));
            String editorial = JOptionPane.showInputDialog(null, "Nueva editorial:",
                    tablaLibros.getValueAt(fila, 2));
            boolean prestado = Boolean.parseBoolean((String) tablaLibros.getValueAt(fila, 3));
            String curpPrestador = (String) tablaLibros.getValueAt(fila, 4);

            if (nuevoTitulo == null || autor == null || editorial == null) {
                JOptionPane.showMessageDialog(null, "Modificación cancelada", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (int i = 0; i < tablaLibros.getRowCount(); i++) {
                String tituloEnTabla = (String) tablaLibros.getValueAt(i, 0);
                if (tituloEnTabla.equalsIgnoreCase(nuevoTitulo) && !tituloEnTabla.equalsIgnoreCase(tituloOriginal)) {
                    JOptionPane.showMessageDialog(null, "Ya existe otro libro con ese título", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            Libro libroModificado = new Libro(nuevoTitulo, autor, editorial, prestado, curpPrestador);
            ManejoRegistros.modificarLibro(libroModificado);

            JOptionPane.showMessageDialog(null, "Libro modificado correctamente");

            contentPane.remove(4);
            contentPane.add(crearVisualizacionUsuarios(), "Tarjeta 5");
            layout.show(contentPane, "Tarjeta 5");
        });

        // Agregar botones de libros al mismo panel
        panelBotones.add(btnModificarLibro);
        panelBotones.add(btnEliminarLibro);

        // Panel con tablas de usuarios y libros
        JPanel panelUsuarios = new JPanel(new BorderLayout());
        panelUsuarios.add(new JLabel("Usuarios registrados", JLabel.CENTER), BorderLayout.NORTH);
        panelUsuarios.add(scrollUsuarios, BorderLayout.CENTER);

        JPanel panelLibros = new JPanel(new BorderLayout());
        panelLibros.add(new JLabel("Libros registrados", JLabel.CENTER), BorderLayout.NORTH);
        panelLibros.add(scrollLibros, BorderLayout.CENTER);

        JPanel panelTablas = new JPanel();
        panelTablas.setLayout(new BoxLayout(panelTablas, BoxLayout.Y_AXIS));
        panelTablas.add(panelUsuarios);
        panelTablas.add(panelLibros);

        // Añadir botones y tablas al panel principal
        reglas.gridx = 0;
        reglas.gridy = 1;
        reglas.weighty = 0;
        reglas.fill = GridBagConstraints.NONE;
        panel.add(panelBotones, reglas);

        reglas.gridx = 0;
        reglas.gridy = 0;
        reglas.gridwidth = 2;
        reglas.fill = GridBagConstraints.BOTH;
        reglas.weightx = 1.0;
        reglas.weighty = 1.0;
        panel.add(panelTablas, reglas);

        return panel;
    }
}
