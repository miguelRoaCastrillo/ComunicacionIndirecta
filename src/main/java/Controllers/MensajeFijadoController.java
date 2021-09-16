package Controllers;

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
public class MensajeFijadoController implements Receiver{
    
    //Variables
    String mensajeFijado;
    SimpleChatView simpleChat;
    JChannel channel; 
    Boolean esProfesor;
    
    //Constructor
    public MensajeFijadoController(SimpleChatView simpleChat, Boolean esProfesor){
        
        //Inicializando las variables de la clase
        this.simpleChat = simpleChat;        
        this.esProfesor = esProfesor;       
        
        //Se inicialoa el reciever
        try{
            this.start();
            enviarMensaje();
        }catch(Exception error){
            System.out.println("Existe un error al iniciar el canal para el mensaje fijado: " + error.toString());
        }
    }
    
    //Set y get
    public String getMensajeFijado() {
        return mensajeFijado;
    }

    public void setMensajeFijado(String mensajeFijado) {
        this.mensajeFijado = mensajeFijado;
    }

    public SimpleChatView getSimpleChat() {
        return simpleChat;
    }

    public void setSimpleChat(SimpleChatView simpleChat) {
        this.simpleChat = simpleChat;
    }
    
    //Para ejecucion de JGroups  
    /**
     * Envia mensaje a JGroup indicado de que se quiere fijar dicho mensaje
    */
    public void enviarMensaje(){
        if(this.channel.getState().equals("CONNECTED")){
            
            String mensaje = "Este es el mensaje fijado";
            
            Message msg = new ObjectMessage(null, mensaje);
            
            try{
                this.channel.send(msg);
            }catch(Exception error){
                System.out.println("Existe un error para enviar dicho mensaje fijado: " + error.toString());
                     
            }
        }
    }
    /**
     * Inicia el canal de mensajería para el mensaje fijado
    */
    private void start() throws Exception {
        channel = new JChannel().setReceiver(this).connect("MensajeFijado");            
    }
    
    @Override
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }

    @Override
    public void receive(Message msg) {
        System.out.println("Recibiendo mensaje fijado");
        System.out.println(msg.getSrc() + ": " + msg.getObject()); 
        
        this.simpleChat.getTxtMensajeFijado().setText(msg.getObject().toString());                
    }           
}
