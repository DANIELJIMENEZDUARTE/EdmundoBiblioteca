package utilerias;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelos.Libro;
import modelos.Persona;

public class ManejoRegistros {

    public static void guardarLibro(Libro libro) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("libros.csv", true))) {
            writer.write(libro.toString());
            writer.newLine();
        }
    }

    public static void eliminarLibro(String titulo) {
        try {
            Path ruta = Paths.get("libros.csv");
            List<String> lineas = Files.readAllLines(ruta);
            List<String> nuevasLineas = new ArrayList<>();
            for (String linea : lineas) {
                if (!linea.startsWith(titulo + ",")) {
                    nuevasLineas.add(linea);
                }
            }

            Files.write(ruta, nuevasLineas);
        } catch (IOException e) {
        }
    }

    public static void modificarLibro(Libro libromodificado) {
        String titulo = libromodificado.getTitulo();
        try {
            Path ruta = Paths.get("libros.csv");
            List<String> lineas = Files.readAllLines(ruta);
            List<String> nuevasLineas = new ArrayList<>();
            for (String linea : lineas) {
                if (linea.startsWith(titulo + ",")) {
                    nuevasLineas.add(libromodificado.toString());
                } else {
                    nuevasLineas.add(linea);
                }
            }

            Files.write(ruta, nuevasLineas);
        } catch (IOException e) {
        }
    }

    public static boolean prestarLibro(String titulo, String curp) {
        try {
            Path ruta = Paths.get("libros.csv");
            List<String> lineas = Files.readAllLines(ruta);
            List<String> nuevasLineas = new ArrayList<>();
            boolean libroPrestado = false;

            for (String linea : lineas) {
                if (linea.startsWith(titulo + ",")) {
                    String[] partes = linea.split(",");
                    if (partes.length >= 5 && partes[3].equals("false")) { // Verifica si el libro está disponible
                        partes[3] = "true"; // Cambia el estado a no disponible
                        partes[4] = curp; // Asigna la CURP del prestador
                        libroPrestado = true;
                    }
                    nuevasLineas.add(String.join(",", partes));
                } else {
                    nuevasLineas.add(linea);
                }
            }

            Files.write(ruta, nuevasLineas);
            return libroPrestado;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean devolverLibro(String titulo) {
        try {
            Path ruta = Paths.get("libros.csv");
            List<String> lineas = Files.readAllLines(ruta);
            List<String> nuevasLineas = new ArrayList<>();
            boolean libroDevuelto = false;

            for (String linea : lineas) {
                if (linea.startsWith(titulo + ",")) {
                    String[] partes = linea.split(",");
                    if (partes.length >= 5 && partes[3].equals("true")) { // Verifica si el libro está prestado
                        partes[3] = "false"; // Cambia el estado a disponible
                        partes[4] = "null"; // Elimina la CURP del prestador
                        libroDevuelto = true;
                    }
                    nuevasLineas.add(String.join(",", partes));
                } else {
                    nuevasLineas.add(linea);
                }
            }
            Files.write(ruta, nuevasLineas);
            return libroDevuelto;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean libroExiste(String titulo) {
        try {
            Path ruta = Paths.get("libros.csv");
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas) {
                if (linea.startsWith(titulo + ",")) {
                    return true; // El libro ya existe
                }
            }
        } catch (IOException e) {
        }
        return false; // El libro no existe
    }

    public static void guardarPersona(Persona persona) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("personas.csv", true))) {
            writer.write(persona.toString());
            writer.newLine();
        } catch (IOException e) {
        }
    }

    public static void eliminarPersona(String curp) {
        try {
            Path ruta = Paths.get("personas.csv");
            List<String> lineas = Files.readAllLines(ruta);
            List<String> nuevasLineas = new ArrayList<>();
            for (String linea : lineas) {
                if (!linea.startsWith(curp + ",")) {
                    nuevasLineas.add(linea);
                }
            }

            Files.write(ruta, nuevasLineas);
        } catch (IOException e) {
        }
    }

    public static void modificarPersona(Persona personaModificada) {
        String curp = personaModificada.getCurp();
        try {
            Path ruta = Paths.get("personas.csv");
            List<String> lineas = Files.readAllLines(ruta);
            List<String> nuevasLineas = new ArrayList<>();

            for (String linea : lineas) {
                if (linea.startsWith(curp + ",")) {
                    nuevasLineas.add(personaModificada.toString()); // reemplaza la línea con la nueva
                } else {
                    nuevasLineas.add(linea);
                }
            }

            Files.write(ruta, nuevasLineas);
        } catch (IOException e) {
        }
    }

    public static boolean curpExiste(String curp) {
        try {
            Path ruta = Paths.get("personas.csv");
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas) {
                if (linea.startsWith(curp + ",")) {
                    return true; // La CURP ya existe
                }
            }
        } catch (IOException e) {
        }
        return false; // La CURP no existe
    }

    public static boolean validarCURP(String curp) {
        String regex = "^[A-Z][AEIOU][A-Z]{2}\\d{2}(0[1-9]|1[0-2])"
                + "(0[1-9]|[12][0-9]|3[01])[HM][A-Z]{2}"
                + "[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d]\\d$";
        return curp != null && curp.matches(regex);
    }

    public static void asegurarArchivos() {
        try {
            Path rutaLibros = Paths.get("libros.csv");
            if (!Files.exists(rutaLibros)) {
                Files.createFile(rutaLibros);
            }

            Path rutaPersonas = Paths.get("personas.csv");
            if (!Files.exists(rutaPersonas)) {
                Files.createFile(rutaPersonas);
            }
        } catch (IOException e) {
        }
    }

    public static boolean validacionPersona(String nombre, String apellidos, String pass,
            String confirmar, String telefono, String correo) {
        if (nombre.isEmpty() || apellidos.isEmpty() || pass.isEmpty() ||
                confirmar.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (nombre.matches(".\\d.") || apellidos.matches(".\\d.")) {
        JOptionPane.showMessageDialog(null, "Nombre y apellidos no deben contener números", "Error",
                JOptionPane.ERROR_MESSAGE);
        return false;
        }

        if (telefono.length() != 10 || !telefono.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Teléfono no válido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!correo.contains("@")) {
            JOptionPane.showMessageDialog(null, "Correo no válido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!pass.equals(confirmar)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean validacionLibro(String titulo, String autor, String editorial) {
        if (titulo.isEmpty() || autor.isEmpty() || editorial.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (ManejoRegistros.libroExiste(titulo)) {
            JOptionPane.showMessageDialog(null, "El libro ya está registrado", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}