package Models;

import java.awt.Color;

/**
 *
 * @author Miguel Roa
 */
public class UserModel {
    
    public String nombre;
    public String apellido;
    public Color color; //Color en hexacode
    
    public UserModel(String nombre, String apellido, Color color){
        this.nombre = nombre;
        this.apellido = apellido;
        this.color = color;        
    }
        
    //Set y get
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }   
                
}
