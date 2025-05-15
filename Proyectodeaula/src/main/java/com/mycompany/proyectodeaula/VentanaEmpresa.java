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

public class VentanaEmpresa extends JFrame {
    private Empresa empresa;
    private DefaultListModel<Vacante> modeloLista;
    private JList<Vacante> listaVacantes;

    public VentanaEmpresa(Empresa empresa) {
        this.empresa = empresa;

        setTitle("Empresa");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(60,166,166));
        
        
        
        
        JLabel lbl = new JLabel("Bienvenido, " + empresa.getNombre());
        lbl.setForeground(Color.WHITE);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        add(lbl, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        for (Vacante v : empresa.getVacantesPublicadas()) {
            modeloLista.addElement(v);
        }

        listaVacantes = new JList<>(modeloLista);
        add(new JScrollPane(listaVacantes), BorderLayout.CENTER);

        JButton btnPublicar = new JButton("Publicar Nueva Vacante");
        btnPublicar.setBackground(new Color(1,46,64));
        btnPublicar.setForeground(Color.white);
        btnPublicar.addActionListener(e -> publicarVacante());
        
        
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(60,166,166));
        panelBotones.setLayout(new FlowLayout());
        
        
        JButton btnPublicarVacante = new JButton("Publicar Vacante");
        btnPublicarVacante.setBackground(new Color(1,46,64));
        btnPublicarVacante.setForeground(Color.white);
        btnPublicar.addActionListener(e -> publicarVacante());
        panelBotones.add(btnPublicar);
        
        
        JButton btnPerfil = new JButton("Ver/Editar Perfil");
        btnPerfil.setBackground(new Color(1,46,64));
        btnPerfil.setForeground(Color.white);
        btnPerfil.addActionListener(e -> new VentanaPerfil(empresa));
        panelBotones.add(btnPerfil);
        
        
        JButton btnEliminar = new JButton("Eliminar Vacante");
        btnEliminar.setBackground(new Color(1,46,64));
        btnEliminar.setForeground(Color.white);
        btnEliminar.addActionListener(e -> eliminarVacante());
        panelBotones.add(btnEliminar);
        
        
        JButton btnVerPostulantes = new JButton("Ver Postulantes");
        btnVerPostulantes.setBackground(new Color(1,46,64));
        btnVerPostulantes.setForeground(Color.white);
        btnVerPostulantes.addActionListener(e -> verPostulantes());
        panelBotones.add(btnVerPostulantes);
        
        
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

    private void publicarVacante() {
        String titulo = JOptionPane.showInputDialog(this, "Título de la vacante:");
        if (titulo == null || titulo.trim().isEmpty()) return;

        String descripcion = JOptionPane.showInputDialog(this, "Descripción:");
        if (descripcion == null || descripcion.trim().isEmpty()) return;
        
         String salario = JOptionPane.showInputDialog(this, "Salario por mes:");
        if (salario == null || salario.trim().isEmpty()) return;

        Vacante nueva = new Vacante(titulo, descripcion, empresa.getNombre(), salario);
        empresa.agregarVacante(nueva);
        modeloLista.addElement(nueva);

        // Guardar cambios en archivo
        ArrayList<Usuario> lista = ArchivoDatos.cargarUsuarios();
        for (int i = 0; i < lista.size(); i++) {
            Usuario u = lista.get(i);
            if (u.getNombreUsuario().equals(empresa.getNombreUsuario())) {
                lista.set(i, empresa);
                break;
            }
        }
        ArchivoDatos.guardarUsuarios(lista);
    }
    private void eliminarVacante() {
    Vacante seleccionada = listaVacantes.getSelectedValue();
    if (seleccionada == null) {
        JOptionPane.showMessageDialog(this, "Selecciona una vacante para eliminar.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this,
            "¿Estás seguro de que quieres eliminar esta vacante?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) return;

    empresa.getVacantesPublicadas().remove(seleccionada);
    modeloLista.removeElement(seleccionada);

    // Guardar cambios
    ArrayList<Usuario> lista = ArchivoDatos.cargarUsuarios();
    for (int i = 0; i < lista.size(); i++) {
        Usuario u = lista.get(i);
        if (u.getNombreUsuario().equals(empresa.getNombreUsuario())) {
            lista.set(i, empresa);
            break;
        }
    }

    // También despostular a personas
    for (Usuario u : lista) {
        if (u instanceof Persona p) {
            p.getVacantesPostuladas().remove(seleccionada);
        }
    }

    ArchivoDatos.guardarUsuarios(lista);
}
    private void verPostulantes() {
    Vacante seleccionada = listaVacantes.getSelectedValue();
    if (seleccionada == null) {
        JOptionPane.showMessageDialog(this, "Selecciona una vacante.");
        return;
    }

    new VentanaPostulante(seleccionada);
}
}
