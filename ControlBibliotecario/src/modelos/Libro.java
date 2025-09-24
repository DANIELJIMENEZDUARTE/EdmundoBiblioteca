package modelos;

public class Libro {
    private String titulo;
    private String autor;
    private String editorial;
    private boolean estado;
    private String nombrePrestador;

    public Libro(String titulo, String autor, String editorial, boolean estado, String nombrePrestador) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.estado = (estado == false);
        this.nombrePrestador = nombrePrestador;
    }

    public String toString() {
        return titulo + "," + autor + "," + editorial + "," + estado + "," + nombrePrestador;
    }

    public String getTitulo() {
        return titulo;
    }
}