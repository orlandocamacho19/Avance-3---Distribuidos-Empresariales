package mainPackage;


import dominio.Alumno;
import dominio.Maestro;
import dominio.Materia;
import dominio.Asignacion;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author orlandocamacho
 */
public class CrearDatos {

    private Materia materia1 = new Materia();
 
    public CrearDatos() {
        Alumno alumno1 = new Alumno("Manuel Valenzuela", "00000216785", "6to");
        Alumno alumno2 = new Alumno("Orlando Camacho", "00000216600", "6to");
        Maestro maestro = new Maestro("Gilberto Borrego", "00123544567", "pswd");
        Asignacion asig1 = new Asignacion("01", "Asignacion 1", "9", alumno1);
        Asignacion asig2 = new Asignacion("02", "Asignacion 1", "10", alumno1);
        Asignacion asig3 = new Asignacion("03", "Asignacion 2", "10", alumno2);
        Asignacion asig4 = new Asignacion("04", "Asignacion 2", "9", alumno2);
        
        materia1.setID("01");
        materia1.setSemestre("6");
        materia1.setNombre("Sistemas Distribuidos");
        materia1.setMaestro(maestro);
        materia1.addAlumno(alumno1);
        materia1.addAlumno(alumno2);
        materia1.addAsignacion(asig1);
        materia1.addAsignacion(asig2);
        materia1.addAsignacion(asig3);
        materia1.addAsignacion(asig4);
        
    }

    public Materia getMateria1() {
        return materia1;
    }
}
