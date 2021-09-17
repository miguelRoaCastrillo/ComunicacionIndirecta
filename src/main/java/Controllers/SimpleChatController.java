package Controllers;

import Hilos.HiloRecibirMensajes;
import Views.SimpleChatView;
import java.io.FileReader;
import java.io.IOException;
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
            this.simpleChat = simpleChat;                        
            this.start();                                    
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
        
        String mensaje = "";
        
        mensaje = mensaje + "Usuario: " + (this.simpleChat.getUsuario().getNombre() != null ? this.simpleChat.getUsuario().getNombre() : "");        
        mensaje = mensaje + " " + (this.simpleChat.getUsuario().getApellido() != null ? this.simpleChat.getUsuario().getApellido() : "");
        mensaje = mensaje + "\n";
        mensaje = mensaje + "Dice: " + this.simpleChat.getTxtMensaje().getText();       
        mensaje = mensaje + "\n";
        
        if(!mensaje.isEmpty()){
            
            Message msg = new ObjectMessage(null, mensaje);
        
            try{            
                if(this.channel != null){           
                   this.channel.send(msg);           
                }
            }catch(Exception error){
                System.out.println("Existe un errro al intentar enviar el mensaje designado: " + error.toString());
            }

            //Limpia el campo de mensaje para enviar otro de manera más fácil
            this.simpleChat.getTxtMensaje().setText("");
            
            return mensaje;
            
        }else{
            System.out.println("El mensaje está vacío, no se envia nada al canal");
            
            return "";
        }        
    }  
    
    /**
     * Para leer el archivo del mensaje fijado en caso de ser estudiante
    */
    public void leerMensajeFijado(){
        try{

            //Se lee el nuevo mensaje guardado en el archivo mencionado
            FileReader reader = new FileReader("mensajesFijados.txt");               

            int i;
            String newMensajeFijado = "";

            while(( i = reader.read() ) != -1){
                newMensajeFijado = newMensajeFijado + ((char) i);
            }               

            this.simpleChat.getTxtMensajeFijado().setText(newMensajeFijado);
            
        }catch(IOException error){
            System.out.println("Existe un error al intentar leer el archivo con el mensaje fijado del profesor: " + error.toString());
        }
    }

    /**
     * Inicia el canal de mensajería
    */
    private void start() throws Exception {
        channel = new JChannel().setReceiver(this).connect("SimpleChat");    
        correHilo();
    }
    
    /**
     * Crea el hilo y lo inicia
     *
     */
    public final void correHilo(){
        try{
            //Para contar el número de segundos ejecución
            Runnable runnable = new HiloRecibirMensajes(channel, recibidor, simpleChat, this);
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
        System.out.println("Recibiendo mensaje ");
        System.out.println(msg.getSrc() + ": " + msg.getObject());
        
        //Va sumando la cadena de caracteres en el chat completo
        String chatcompleto = this.simpleChat.getChatCompleto();
        if(chatcompleto == null){
            chatcompleto = "";
        }
        
        chatcompleto = chatcompleto + (msg.getObject() != null ? msg.getObject().toString() : "" );
        
        this.simpleChat.setChatCompleto(chatcompleto);
        this.simpleChat.getTxtChat().setText(this.simpleChat.getChatCompleto());
    }
}
