/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carnet;

import Logica.Controlador;
import Logica.Estudiante;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alejandro
 */
public class VentanaVisualizar extends JFrame{
    
    Controlador control;
    Estudiante estActual;
    FotoEstudiante fotoEstudiante;   
    JLabel lblNombre;
    JLabel lblCodigo;
    JLabel lblUniversidad;
    JLabel lblNombreEst;
    JLabel lblCodigoEst;
    JLabel lblUniversidadEst;
    JButton btnSiguiente;
    JButton btnAnterior;
    JButton btnSalir;
    private Dimension dmsn;

    
    public VentanaVisualizar(Controlador control){
        this.control = control;        
        this.setSize(420,250);
        this.setLayout(null);    
        this.setResizable(false);
       
        fotoEstudiante = new FotoEstudiante();
        fotoEstudiante.setBounds(20, 20, 120, 150);
        Dimension dmsn = fotoEstudiante.getSize();        
        fotoEstudiante.setPreferredSize(null);
        fotoEstudiante.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Foto"));
        this.add(fotoEstudiante);
        
        lblNombre = new JLabel("Nombre : ");
        lblNombre.setBounds(160, 40, 80, 30);
        this.add(lblNombre);
        
        lblNombreEst = new JLabel();
        lblNombreEst.setBounds(250, 40, 160, 30);
        this.add(lblNombreEst);
        
        lblCodigo = new JLabel("Código : ");
        lblCodigo.setBounds(160, 80, 180, 30);
        this.add(lblCodigo);                
                
        lblCodigoEst = new JLabel();
        lblCodigoEst.setBounds(250, 80, 160, 30);
        this.add(lblCodigoEst);
        
        lblUniversidad = new JLabel("Universidad : ");
        lblUniversidad.setBounds(160, 120, 160, 30);
        this.add(lblUniversidad);
        
        lblUniversidadEst = new JLabel();
        lblUniversidadEst.setBounds(250,120,160,30);
        this.add(lblUniversidadEst);
        
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBounds(300, 180, 100, 20);
        btnSiguiente.addActionListener(control);
        this.add(btnSiguiente);
        
        btnAnterior = new JButton("Anterior");
        btnAnterior.setBounds(10, 180, 100, 20);
        btnAnterior.addActionListener(control);
        this.add(btnAnterior);
        
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(160, 180, 100, 20);
        btnSalir.addActionListener(control);
        this.add(btnSalir);
        
    }

    public JButton getBtnSiguiente() {
        return btnSiguiente;
    }

    public JButton getBtnAnterior() {
        return btnAnterior;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JPanel getFotoEstudiante() {
        return fotoEstudiante;
    }
    
    public Dimension getDmsn() {
        return dmsn;
    }

    public void setEstActual(Estudiante estActual) {
        this.estActual = estActual;                
        this.lblNombreEst.setText(estActual.getNombre());
        this.lblCodigoEst.setText(estActual.getCodigo());
        this.lblUniversidadEst.setText(estActual.getUniversidad());
        
        this.repaint();
    }
    
    class FotoEstudiante extends JPanel{     
        
        @Override
        public void paint(Graphics g){            
            String url = estActual.getDirFoto();             
            ImageIcon img = new ImageIcon (url);            
            g.drawImage(img.getImage(), 5, 10, this.getWidth()-10,this.getHeight()-15,null);
            this.setOpaque(false);
            super.paint(g);
        }
    }
    
}
