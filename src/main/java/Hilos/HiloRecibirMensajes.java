package Hilos;


import Views.SimpleChatView;
import org.jgroups.JChannel;
import org.jgroups.Receiver;


/**
 *
 * @author Miguel Roa
 */
public class HiloRecibirMensajes implements Runnable{

    JChannel channel;
    Receiver recibidor;
    SimpleChatView simpleChat;
    
    //Constructor
    public HiloRecibirMensajes(JChannel channel, Receiver recibidor, SimpleChatView simpleChat) {
        this.channel = channel;
        this.recibidor = recibidor;
        this.simpleChat = simpleChat;
    }        

    @Override
    public void run() {
        int segundos = 0, delay = 2000;                                                                
        while(true){            
            System.out.println("Segundos transcurridos: " + segundos);                                                               
            segundos++;
            
            //Lectura del canal para colocar en la ventana de chat
            
            try{      
                Thread.sleep(delay);
            }catch(InterruptedException error){
                System.out.println("Error en delay de hilo: " + error.toString());
            }
        }               
    }   
}
