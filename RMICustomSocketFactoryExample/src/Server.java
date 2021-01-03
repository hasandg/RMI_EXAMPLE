import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {


    public static void main(String args[]) throws IOException {



        try {
            ServerImplements skeleton = new ServerImplements();

            Registry rgsty = LocateRegistry.createRegistry(1888);
            rgsty.rebind("RMIAPPLICATION", skeleton);
            System.out.println("Server has been started");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}