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

public class Persona extends Usuario {
    private ArrayList<Vacante> vacantesPostuladas = new ArrayList<>();

    public Persona(String usuario, String contraseña, String nombre, String email) {
        super(usuario, contraseña, nombre, email);
    }

    @Override
    public String getTipo() {
        return "Persona";
    }
public ArrayList<Vacante> getVacantesPostuladas() {
    return vacantesPostuladas;
}

public void postularse(Vacante v) {
    if (!vacantesPostuladas.contains(v)) {
        vacantesPostuladas.add(v);
    }
}
}