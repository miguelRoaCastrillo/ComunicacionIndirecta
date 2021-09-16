package Controllers;

import Views.SimpleChatView;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ObjectMessage;
import org.jgroups.Receiver;

/**
 *
 * @author Miguel Roa
 */
public class SimpleChatController { 
    
    JChannel channel;
    Receiver recibidor;
        
    public SimpleChatController() throws Exception {
        System.out.println("Inicia el controlador del chat");
        
        try{
            this.start();
        }catch(Exception error){
            System.out.println("Existe un error al inicializar el canal de mensajería: " + error.toString());
            throw error;
        }
    }          
      
    /**
     * Muestra un mensaje de un usuario en la ventana de chat general
     * 
     * @param simpleChatView
     * @return mensaje
     */
    public String enviarMensaje(SimpleChatView simpleChatView){
        
        String mensaje = simpleChatView.getTxtMensaje().getText();                
        if(!mensaje.isEmpty()){
            Message msg = new ObjectMessage(null, mensaje);
        
            try{            
                if(this.channel != null){           
                   this.channel.send(msg);           
                }
            }catch(Exception error){
                System.out.println("Existe un errro al intentar enviar el mensaje designado: " + error.toString());
            }

            return mensaje;
            
        }else{
            System.out.println("El mensaje está vacío, no se envia nada al canal");
            
            return "";
        }        
    }  
    
    /**
     * Inicia el canal de mensajería
    */
    private void start() throws Exception {

        channel = new JChannel(); // Usa la configuración estandar XML (udp.xml)
        channel.setReceiver(recibidor);
        channel.connect("SimpleChat");

    }
    
    /*
    * Recibe un mensaje del canal en cuestión
    */
    public void recibeMensaje(){
        try{
            
        }catch(Exception error){
            System.out.println("Existe un error al traer los mensajes del canal");
        }
    }
}
