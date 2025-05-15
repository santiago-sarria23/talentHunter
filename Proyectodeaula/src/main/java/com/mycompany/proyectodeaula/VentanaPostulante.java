/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodeaula;

/**
 *
 * @author ACER
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPostulante extends JFrame {
    public VentanaPostulante(Vacante vacante) {
        setTitle("Postulantes - " + vacante.getTitulo());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        DefaultListModel<String> modelo = new DefaultListModel<>();
        ArrayList<Usuario> usuarios = ArchivoDatos.cargarUsuarios();
        for (Usuario u : usuarios) {
             if (u instanceof Persona persona) {
                for (Vacante v : persona.getVacantesPostuladas()) {
                   if (v.equals(vacante)) {
                        modelo.addElement(persona.getNombre() + " (" + persona.getEmail() + ")");
                        break;
            }
        }
    }
}
   
        JList<String> lista = new JList<>(modelo);
        add(new JScrollPane(lista), BorderLayout.CENTER);

        if (modelo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nadie se ha postulado aún a esta vacante.");
            dispose();
        } else {
            setVisible(true);
        }
    }
}
