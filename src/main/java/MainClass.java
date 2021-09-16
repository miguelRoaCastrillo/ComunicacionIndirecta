/**
 * Proyecto con fines académicos para la UNEG, específicamente para la materia de sistemas distribuidos
 */

//Imports
import Controllers.MainController;
import Views.CreacionUsuarioView;
import Views.MainAppFrame;

/**
 *
 * @author Miguel Roa
 */
public class MainClass {
    public static void main(String []args){
        
        MainAppFrame mainFrame = new MainAppFrame();        
        
        MainController mainController = new MainController(mainFrame);
                
        CreacionUsuarioView creacionUsuario = new CreacionUsuarioView(mainController);         
        creacionUsuario.setBounds(0, 0, 550, 500);                
        
        mainFrame.add(creacionUsuario);        
        mainFrame.setTitle("Comunicación Indirecta-Grupos / Miguel Roa");        
        mainFrame.setVisible(true);        
        mainFrame.setBounds(0, 0, 570, 500);
        mainFrame.setLocationRelativeTo(null);
        
    }
}
