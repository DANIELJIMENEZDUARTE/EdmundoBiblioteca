package modelos;

public class Persona {

    private String nombre;
    private String apellido;
    private String correoelectronico;
    private String contrasena;
    private String telefono;
    

    public Persona(String nombre, String apellido, String correoelectronico, String contrasena, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoelectronico = correoelectronico;
        this.contrasena = contrasena;
        this.telefono = telefono;
    }

    public String toString() {
        return nombre + "," + apellido + "," + correoelectronico + "," + contrasena + "," + telefono;
    }
}
