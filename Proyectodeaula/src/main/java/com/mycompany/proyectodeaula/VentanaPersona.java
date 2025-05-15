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
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaPersona extends JFrame {
    private Persona persona;
    private DefaultListModel<Vacante> modeloVacantes;
    private JList<Vacante> listaVacantes;

    public VentanaPersona(Persona persona) {
        this.persona = persona;

        setTitle("Persona");
        setSize(500, 400);
        getContentPane().setBackground(new Color(60,166,166));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("Bienvenido, " + persona.getNombre());
        lbl.setForeground(Color.WHITE);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbl, BorderLayout.NORTH);

        modeloVacantes = new DefaultListModel<>();
        ArrayList<Vacante> todas = ArchivoDatos.obtenerTodasLasVacantes();
        for (Vacante v : todas) {
            modeloVacantes.addElement(v);
        }

        listaVacantes = new JList<>(modeloVacantes);
        add(new JScrollPane(listaVacantes), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBackground(new Color(60,166,166));

        JButton btnPostular = new JButton("Postularse");
        btnPostular.setBackground(new Color(1,46,64));
        btnPostular.setForeground(Color.white);
        btnPostular.addActionListener(e -> postularse());
        panelBotones.add(btnPostular);
        JButton btnDespostular = new JButton("Despostularse");
        btnDespostular.setBackground(new Color(1,46,64));
        btnDespostular.setForeground(Color.white);
        btnDespostular.addActionListener(e -> despostularse());
        panelBotones.add(btnDespostular);

        JButton btnPerfil = new JButton("Ver/Editar Perfil");
        btnPerfil.setBackground(new Color(1,46,64));
        btnPerfil.setForeground(Color.white);
        btnPerfil.addActionListener(e -> new VentanaPerfil(persona));
        panelBotones.add(btnPerfil);
        
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBackground(new Color(1,46,64));
        btnCerrarSesion.setForeground(Color.white);
        btnCerrarSesion.addActionListener(e -> {
        dispose(); 
        new VentanaLogin(); 
});
        panelBotones.add(btnCerrarSesion);
        add(panelBotones, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void postularse() {
        Vacante seleccionada = listaVacantes.getSelectedValue();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una vacante.");
            return;
        }else if (seleccionada != null && !persona.getVacantesPostuladas().contains(seleccionada)) {
            persona.getVacantesPostuladas().add(seleccionada);

            persona.postularse(seleccionada);

        // Guardar cambios
        ArrayList<Usuario> lista = ArchivoDatos.cargarUsuarios();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreUsuario().equals(persona.getNombreUsuario())) {
                lista.set(i, persona);
                break;
            }
        }
        ArchivoDatos.guardarUsuarios(lista);

        JOptionPane.showMessageDialog(this, "¡Te has postulado a la vacante!");
    }
    }   
    private void despostularse() {
    Vacante seleccionada = listaVacantes.getSelectedValue();
    if (seleccionada == null) {
        JOptionPane.showMessageDialog(this, "Selecciona una vacante.");
        return;
    }

    if (!persona.getVacantesPostuladas().contains(seleccionada)) {
        JOptionPane.showMessageDialog(this, "No estás postulado a esta vacante.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this,
            "¿Deseas despostularte de esta vacante?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) return;

    persona.getVacantesPostuladas().remove(seleccionada);

    
    ArrayList<Usuario> lista = ArchivoDatos.cargarUsuarios();
    for (int i = 0; i < lista.size(); i++) {
        if (lista.get(i).getNombreUsuario().equals(persona.getNombreUsuario())) {
            lista.set(i, persona);
            break;
        }
    }

    ArchivoDatos.guardarUsuarios(lista);

    JOptionPane.showMessageDialog(this, "Te has despostulado con éxito.");
}
}