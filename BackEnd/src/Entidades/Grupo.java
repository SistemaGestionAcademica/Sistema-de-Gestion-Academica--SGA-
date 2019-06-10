/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Steven Villalobos
 */
public class Grupo {
    private String codigo;
    private Curso curso;
    private Profesor profesor;
    private String horario;

    public Grupo() {
        this.codigo = "";
        this.curso = new Curso();
        this.profesor = new Profesor();
        this.horario = "";
    }

    public Grupo(String codigo, Curso codigo_curso, Profesor profesor, String horario) {
        this.codigo = codigo;
        this.curso = curso;
        this.profesor = profesor;
        this.horario = horario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso codigo_curso) {
        this.curso = codigo_curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Grupo{" + "cod_grupo=" + codigo + ", codigo_curso=" + curso + ", cedula_profesor=" + profesor + ", horario=" + horario + '}';
    }
}
