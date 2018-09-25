/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.vistas.reyes;

import Ve.com.biller.estructuras.reyes.SidesHamburguesa;
import Ve.com.biller.eventos.reyes.EventoEMDeleteAtributes;
import Ve.com.biller.eventos.reyes.EventoEMDialogAddAtrib;
import Ve.com.biller.helpers.reyes.CustomDialog;
import Ve.com.biller.helpers.reyes.ImageManager;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author luisr
 */
public class DialogoEditAttributes extends CustomDialog{
    JPanel panelContainer;
    SidesHamburguesa[] sidesVec;
    JScrollPane scroll;
    
    public DialogoEditAttributes(SidesHamburguesa[] sides) {
        this.sidesVec=sides;
        createGUI();
    }
    private void createGUI(){
        JButton buttonAddSide;
        ImageIcon imgIconAdd=null;
        ImageIcon imgIconExit=null;
        int  j=0;
        
        panelContainer= new JPanel(new MigLayout());
        for (int i = 0; i < sidesVec.length; i++) {
            j++;
            panelContainer.add(buildAttrib(sidesVec[i],i),"cell 0 "+i+" 2 1");            
        }
        try {
            imgIconAdd= new ImageIcon(ImageIO.read(PanelLoginUsersManagement.class.getResource(DatosBotones.ICONOS_BOTONES_ORDENES[0])));//imagen de añadir
            imgIconExit= new ImageIcon(ImageIO.read(PanelLoginUsersManagement.class.getResource(DatosBotones.ICONOS_BOTONES_MAIN[5])));//imagen de X salir
        } catch (IOException ex) {}
        
        buttonAddSide= new JButton("Añadir",imgIconAdd);
        buttonAddSide.setFont(Tipografia.ARIAL_14);
        buttonAddSide.setForeground(Color.BLACK);
        buttonAddSide.addActionListener(new EventoEMDialogAddAtrib(this));
        
        buttonClose.setText("Cancelar");
        
        buttonClose.setFont(Tipografia.ARIAL_14);
        buttonClose.setForeground(Color.BLACK);
        buttonClose.setIcon(imgIconExit);
            
        panelContainer.add(buttonAddSide,   "cell 0 "+j+"1 1");
        panelContainer.add(buttonClose,     "cell 1 "+j+"1 1");

        
        scroll= new JScrollPane(panelContainer);
        scroll.setPreferredSize(new Dimension(400, 400));
        scroll.getVerticalScrollBar().setUnitIncrement(8);
        this.createDialog(scroll, "Acompañantes");
    }
    private JPanel buildAttrib(SidesHamburguesa sides,int ref){
        JPanel panel;
        ImageIcon imgIcon=null;
        int size = sides.getDescripciones().length;
        JPanel[] panelBar= new JPanel[size];
        JLabel[] labelNombre= new JLabel[size];
        JLabel[] labelDesc= new JLabel[size];
        JButton[] buttonDel= new JButton[size];
        
        panel= new JPanel(new MigLayout());
        try {
            imgIcon=ImageManager.iconResize(new ImageIcon(ImageIO.read(PanelLoginUsersManagement.class.getResource(DatosBotones.ICONOS_BOTONES_FACTURA[2]))),16,16);//imagen de eliminar
        } catch (IOException ex) {}
        
        for (int i = 0; i < size; i++) {
            panelBar[i]= new JPanel(new MigLayout());
            panelBar[i].setOpaque(false);

            labelNombre[i]= new JLabel(sides.getLabel());
            labelNombre[i].setFont(Tipografia.ARIAL_BOLD_14);
            labelNombre[i].setForeground(Color.BLACK);
            
            labelDesc[i]= new JLabel(sides.getDescripciones()[i]);
            labelDesc[i].setFont(Tipografia.ARIAL_14);
            labelDesc[i].setForeground(Color.BLACK);
            
            buttonDel[i]= new JButton(imgIcon);
            buttonDel[i].setFont(Tipografia.ARIAL_14);
            buttonDel[i].setForeground(Color.BLACK);
            buttonDel[i].addActionListener(new EventoEMDeleteAtributes(sides.getLabel(), sides.getDescripciones()[i], panel, panelBar[i]));
            
            panelBar[i].add(labelNombre[i],   "cell 0 0 1 1,width 150px");
            panelBar[i].add(labelDesc[i],     "cell 1 0 1 1,width 100px");
            panelBar[i].add(buttonDel[i],     "cell 2 0 1 1");
            
            panel.add(panelBar[i],"cell 0 "+i+" 1 1");

        }
        
        if (ref%2==0) {
            panel.setBackground(Color.WHITE);
        }
        
        return panel;
    }    
    
    
}
