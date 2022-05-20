package Dominio;

import com.google.gson.Gson;
import java.util.ArrayList;

public class DatosAlumnoProgreso {
    
    private Materia materia;
    private Alumno alumno;
    private ArrayList<Asignacion> asignaciones = new ArrayList();
    
    public DatosAlumnoProgreso(Materia materia, Alumno alumno) {
        this.alumno = alumno;
        this.materia = materia;
        for (int i = 0; i < materia.getAsignaciones().size(); i++) {
            if (materia.getAsignaciones().get(i).getAlumno() == alumno) {
                asignaciones.add(materia.getAsignaciones().get(i));
            }
        }
    }
    
    public Materia getMateria() {
        return materia;
    }
    
    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    public Alumno getAlumno() {
        return alumno;
    }
    
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    public ArrayList<Asignacion> getAsignaciones() {
        return asignaciones;
    }
    
    public void setAsignaciones(ArrayList<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }
    
    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return "Progreso del alumno: " + "Materia: " + materia + ", Id Alumno: " + alumno.getID() + ", Asignaciones: " + asignaciones;
    }
    
    
    
}
