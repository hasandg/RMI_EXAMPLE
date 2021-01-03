
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImplements extends UnicastRemoteObject implements RemoteInterface {


    protected ServerImplements() throws RemoteException {
        super(1889, new CustomClientSocketFactory(), new CustomServerSocketFactory());
    }

    public int add(int x, int y) {
        return (x + y);
    }
}