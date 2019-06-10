/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven Villalobos
 */
public class Matricula {
    int nota;
    List<Alumno> estudiantes;
    List<Grupo> grupos;

    public Matricula() {
        this.nota = 0;
        this.estudiantes = new ArrayList<Alumno>();
        this.grupos =  new ArrayList<Grupo>();
    }

    public Matricula(int nota, List<Alumno> estudiantes, List<Grupo> grupos) {
        this.nota = nota;
        this.estudiantes = estudiantes;
        this.grupos = grupos;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setEstudiantes(List<Alumno> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public int getNota() {
        return nota;
    }

    public List<Alumno> getEstudiantes() {
        return estudiantes;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }
}
