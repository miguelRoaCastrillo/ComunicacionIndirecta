package Controllers;

import Views.SimpleChatView;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
        
        //Se inicializa el reciever
        try{
            this.start();            
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
            
            String mensaje = simpleChat.getTxtMensaje().getText();                                    
            Message msg = new ObjectMessage(null, mensaje);                       
            
            try{
                this.channel.send(msg);
            }catch(Exception error){
                System.out.println("Existe un error para enviar dicho mensaje fijado: " + error.toString());
                     
            }
        }
    }
    /**
     * Permite leer el mensaje fijado que dejó el profesor
     * 
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
        
        String mensaje = msg.getObject().toString();
        
        /**
         * Se tendría que guardar dicho mensaje en un archivo
         * para que todos los estudiantes a través del tiempo de ejecución puedan verlo
         * aún así no habiendo estado ellos cuando el profesor lo envió, por tanto se agrega a continuación
        */
        
        try{
            //En la ruta base de este projecto de Java
            File archivoMensajesFijados = new File("mensajesFijados.txt");
            
            //Si el archivo no existe se crea
            if(!archivoMensajesFijados.exists()){                
                archivoMensajesFijados.createNewFile();
            }
            
            //Se escribe el contenido del mensaje sobre el archivo indicado            
            FileWriter escritor = new FileWriter(archivoMensajesFijados);
            
            for(int i=0; i < mensaje.length() ; i++){
                escritor.write(mensaje.charAt(i));
            }
            
            escritor.close();
            
            //Se lee el nuevo mensaje fijado
            leerMensajeFijado();  
            
        }catch(IOException error){
            System.out.println("Existe un error en el archivo de mensajes fijados: " + error.toString());
        }           
    }           
}
