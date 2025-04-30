import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MensagemCliente extends Remote {
    void receberMensagem(String mensagem) throws RemoteException;
}
