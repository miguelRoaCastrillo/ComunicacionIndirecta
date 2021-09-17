package Hilos;

import Controllers.SimpleChatController;
import Views.SimpleChatView;
import java.io.File;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ObjectMessage;
import org.jgroups.Receiver;
import org.jgroups.util.MessageBatch;


/**
 *
 * @author Miguel Roa
 */
public class HiloRecibirMensajes implements Runnable{

    JChannel channel;
    Receiver recibidor;
    SimpleChatView simpleChat;
    SimpleChatController controllerReceiver;
    
    //Constructor
    public HiloRecibirMensajes(
        JChannel channel,
        Receiver recibidor,
        SimpleChatView simpleChat,
        SimpleChatController controllerReceiver
    ) {
        this.channel = channel;
        this.recibidor = recibidor;
        this.simpleChat = simpleChat;
        this.controllerReceiver = controllerReceiver;
    }        

    @Override
    public void run() {
        int segundos = 0, delay = 500;     
        Message msg = new ObjectMessage();        
        MessageBatch messages = null;
        
        //Lectura del canal para colocar en la ventana de chat        
        
        if(channel.getState().equals("CONNECTED")){
            while(true){            

                //System.out.println("Segundos transcurridos: " + segundos);                                                               
                //segundos++;                                            
                
                //En caso de que fallen los mensajes fijados para el profesor
                if(this.simpleChat.getTxtChat().getText() == null || this.simpleChat.getTxtChat().getText().equals("")){
                    File archivo = new File("mensajesFijados.txt");
                    
                    if(archivo.exists()){
                        controllerReceiver.leerMensajeFijado();
                    }                                                            
                }

                try{      
                    Thread.sleep(delay);
                }catch(InterruptedException error){
                    System.out.println("Error en delay de hilo: " + error.toString());
                }
                
                
            }     
        }else{
            System.out.println("El canal se encuentra actualmente desconectado");
        }           
    }   
}
