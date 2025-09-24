package utilerias;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import modelos.Libro;
import modelos.Persona;

public class ManejoRegistros {
    public static void guardarLibro(Libro libro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("libros.csv", true))) {
            writer.write(libro.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar el libro: " + e.getMessage());
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
            System.err.println("Error al eliminar el libro: " + e.getMessage());
        }
    }

    public static void ModificarLibro(Libro libromodificado) {
        String titulo = libromodificado.getTitulo();
        try {
            Path ruta = Paths.get("libros.csv");
            List<String> lineas = Files.readAllLines(ruta);
            List<String> nuevasLineas = new ArrayList<>();
            for (String linea : lineas) {
                if (linea.startsWith(titulo + ",")) {
                    nuevasLineas.add(libromodificado.toString());
                }
                else {
                    nuevasLineas.add(linea);
                }
            }

            Files.write(ruta, nuevasLineas);
        } catch (IOException e) {
            System.err.println("Error al modificar el libro: " + e.getMessage());
        }
    }

    public static void guardarPersona(Persona persona) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("personas.csv", true))) {
            writer.write(persona.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar la persona: " + e.getMessage());
        }
    }
}
