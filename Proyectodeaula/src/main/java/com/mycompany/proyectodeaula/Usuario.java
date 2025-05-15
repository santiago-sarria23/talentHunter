/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodeaula;


import java.io.Serializable;

public abstract class Usuario implements Serializable {
    protected String nombreUsuario;
    protected String contraseña;
    protected String nombre;
    protected String email;

    public Usuario(String nombreUsuario, String contraseña, String nombre, String email) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombreUsuario() { 
        return nombreUsuario; }
    
    public String getContraseña() { 
        return contraseña; }
    
    public String getNombre() { 
        return nombre; }
    
    public String getEmail() { 
        return email; }

    public void setNombre(String nombre) { 
        this.nombre = nombre; }
    
    public void setEmail(String email) { 
        this.email = email; }

    public abstract String getTipo(); // "Persona" o "Empresa"
}