import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Mensagem extends Remote {
    void enviarMensagem(String cliente,String mensagem, String dataHora) throws RemoteException;
    void cadastrarCliente(MensagemCliente cliente) throws RemoteException;
}
