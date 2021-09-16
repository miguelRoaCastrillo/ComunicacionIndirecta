package Hilos;

import Controllers.SimpleChatController;
import Views.SimpleChatView;
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
        int segundos = 0, delay = 2000;     
        Message msg = new ObjectMessage();        
        MessageBatch messages = null;
        
        //Lectura del canal para colocar en la ventana de chat
        System.out.println("Este es el view del canal: " + channel.getView());   
        System.out.println("Estado del canal: " + channel.getState());
        
        if(channel.getState().equals("CONNECTED")){
            while(true){            

                System.out.println("Segundos transcurridos: " + segundos);                                                               
                segundos++;                                            

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
