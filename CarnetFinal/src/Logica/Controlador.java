/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import carnet.VentanaCorregir;
import carnet.VentanaRegistrar;
import carnet.VentanaVisualizar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Controlador implements ActionListener {

    private VentanaRegistrar vReg;
    private VentanaCorregir vCor;
    private VentanaVisualizar vVis;
    private ArrayList<Estudiante> estudiantes;
    private String dirTemp = "";
    boolean existe;
    int uBuscada;
    int estEditar;
    int estVisualizado;
    public String[] universidades = {"U. Distrital", "U. Nacional"};

    public Controlador() {
        iniciarPrograma();
        vCor = new VentanaCorregir(estudiantes, this);
    }

    public void iniciarPrograma() {
        vReg = new VentanaRegistrar(estudiantes, this);
        vReg.setVisible(true);
        vVis = new VentanaVisualizar(this);
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

    public boolean leer() {
        boolean leido = false;
        try {
            FileInputStream fin = new FileInputStream("./data/listas.bin");
            ObjectInputStream reader = new ObjectInputStream(fin);
            try {
                estudiantes = ((ArrayList) reader.readObject());
            } catch (ClassNotFoundException cnfe) {
                JOptionPane.showMessageDialog(null, "No se encontró la clase a leer");
                leido = false;
            }
            leido = true;
            reader.close();
            fin.close();
        } catch (IOException ioe) {

        }
        return leido;
    }

    public String insertarFoto() {
        JFileChooser foto = new JFileChooser();
        foto.showOpenDialog(null);
        File imagen = foto.getSelectedFile();
        return imagen.getPath();
    }

    public boolean comprobarSiguiente(int i, int uBuscada) {
        boolean esIgual = false;
        if (estudiantes.get(i).getUniversidad().equals(universidades[uBuscada])) {
            esIgual = true;
        }
        return esIgual;
    }

    public void dibujarFoto(Estudiante est, Graphics grafico) {
        ImageIcon img = new ImageIcon(est.getDirFoto());
        Dimension dim = vVis.getDmsn();
        grafico.drawImage(img.getImage(), vVis.getFotoEstudiante().getX(), vVis.getFotoEstudiante().getY(), dim.width, dim.height, null);
        vVis.getFotoEstudiante().setOpaque(false);
        vVis.paintComponents(grafico);
        vVis.paint(grafico);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(vReg.getBtnRegistrar())) {
            if (vReg.getTxtCodigo().getText().length() == 11) {
                vReg.getTxtCodigo().setForeground(Color.black);
                Estudiante estTemp = new Estudiante();
                boolean error = false;
                if (!vReg.getTxtCodigo().getText().isEmpty() || !vReg.getTxtCarrera().getText().isEmpty() || !vReg.getTxtNombre().getText().isEmpty() | !vReg.getCbUniversidad().getSelectedItem().equals(-1)) {
                    try {
                        estTemp.setUniversidad(vReg.getCbUniversidad().getSelectedItem().toString());
                    } catch (Exception e) {
                        error = true;
                    }
                } else {
                    error = true;
                }
                estTemp.setCarrera(vReg.getTxtCarrera().getText());
                estTemp.setCodigo(vReg.getTxtCodigo().getText());
                estTemp.setNombre(vReg.getTxtNombre().getText());
                estTemp.setDirFoto(dirTemp);

                boolean exist = false;

                for (int i = 0; i < estudiantes.size(); i++) {
                    if (estudiantes.get(i).getCodigo().equals(vReg.getTxtCodigo().getText())) {
                        exist = true;
                        JOptionPane.showMessageDialog(null, "Este estudiante ya se encuentra registrado");
                    }
                }
                if (exist == false && error == false) {

                    if (guardar(estudiantes)) {
                        estudiantes.add(estTemp);
                        JOptionPane.showMessageDialog(null, "Estudiante " + estudiantes.size() + " añadido");
                        vReg.getTxtCarrera().setText("");
                        vReg.getTxtCodigo().setText("");
                        vReg.getTxtNombre().setText("");
                        vReg.getCbUniversidad().setSelectedIndex(-1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Hubo un error al momento de registrar el estudiante");
                    }
                }
            } else {
                vReg.getTxtCodigo().setForeground(Color.red);
            }
        }

        if (ae.getSource().equals(vReg.getBtnVerLista())) {
            estVisualizado = -1;
            if (!(estudiantes.isEmpty())) {
                if (vReg.getCheckDistrital().isSelected() && !vReg.getCheckNacional().isSelected()) {
                    uBuscada = 1;
                    do {
                        estVisualizado++;
                    } while (estVisualizado < estudiantes.size() && comprobarSiguiente(estVisualizado, uBuscada));
                    vVis.setEstActual(estudiantes.get(estVisualizado));
                    vVis.setVisible(true);
                    vVis.repaint();
                } else {
                    if (vReg.getCheckNacional().isSelected() && !vReg.getCheckDistrital().isSelected()) {
                        uBuscada = 0;
                        do {
                            estVisualizado++;
                        } while (estVisualizado < estudiantes.size() && comprobarSiguiente(estVisualizado, uBuscada));
                        vVis.setEstActual(estudiantes.get(estVisualizado));
                        vVis.setVisible(true);
                        vVis.repaint();
                    } else {
                        if ((!vReg.getCheckNacional().isSelected() && !vReg.getCheckDistrital().isSelected()) || (vReg.getCheckNacional().isSelected() && vReg.getCheckDistrital().isSelected())) {
                            estVisualizado = 0;
                            uBuscada = 2;
                            vVis.setEstActual(estudiantes.get(estVisualizado));
                            vVis.setVisible(true);
                            vVis.repaint();
                        }

                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay estudiantes registrados");
            }

        }

        if (ae.getSource().equals(vReg.getBtnCorregir())) {
            vCor.setVisible(true);
        }

        if (ae.getSource().equals(vReg.getBtnBorrarTodo())) {
            estudiantes.clear();
            JOptionPane.showMessageDialog(null, "Todos los estudiantes han sido eliminados");
        }
        if (ae.getSource().equals(vReg.getBtnFoto())) {
            dirTemp = insertarFoto();
        }

        //Ventana Corregir
        if (ae.getSource().equals(vCor.getBtnBuscar()) && vCor.getTxtBuscar().getText().length() == 11 && estudiantes.size() > 0) {
            existe = false;
            for (int i = 0; i < estudiantes.size(); i++) {
                if (estudiantes.get(i).getCodigo().equals(vCor.getTxtBuscar().getText())) {
                    existe = true;
                    estEditar = i;
                }
            }

            if (existe = true) {
                vCor.getTxtCarrera().setText(estudiantes.get(vCor.getEstEditar()).getCarrera());
                vCor.getTxtCodigo().setText(estudiantes.get(estEditar).getCodigo());
                vCor.getTxtNombre().setText(estudiantes.get(estEditar).getNombre());
                if (estudiantes.get(estEditar).getUniversidad().equals(universidades[0])) {
                    vCor.getCbUniversidad().setSelectedItem(universidades[0]);
                } else {

                    if (estudiantes.get(estEditar).getUniversidad().equals(universidades[1])) {
                        {
                            vCor.getCbUniversidad().setSelectedItem(universidades[1]);
                        }
                    }
                }
                vCor.getTxtBuscar().setText("");
            }
        }

        if (ae.getSource().equals(vCor.getBtnCorregir())) {
            if (vCor.getTxtCodigo().getText().length() == 11 && existe == true) {
                estudiantes.get(estEditar).setCarrera(vCor.getTxtCarrera().getText());
                estudiantes.get(estEditar).setCodigo(vCor.getTxtCodigo().getText());
                estudiantes.get(estEditar).setNombre(vCor.getTxtNombre().getText());
                estudiantes.get(estEditar).setUniversidad(vCor.getCbUniversidad().getSelectedItem().toString());
                vCor.getTxtCarrera().setText("");
                vCor.getTxtCodigo().setText("");
                vCor.getTxtNombre().setText("");
                estudiantes.get(estEditar).visualizar();
                existe = false;
                vCor.getTxtBuscar().setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Hay datos incorrectos");
            }
        }

        if (ae.getSource().equals(vCor.getBtnEliminar())) {
            if (existe == true) {
                String nomEliminado = estudiantes.get(estEditar).getNombre();
                estudiantes.remove(estEditar);
                existe = false;
                vCor.getTxtCarrera().setText("");
                vCor.getTxtCodigo().setText("");
                vCor.getTxtNombre().setText("");
                vCor.getTxtBuscar().setText("");
                JOptionPane.showMessageDialog(null, "Estudiante " + nomEliminado + " eliminado de la base de datos");
            }
        }

        if (ae.getSource().equals(vVis.getBtnSiguiente())) {
            int temp = estVisualizado;
            boolean encontrada = false;
            if (uBuscada != 2) {
                do {
                    try{
                        temp++;
                        comprobarSiguiente(temp,uBuscada);
                        encontrada = true;
                        estVisualizado = temp;
                    }catch(Exception e){
                        vVis.setVisible(false);
                        encontrada = false;
                    }
                } while (estVisualizado < estudiantes.size() && comprobarSiguiente(estVisualizado, uBuscada));                
            } else {
                try {
                    estVisualizado++;
                    estudiantes.get(estVisualizado);
                    encontrada = true;
                } catch (Exception e) {
                    vVis.setVisible(false);
                    encontrada = false;
                }
            }
            
            if (encontrada) {
                vVis.setEstActual(estudiantes.get(estVisualizado));
            } else {
                vVis.setVisible(false);
            }
        }
        
        if (ae.getSource().equals(vVis.getBtnAnterior())) {
            int temp = estVisualizado;
            boolean encontrada = false;
            if (uBuscada != 2) {
                do {
                    estVisualizado--;
                    if (!(estVisualizado >0)) {
                        encontrada = false;
                    }else{
                        encontrada = true;
                    }
                } while (estVisualizado >0 && comprobarSiguiente(estVisualizado, uBuscada));
                estVisualizado = temp;
            } else {
                try {
                    estVisualizado--;
                    estudiantes.get(estVisualizado);
                    encontrada = true;
                } catch (Exception e) {
                    vVis.setVisible(false);
                    encontrada = false;
                }
            }
            
            if (encontrada) {
                vVis.setEstActual(estudiantes.get(estVisualizado));
            } else {
                vVis.setVisible(false);
            }
        }
        
        if(ae.getSource().equals(vVis.getBtnSalir())){
            vVis.setVisible(false);
        }
        guardar(estudiantes);
    }
}
