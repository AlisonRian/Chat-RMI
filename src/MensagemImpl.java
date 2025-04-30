import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class MensagemImpl implements Mensagem{
    private final List<MensagemCliente> clientes = new ArrayList<>();

    @Override
    public void enviarMensagem(String cliente, String mensagem, String dataHora) throws RemoteException {
        for(MensagemCliente c : clientes){
            try {
                c.receberMensagem(" - "+cliente+"("+dataHora+"): "+mensagem);
            }catch (Exception e){
                System.out.println("Erro ao enviar mensagem para cliente: " + e.getMessage());
            }
        }
        System.out.println(" - "+cliente+"("+dataHora+"): "+mensagem);
    }

    @Override
    public void cadastrarCliente(MensagemCliente cliente) throws RemoteException {
        clientes.add(cliente);
    }
}
