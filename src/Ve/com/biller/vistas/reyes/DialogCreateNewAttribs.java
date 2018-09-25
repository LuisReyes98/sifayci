/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.vistas.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Atributos;
import Ve.com.biller.eventos.reyes.EventoEMAddAtrib;
import Ve.com.biller.helpers.reyes.CustomDialog;
import Ve.com.biller.helpers.reyes.WordsOnlyDocFilter;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author luisr
 */
public class DialogCreateNewAttribs extends CustomDialog{
    JComboBox comboBox;
    String[] comboOption;
    JTextField textFieldType;
    JTextField textFieldName;
    JButton buttonCreate;
    JPanel panelContainer;
    JRadioButton[] radioButton= new JRadioButton[2];
    ButtonGroup buttonGroup;
    ImageIcon imgIconAdd;
    ImageIcon imgIconExit;
    JLabel labelTipo;
    JLabel labelNombre;
    DialogoEditAttributes dialogEdit;
    public DialogCreateNewAttribs(DialogoEditAttributes dialogEdit) {
        this.dialogEdit=dialogEdit;
        create();
    }
    private void create(){
        PlainDocument doc;
        panelContainer= new JPanel(new MigLayout());
        int size= CRUD_Atributos.componentesHamburguesa.length;
        comboOption= new String[size];
        for (int i = 0; i < size; i++) {
            comboOption[i]=CRUD_Atributos.componentesHamburguesa[i].getLabel();
        }
        
        comboBox= new JComboBox(comboOption);
        comboBox.setFont(Tipografia.ARIAL_14);
        comboBox.setForeground(Color.BLACK);
        
        radioButton[0]= new JRadioButton("Existente");
        radioButton[0].setFont(Tipografia.ARIAL_14);
        radioButton[0].setForeground(Color.BLACK);
        
        radioButton[1]= new JRadioButton("Nuevo");
        radioButton[1].setFont(Tipografia.ARIAL_14);
        radioButton[1].setForeground(Color.BLACK);
        
        buttonGroup=new ButtonGroup();
        buttonGroup.add(radioButton[0]);
        buttonGroup.add(radioButton[1]);
        
        textFieldName= new JTextField();
        textFieldName.setFont(Tipografia.ARIAL_14);
        textFieldName.setForeground(Color.BLACK);
        doc=(PlainDocument)textFieldName.getDocument();
        doc.setDocumentFilter(new WordsOnlyDocFilter());
        
        textFieldType= new JTextField();
        textFieldType.setFont(Tipografia.ARIAL_14);
        textFieldType.setForeground(Color.BLACK);
        doc=(PlainDocument)textFieldType.getDocument();
        doc.setDocumentFilter(new WordsOnlyDocFilter()); 
        
        labelTipo= new JLabel("Tipo:");
        labelTipo.setFont(Tipografia.ARIAL_BOLD_14);
        labelTipo.setForeground(Color.BLACK);   
        
        labelNombre= new JLabel("Nombre:");
        labelNombre.setFont(Tipografia.ARIAL_BOLD_14);
        labelNombre.setForeground(Color.BLACK);      
        
        try {
            imgIconAdd= new ImageIcon(ImageIO.read(PanelLoginUsersManagement.class.getResource(DatosBotones.ICONOS_BOTONES_ORDENES[0])));//imagen de añadir
            imgIconExit= new ImageIcon(ImageIO.read(PanelLoginUsersManagement.class.getResource(DatosBotones.ICONOS_BOTONES_MAIN[5])));//imagen de X salir
        } catch (IOException ex) {}
        
        buttonCreate= new JButton("Añadir",imgIconAdd);
        buttonCreate.setFont(Tipografia.ARIAL_BOLD_14);
        buttonCreate.setForeground(Color.BLACK);
        buttonCreate.addActionListener(new EventoEMAddAtrib(this, dialogEdit));
        
        buttonClose.setText("Cancelar");
        buttonClose.setFont(Tipografia.ARIAL_BOLD_14);
        buttonClose.setForeground(Color.BLACK);
        buttonClose.setIcon(imgIconExit);
        
        
        panelContainer.add(labelTipo,       "cell 0 0 2 1");
        panelContainer.add(radioButton[0],  "cell 0 1 1 1,width 100px");
        panelContainer.add(comboBox,        "cell 1 1 1 1");
        panelContainer.add(radioButton[1],  "cell 0 2 1 1,width 100px");        
        panelContainer.add(textFieldType,   "cell 1 2 1 1,growx");
        
        panelContainer.add(labelNombre,     "cell 0 3 1 1,width 100px");
        panelContainer.add(textFieldName,   "cell 1 3 1 1,growx");
        panelContainer.add(buttonCreate,    "cell 0 4 1 1");
        panelContainer.add(buttonClose,    "cell 1 4 1 1");
        
                
        this.createDialog(panelContainer, "Crear nuevo Acompañante");
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public String[] getComboOption() {
        return comboOption;
    }

    public JTextField getTextFieldType() {
        return textFieldType;
    }

    public JTextField getTextFieldName() {
        return textFieldName;
    }
    public JRadioButton[] getRadioButtons(){
        return radioButton;
    }
    
    
   
    
}
