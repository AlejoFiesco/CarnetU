
package Logica;


import java.awt.Image;
import java.awt.TrayIcon;
import java.io.Serializable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;

public class Estudiante implements Serializable{
    private String nombre;
    private String codigo;
    private String carrera;
    private String universidad;
    private String dirFoto;

    public String getDirFoto() {
        return dirFoto;
    }

    public void setDirFoto(String dirFoto) {
        this.dirFoto = dirFoto;
    }

    public Estudiante(){
        nombre = "";
        codigo = "";
        carrera = "";
        universidad = "";
        dirFoto = "";
    }
    public void visualizar(){
        ImageIcon foto = new ImageIcon(this.dirFoto);          
        JOptionPane.showMessageDialog(null, "Nombre: "+this.nombre+"\nCódigo: "+this.codigo+"\nCarrera: "+this.carrera+"\nUniversidad: "+this.universidad,"Carné",PLAIN_MESSAGE,foto);
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    
}
