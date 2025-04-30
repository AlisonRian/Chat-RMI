import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Servidor implements Mensagem {

    // O cliente utiliza a instância remota desse método para enviar a mensagem para o servidor,
    // o servidor recebe a mensagem e encaminha para todos os clientes.
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

    // O cliente quando inicia a conexão com o servidor, chama esse método para adicionar seu stub(referência)
    // na lista que o servidor armazena com todos os clientes conectados e posteriormente
    // utiliza para enviar a mensagem para os outros usuários.
    @Override
    public void cadastrarCliente(MensagemCliente cliente) throws RemoteException {
        clientes.add(cliente);
    }

    List<MensagemCliente> clientes = new ArrayList<>();

    public static void main(String[] args){
        try{

            //Cria uma instância do objeto remoto.
            Mensagem msg = new Servidor();
            UnicastRemoteObject.exportObject(msg, 1099);

            // Cria o RMI Registry na porta 1099 (caso ainda não tenha sido iniciado)
            // um servidor simples de diretório onde o servidor RMI pode registrar objetos remotos
            // para que clientes possam localizá-los e invocá-los.
            Registry registry = LocateRegistry.createRegistry(1099);

            // Registra o objeto remoto no RMI Registry
            registry.rebind("servidor", msg);

            System.out.println("Servidor RMI iniciado.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
