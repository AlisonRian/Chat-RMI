import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite seu nome: ");
        String cliente = entrada.nextLine();
        String textoMensagem = "";
        while(!textoMensagem.equals("!!!")){
            try{
                // Conecta o RMI registry ao servidor.
                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                // Busca a referência do objeto remoto pelo nome que foi definido pelo servidor.
                Mensagem msg = (Mensagem) registry.lookup("Mensagem");

                LocalDateTime dataHora = LocalDateTime.now();
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm");

                System.out.println("Digite sua mensagem:");
                textoMensagem = entrada.nextLine();
                String mensagem = msg.enviarMensagem(cliente,textoMensagem, dataHora.format(formatador));

                System.out.println("Resposta do servidor: " + mensagem);
            }catch(Exception e){
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
