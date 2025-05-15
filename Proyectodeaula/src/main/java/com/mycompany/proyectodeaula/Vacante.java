/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodeaula;

/**
 *
 * @author ACER
 */

import java.io.Serializable;
import java.util.Objects;

public class Vacante implements Serializable {
    private String titulo;
    private String descripcion;
    private String nombreEmpresa;
    private String salario;
    private double Salario;

    public Vacante(String titulo, String descripcion, String nombreEmpresa, String salario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.nombreEmpresa = nombreEmpresa;
        this.salario= salario;
    }

    public String getTitulo() { 
        return titulo; }
    public String getDescripcion() { 
        return descripcion; }
    public String getNombreEmpresa() { 
        return nombreEmpresa; }

    @Override
    public String toString() {
        return "Titulo:" + titulo +  " Salario por mes:" + salario +  "Descripcion:" + descripcion +  " Nombre de la empresa:" + nombreEmpresa;
    }
    public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Vacante other = (Vacante) obj;
    return titulo.equals(other.titulo) &&
           descripcion.equals(other.descripcion) &&
           Salario == other.Salario;
}

@Override
public int hashCode() {
    return Objects.hash(titulo, descripcion, Salario);
}
}
