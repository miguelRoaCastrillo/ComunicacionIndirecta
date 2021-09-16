
import org.jgroups.JChannel;
import org.jgroups.Receiver;


/**
 *
 * @author Miguel Roa
 */
public class HiloRecibirMensajes implements Runnable{

    JChannel channel;
    Receiver recibidor;
    
    //Constructores
    public HiloRecibirMensajes() {
        
    }
    
    public HiloRecibirMensajes(JChannel channel, Receiver recibidor) {
        this.channel = channel;
        this.recibidor = recibidor;
    }        

    @Override
    public void run() {
        try{
            
        }catch(Exception error){
            System.out.println("Existe un erro al intentar correr el hilo para leer los mensajes del canal");
        }
    }
    
}
