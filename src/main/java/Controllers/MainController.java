/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.UserModel;
import Views.CreacionUsuarioView;
import Views.MainAppFrame;
import Views.SimpleChatView;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel Roa
 */
public class MainController {
    
    MainAppFrame mainFrame;
    JOptionPane pane;
    UserModel usuario; 
    CreacionUsuarioView creacionUsuarioView;
    
    public MainController(MainAppFrame mainFrame){
        this.mainFrame = mainFrame;
    }
        
    public boolean mostrarChat(
            UserModel usuario,
            Boolean esProfesor,
            CreacionUsuarioView creacionUsuarioView,
            Color color
    ) throws Exception {
        try{                                                  
                                    

            int resp = JOptionPane.showConfirmDialog(
                mainFrame,
                "¿Está seguro de los datos y quiere entrar con dicho usuario?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
            ); 
            
            /*
                this.creacionUsuarioView = creacionUsuarioView;

                this.creacionUsuarioView.getLabelLoading().setText("CARGANDO...");
                this.creacionUsuarioView.getLabelLoading().setVisible(true);
                this.creacionUsuarioView.revalidate();
                this.creacionUsuarioView.repaint();                       
            */
            
            if(resp == JOptionPane.YES_OPTION){
                                                                
                SimpleChatView simpleChat = new SimpleChatView(usuario, esProfesor, color);                                
                        
                simpleChat.setBackground(Color.white);
                simpleChat.setBounds(0,0,550,450);                 
            
                mainFrame.getContentPane().removeAll();                                
                mainFrame.getContentPane().add(simpleChat);                
                mainFrame.revalidate();
                mainFrame.repaint();
                return true;
            }
            
        return false;                                    
            
        }catch(HeadlessException error){
            System.out.println("Existe un error para mostrar el chat" + error.toString());
            throw error;
        }
    }
}
