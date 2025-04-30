import java.rmi.RemoteException;

public class MensagemImpl implements Mensagem{
    MensagemImpl() throws RemoteException{
        super();
    }
    @Override
    public String enviarMensagem(String cliente, String mensagem, String dataHora) throws RemoteException {
        System.out.println(" - "+cliente+"("+dataHora+"): "+mensagem);
        return "Mensagem enviada com sucesso!";
    }
}
