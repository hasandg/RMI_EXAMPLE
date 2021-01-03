import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.server.RMISocketFactory;


public class client extends JFrame {
    TextField t1 = new TextField(30);
    TextField t2 = new TextField(30);
    Label rs = new Label("SUM = 0");
    JButton b = new JButton("ADD");
    Panel p = new Panel(new GridLayout(4, 1, 5, 5));
    RemoteInterface stub;

    public client() {
        super("Client Side");
        setSize(250, 250);
        setLocation(300, 300);
        getContentPane().add(p, "North");
        p.add(t1);
        p.add(t2);
        p.add(rs);
        p.add(b);

        t1.setText("10");
        t2.setText("20");
        try {

            int timeoutMillis = 5000;

            RMISocketFactory.setSocketFactory(new  RMISocketFactory() {
                public Socket createSocket (String host, int port)
                        throws IOException {
                    Socket socket = new Socket();
                    socket.setSoTimeout(timeoutMillis);
                    socket.setSoLinger(false, 0);
                    socket.connect(new InetSocketAddress(host, port), timeoutMillis);
                    return socket;
                }

                public ServerSocket createServerSocket (int port)
                        throws IOException {
                    return new ServerSocket(port);
                }
            });


            //String ipp = JOptionPane.showInputDialog("Please enter the Ip and port of server");
            String ip = "rmi://localhost:1888/RMIAPPLICATION";
            stub = (RemoteInterface) Naming.lookup(ip);
           // s.
        } catch (Exception Exp) {
            JOptionPane.showMessageDialog(null, Exp.getMessage());
        }


        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int a = Integer.parseInt(t1.getText());
                int b = Integer.parseInt(t2.getText());
                try {
                    int sum = stub.add(a, b);
                    rs.setText("Sum of two no= " + sum);
                } catch (Exception exc) {
                   exc.printStackTrace();
                }
                System.out.println("Error!");
            }
        });
    }


    public static void main(String args[]) {
        client c = new client();
        c.setDefaultCloseOperation(EXIT_ON_CLOSE);
        c.setVisible(true);
    }
}