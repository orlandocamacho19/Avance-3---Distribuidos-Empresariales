/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import com.google.gson.Gson;
import java.util.Objects;

/**
 *
 * @author orlandocamacho
 */
public class Asignacion {
    private String ID;
    private String nombre;
    private String calificacion;
    private Alumno alumno;

    public Asignacion() {
    }

    public Asignacion(String ID, String nombreAs, String calificacion, Alumno alumno) {
        this.ID = ID;
        this.nombre = nombreAs;
        this.calificacion = calificacion;
        this.alumno = alumno;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombreAs() {
        return nombre;
    }

    public void setNombreAs(String nombreAs) {
        this.nombre = nombreAs;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asignacion other = (Asignacion) obj;
        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public String toString() {
        return "Asignacion: " + nombre + ", calificacion: " + calificacion + ", alumno:" + alumno;
    }
    
    
}
