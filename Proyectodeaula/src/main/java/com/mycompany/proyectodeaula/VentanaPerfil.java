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

public class VentanaPerfil extends JFrame {
    private Usuario usuario;
    private JTextField txtNombre, txtEmail;
    private JLabel lblTipo, lblUsuario;

    public VentanaPerfil(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Perfil");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Usuario:"));
        lblUsuario = new JLabel(usuario.getNombreUsuario());
        add(lblUsuario);

        add(new JLabel("Tipo de cuenta:"));
        lblTipo = new JLabel(usuario.getTipo());
        add(lblTipo);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(usuario.getNombre());
        add(txtNombre);

        add(new JLabel("Email:"));
        txtEmail = new JTextField(usuario.getEmail());
        add(txtEmail);

        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        add(btnGuardar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        add(btnCerrar);

        setVisible(true);
    }

    private void guardarCambios() {
        String nuevoNombre = txtNombre.getText().trim();
        String nuevoEmail = txtEmail.getText().trim();

        if (nuevoNombre.isEmpty() || nuevoEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos.");
            return;
        }

        usuario.setNombre(nuevoNombre);
        usuario.setEmail(nuevoEmail);

        // Actualizar en archivo
        ArrayList<Usuario> lista = ArchivoDatos.cargarUsuarios();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombreUsuario().equals(usuario.getNombreUsuario())) {
                lista.set(i, usuario);
                break;
            }
        }
        ArchivoDatos.guardarUsuarios(lista);

        JOptionPane.showMessageDialog(this, "Cambios guardados con éxito.");
        dispose();
    }
}