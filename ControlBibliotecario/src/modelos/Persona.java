package modelos;

public class Persona {

    private String nombre;
    private String apellido;
    private String curp;
    private String correoelectronico;
    private String contrasena;
    private String telefono;
    

    public Persona(String nombre, String apellido,String curp, String contrasena, String telefono , String correoelectronico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.curp = curp;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.correoelectronico = correoelectronico;
    }

    public String toString() {
        return curp + "," + nombre + "," + apellido + "," + correoelectronico + "," + contrasena + "," + telefono;
    }

    public String getCurp() {
        return curp;
    }
}
