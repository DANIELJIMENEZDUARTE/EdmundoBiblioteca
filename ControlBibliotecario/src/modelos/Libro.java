package modelos;

public class Libro {
    private String titulo;
    private String autor;
    private String editorial;
    private boolean estado;
    private String curpPrestador;

    public Libro(String titulo, String autor, String editorial, boolean estado, String curpPrestador) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.estado = estado;
        this.curpPrestador = curpPrestador;
    }

    public String toString() {
        return titulo + "," + autor + "," + editorial + "," + estado + "," + curpPrestador;
    }

    public String getTitulo() {
        return titulo;
    }
}