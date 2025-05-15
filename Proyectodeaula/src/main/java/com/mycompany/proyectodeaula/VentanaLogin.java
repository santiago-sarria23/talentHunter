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

public class VentanaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JButton btnIniciar, btnIrRegistro;

    public VentanaLogin() {
        setTitle("Iniciar Sesión");
        setSize(500, 300);
        getContentPane().setBackground(new Color(60,166,166));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2));

        
        
        JLabel usuario = new JLabel("Usuario");
        usuario.setForeground(Color.WHITE);
        add(usuario);
        txtUsuario = new JTextField();
        add(txtUsuario);
        JLabel contraseña=new JLabel("Contraseña:");
        contraseña.setForeground(Color.WHITE);
        add(contraseña);
        txtContraseña = new JPasswordField();
        add(txtContraseña);

        btnIniciar = new JButton("Iniciar Sesión");
        btnIniciar.setBackground(new Color(1,46,64));
        btnIniciar.setForeground(Color.WHITE);
        btnIrRegistro = new JButton("Registrarse");
        btnIrRegistro.setBackground(new Color(1,46,64));
        btnIrRegistro.setForeground(Color.WHITE);

        add(btnIniciar);
        add(btnIrRegistro);

        btnIniciar.addActionListener(e -> iniciarSesion());
        btnIrRegistro.addActionListener(e -> {
            dispose();
            new VentanaRegistro();
        });

        setVisible(true);
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String contraseña = new String(txtContraseña.getPassword()).trim();

        ArrayList<Usuario> lista = ArchivoDatos.cargarUsuarios();

        for (Usuario u : lista) {
            if (u.getNombreUsuario().equals(usuario) && u.getContraseña().equals(contraseña)) {
                JOptionPane.showMessageDialog(this, "Bienvenido, " + u.getNombre());

                dispose();
                if (u instanceof Persona) {
                    new VentanaPersona((Persona) u);
                } else if (u instanceof Empresa) {
                    new VentanaEmpresa((Empresa) u);
                }
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
    }
}