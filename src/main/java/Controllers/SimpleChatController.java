package Controllers;

import Hilos.HiloRecibirMensajes;
import Views.SimpleChatView;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ObjectMessage;
import org.jgroups.Receiver;
import org.jgroups.View;

/**
 *
 * @author Miguel Roa
 */
public class SimpleChatController implements Receiver { 
    
    JChannel channel;
    Receiver recibidor;
    HiloRecibirMensajes hiloMensajes;
    SimpleChatView simpleChat;
    
    //Constructor
    public SimpleChatController(SimpleChatView simpleChat) throws Exception  {
        
        System.out.println("Inicia el controlador del chat");
        
        try{
            
            this.start();            
            this.simpleChat = simpleChat;
            
            this.correHilo();
            
        }catch(Exception error){
            System.out.println("Existe un error al inicializar el canal de mensajería: " + error.toString());
            throw error;
        }
    }          
      
    /**
     * Muestra un mensaje de un usuario en la ventana de chat general
     * 
     * @return mensaje
     */
    public String enviarMensaje(){
        
        String mensaje = this.simpleChat.getTxtMensaje().getText();
        
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
    
    /**
     * Crea el hilo y lo inicia
     *
     */
    public final void correHilo(){
        try{
            Runnable runnable = new HiloRecibirMensajes(channel, recibidor, simpleChat);
            Thread hiloRecibirMensajes = new Thread(runnable);
            hiloRecibirMensajes.start();   
        }catch(Exception error){
            System.out.println("Existe un problema al correr el hilo para la lectura de mensajes: " + error.toString());
        }       
    }
    
    @Override
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }

    @Override
    public void receive(Message msg) {
        System.out.println(msg.getSrc() + ": " + msg.getObject());
    }
}
