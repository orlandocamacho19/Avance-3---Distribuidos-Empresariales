/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author orlandocamacho
 */
public class Materia {
    private String ID;
    private String nombre;
    private String semestre;
    private Maestro maestro;
    private ArrayList<Alumno> alumnos = new ArrayList();
    private ArrayList<Integer> calificaciones = new ArrayList();
    private ArrayList<Asignacion> asignaciones = new ArrayList();

    public Materia() {
    }

    public Materia(String ID, String nombre, String semestre, Maestro maestro) {
        this.ID = ID;
        this.nombre = nombre;
        this.semestre = semestre;
        this.maestro = maestro;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public Maestro getMaestro() {
        return maestro;
    }

    public void setMaestro(Maestro maestro) {
        this.maestro = maestro;
    }
    
    public void addAlumno(Alumno alumno) {
        alumnos.add(alumno);
        calificaciones.add(0);
    }
    
    public void addAsignacion(Asignacion asignacion) {
        asignaciones.add(asignacion);
    }
    
    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Materia other = (Materia) obj;
        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", semestre: " + semestre + ", maestro: " + maestro + '}';
    }
    
    
}
