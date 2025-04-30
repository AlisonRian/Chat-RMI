
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor{
    public static void main(String[] args){
        try{

            //Cria uma instância do objeto remoto.
            MensagemImpl msg = new MensagemImpl();
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
