/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodeaula;

/**
 *
 * @author ACER
 */

import java.util.ArrayList;

public class Empresa extends Usuario {
    private ArrayList<Vacante> vacantesPublicadas = new ArrayList<>();

    public Empresa(String usuario, String contraseña, String nombre, String email) {
        super(usuario, contraseña, nombre, email);
    }

    @Override
    public String getTipo() {
        return "Empresa";
    }
    
public ArrayList<Vacante> getVacantesPublicadas() {
    return vacantesPublicadas;
}

public void agregarVacante(Vacante v) {
    vacantesPublicadas.add(v);
}
}