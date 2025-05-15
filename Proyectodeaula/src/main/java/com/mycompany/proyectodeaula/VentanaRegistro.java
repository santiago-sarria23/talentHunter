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

public class VentanaRegistro extends JFrame {
    private JTextField txtUsuario, txtNombre, txtEmail;
    private JPasswordField txtContraseña;
    private JComboBox<String> comboTipo;
    private JButton btnRegistrar;

    public VentanaRegistro() {
        setTitle("Registro");
        getContentPane().setBackground(new Color(60,166,166));
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));
        
        
        JLabel tipo = new JLabel("Tipo de cuenta: ");
        tipo.setForeground(Color.white);
        add(tipo);
        comboTipo = new JComboBox<>(new String[] { "Persona", "Empresa" });
        add(comboTipo);
        
        
        JLabel usuario = new JLabel("Usuario: ");
        usuario.setForeground(Color.white);
        add(usuario);
        txtUsuario = new JTextField();
        add(txtUsuario);
        
        
        JLabel contraseña = new JLabel("Contraseña: ");
        contraseña.setForeground(Color.white);
        add(contraseña);
        txtContraseña = new JPasswordField();
        add(txtContraseña);
        
        
        JLabel nombre = new JLabel("Nombre: ");
        nombre.setForeground(Color.white);
        add(nombre);
        txtNombre = new JTextField();
        add(txtNombre);
        
        JLabel email = new JLabel("Email: ");
        email.setForeground(Color.white);

        add(email);
        txtEmail = new JTextField();
        add(txtEmail);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(1,46,64));
        btnRegistrar.setForeground(Color.white);
        add(btnRegistrar);
        add(new JLabel()); 

        btnRegistrar.addActionListener(e -> registrar());

        setVisible(true);
    }

    private void registrar() {
        String tipo = (String) comboTipo.getSelectedItem();
        String usuario = txtUsuario.getText().trim();
        String contraseña = new String(txtContraseña.getPassword()).trim();
        String nombre = txtNombre.getText().trim();
        String email = txtEmail.getText().trim();

        if (usuario.isEmpty() || contraseña.isEmpty() || nombre.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.");
            return;
        }

        ArrayList<Usuario> lista = ArchivoDatos.cargarUsuarios();

        // Verificar si el usuario ya existe
        for (Usuario u : lista) {
            if (u.getNombreUsuario().equals(usuario)) {
                JOptionPane.showMessageDialog(this, "Ese nombre de usuario ya existe.");
                return;
            }
        }

        Usuario nuevo;
        if (tipo.equals("Persona")) {
            nuevo = new Persona(usuario, contraseña, nombre, email);
        } else {
            nuevo = new Empresa(usuario, contraseña, nombre, email);
        }

        lista.add(nuevo);
        ArchivoDatos.guardarUsuarios(lista);
        JOptionPane.showMessageDialog(this, "¡Registro exitoso!");

        dispose(); // Cerrar la ventana de registro
        new VentanaLogin(); // Ir a login
    }
}