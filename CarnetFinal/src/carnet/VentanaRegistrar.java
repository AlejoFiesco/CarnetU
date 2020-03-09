/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carnet;

import Logica.Controlador;
import Logica.Estudiante;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaRegistrar extends JFrame {

    ArrayList<Estudiante> estudiantes;
    VentanaCorregir vCor;

    JTextField txtNombre;
    JLabel lblNombre;
    JLabel lblCodigo;
    JTextField txtCodigo;
    JTextField txtCarrera;
    JLabel lblCarrera;
    JButton btnRegistrar;
    JButton btnVerLista;
    JButton btnCorregir;

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtCarrera() {
        return txtCarrera;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnVerLista() {
        return btnVerLista;
    }

    public JButton getBtnCorregir() {
        return btnCorregir;
    }

    public JButton getBtnBorrarTodo() {
        return btnBorrarTodo;
    }

    public JComboBox getCbUniversidad() {
        return cbUniversidad;
    }

    public JCheckBox getCheckNacional() {
        return checkNacional;
    }

    public JCheckBox getCheckDistrital() {
        return checkDistrital;
    }

    public JButton getBtnFoto() {
        return btnFoto;
    }
    JButton btnBorrarTodo;
    JLabel lblUniversidad;
    JComboBox cbUniversidad;
    String dirTemp = "";
    JCheckBox checkNacional;
    JCheckBox checkDistrital;
    JLabel lblFoto;
    JButton btnFoto;
    Controlador control;

    public VentanaRegistrar(ArrayList<Estudiante> estudiantes, Controlador ctrl) {

        control = ctrl;
        this.estudiantes = estudiantes;
        this.setSize(500, 300);
        this.setLayout(null);
        this.setTitle("Registrar usuario");
        control.leer();
        lblFoto = new JLabel("Foto");
        btnFoto = new JButton("Seleccionar ruta");
        txtNombre = new JTextField();
        lblNombre = new JLabel("Nombre");
        lblCodigo = new JLabel("Codigo");
        txtCodigo = new JTextField();
        btnRegistrar = new JButton("Registrar Estudiante");
        lblCarrera = new JLabel("Carrera");
        txtCarrera = new JTextField();
        btnVerLista = new JButton("Ver carnés");
        btnCorregir = new JButton("Corregir/eliminar");
        btnBorrarTodo = new JButton("Borrar todo");
        lblUniversidad = new JLabel("Universidad");
        cbUniversidad = new JComboBox(control.universidades);
        checkNacional = new JCheckBox("U. Nacional");
        checkDistrital = new JCheckBox("U. Distrital");

        lblFoto.setBounds(20, 180, 120, 20);
        btnFoto.setBounds(100, 180, 160, 30);
        lblNombre.setBounds(20, 20, 120, 30);
        txtNombre.setBounds(160, 20, 100, 30);
        btnRegistrar.setBounds(20, 220, 170, 30);
        txtCodigo.setBounds(160, 60, 100, 30);
        lblCodigo.setBounds(20, 60, 120, 30);
        lblCarrera.setBounds(20, 100, 120, 30);
        txtCarrera.setBounds(160, 100, 100, 30);
        btnVerLista.setBounds(300, 60, 170, 30);
        btnCorregir.setBounds(300, 95, 170, 30);
        btnBorrarTodo.setBounds(300, 130, 170, 30);
        lblUniversidad.setBounds(20, 140, 100, 30);
        cbUniversidad.setBounds(160, 140, 100, 30);
        checkNacional.setBounds(293, 30, 100, 30);
        checkDistrital.setBounds(390, 30, 100, 30);

        btnFoto.addActionListener(control);
        btnRegistrar.addActionListener(control);
        btnVerLista.addActionListener(control);
        btnCorregir.addActionListener(control);
        btnBorrarTodo.addActionListener(control);
        cbUniversidad.setSelectedIndex(-1);

        this.add(txtNombre);
        this.add(lblNombre);
        this.add(btnRegistrar);
        this.add(lblCodigo);
        this.add(txtCodigo);
        this.add(lblCarrera);
        this.add(txtCarrera);
        this.add(btnCorregir);
        this.add(btnVerLista);
        this.add(btnBorrarTodo);
        this.add(lblUniversidad);
        this.add(cbUniversidad);
        this.add(checkDistrital);
        this.add(checkNacional);
        this.add(lblFoto);
        this.add(btnFoto);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
