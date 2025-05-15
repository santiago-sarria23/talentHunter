/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodeaula;

/**
 *
 * @author ACER
 */

import java.io.*;
import java.util.ArrayList;

public class ArchivoDatos {
    private static final String ARCHIVO = "usuarios.dat";

    public static void guardarUsuarios(ArrayList<Usuario> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Usuario> cargarUsuarios() {
        File f = new File(ARCHIVO);
        if (!f.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (ArrayList<Usuario>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public static ArrayList<Vacante> obtenerTodasLasVacantes() {
    ArrayList<Usuario> usuarios = cargarUsuarios();
    ArrayList<Vacante> vacantes = new ArrayList<>();

    for (Usuario u : usuarios) {
        if (u instanceof Empresa empresa) {
            vacantes.addAll(empresa.getVacantesPublicadas());
        }
    }

    return vacantes;
}
}
