package Dominio;


import com.google.gson.Gson;
import Dominio.Alumno;
import Dominio.Materia;
import java.util.Objects;

public class AlumnoRegistro {
    private Alumno alumno;
    private String calificacion;
    private Materia materia;

    public AlumnoRegistro() {
    }

    public AlumnoRegistro(Alumno alumno, String calificacion, Materia materia) {
        this.alumno = alumno;
        this.calificacion = calificacion;
        this.materia = materia;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.alumno);
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
        final AlumnoRegistro other = (AlumnoRegistro) obj;
        return Objects.equals(this.alumno, other.alumno);
    }

    @Override
    public String toString() {
        return "AlumnoRegistro{" + "alumno=" + alumno + ", calificacion=" + calificacion + ", materia=" + materia + '}';
    }
    
    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
