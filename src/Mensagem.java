import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Mensagem extends Remote {
    String enviarMensagem(String cliente,String mensagem, String dataHora) throws RemoteException;
}
