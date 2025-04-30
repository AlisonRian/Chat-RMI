
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Cliente implements MensagemCliente{

    @Override
    public void receberMensagem(String mensagem) throws RemoteException {
        System.out.println(mensagem);
    }

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite seu nome: ");
        final String nome = entrada.nextLine();
        String textoMensagem = "";
            try{
                Cliente cliente = new Cliente();
                // Cria uma instância do objeto do cliente.
                MensagemCliente stub = (MensagemCliente) UnicastRemoteObject.exportObject(cliente,0);
                // Conecta o RMI registry ao servidor.
                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                // Busca a referência do objeto remoto pelo nome que foi definido pelo servidor.
                Mensagem msg = (Mensagem) registry.lookup("servidor");
                // Envia o stub ao servidor, o servidor armazena para poder enviar as mensagens.
                msg.cadastrarCliente(stub);
                while(true){
                    LocalDateTime dataHora = LocalDateTime.now();
                    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm");
                    textoMensagem = entrada.nextLine();
                    msg.enviarMensagem(nome,textoMensagem, dataHora.format(formatador));
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

    }
}
