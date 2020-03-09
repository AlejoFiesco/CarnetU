package carnet;

import Logica.Controlador;
import Logica.Estudiante;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaCorregir extends JFrame {

    ArrayList<Estudiante> est;    
    int estEditar;
    boolean existe;
    Controlador control;
    JTextField txtNombre;
    JLabel lblNombre;
    JLabel lblCodigo;
    JLabel lblCarrera;
    JTextField txtBuscar;
    JTextField txtCarrera;
    JTextField txtCodigo;
    JButton btnBuscar;
    JButton btnCorregir;
    JButton btnEliminar;
    JComboBox cbUniversidad;
    JLabel lblUniversidad;
    String[] universidades = {"U. Distrital", "U. Nacional"};

    public VentanaCorregir(ArrayList<Estudiante> estudiantes, Controlador ctrl) {
        
        control = ctrl;
        est = estudiantes;
        this.setSize(500, 300);
        this.setLayout(null);
        this.setTitle("Corregir Estudiante");        

        txtNombre = new JTextField();
        lblNombre = new JLabel("Nombre");
        lblCodigo = new JLabel("Codigo");
        lblCarrera = new JLabel("Carrera");
        btnEliminar = new JButton("Eliminar");
        cbUniversidad = new JComboBox(universidades);
        txtCodigo = new JTextField();
        txtBuscar = new JTextField();
        txtCarrera = new JTextField();
        btnBuscar = new JButton("Buscar Código");
        btnCorregir = new JButton("Corregir");
        lblUniversidad = new JLabel("Universidad");

        lblNombre.setBounds(20, 20, 120, 30);
        txtNombre.setBounds(160, 20, 100, 30);
        btnCorregir.setBounds(320, 220, 150, 30);
        txtCodigo.setBounds(160, 60, 100, 30);
        lblCodigo.setBounds(20, 60, 120, 30);
        lblCarrera.setBounds(20, 100, 120, 30);
        txtCarrera.setBounds(160, 100, 100, 30);
        txtBuscar.setBounds(20, 180, 150, 30);
        btnBuscar.setBounds(20, 220, 150, 30);
        btnEliminar.setBounds(320, 180, 150, 30);
        cbUniversidad.setBounds(160, 140, 150, 30);
        lblUniversidad.setBounds(20, 140, 100, 30);
        btnEliminar.addActionListener(control);
        btnCorregir.addActionListener(control);
        btnBuscar.addActionListener(control);

        this.add(btnBuscar);
        this.add(txtBuscar);
        this.add(lblCarrera);
        this.add(txtCarrera);
        this.add(txtNombre);
        this.add(lblNombre);
        this.add(btnCorregir);
        this.add(lblCodigo);
        this.add(txtCodigo);
        this.add(btnEliminar);
        this.add(cbUniversidad);
        this.add(lblUniversidad);
    }

    public JTextField getTxtRegistrar() {
        return txtNombre;
    }

    public JTextField getTxtContraseña() {
        return txtCodigo;
    }

    public JButton getBtnRegistrar() {
        return btnCorregir;
    }
    public boolean guardar(ArrayList<Estudiante> est) {
        boolean guardado = false;
        try {
            FileOutputStream fout = new FileOutputStream("./data/listas.bin");
            ObjectOutputStream writer = new ObjectOutputStream(fout);
            writer.writeObject(est);
            writer.close();
            fout.close();
            guardado = true;
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, ioe.getMessage());
        }
        return guardado;
    }   

    public void setEstEditar(int estEditar) {
        this.estEditar = estEditar;
    }

    public int getEstEditar() {
        return estEditar;
    }

    public boolean isExiste() {
        return existe;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JTextField getTxtCarrera() {
        return txtCarrera;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JButton getBtnBuscar() {
        if(btnBuscar == null){
            btnBuscar = new JButton("Buscar Código");
        }
        return btnBuscar;
    }

    public JButton getBtnCorregir() {
        return btnCorregir;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JComboBox getCbUniversidad() {
        return cbUniversidad;
    }
}

