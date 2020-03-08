import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class Server extends Observable implements Runnable
{

    String ip = "localhost";
    int port = 5000;



    private void createServer()
    {
        run();
    }

    @Override
    public void run()
    {

        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;
        ServerSocket server;
        Socket socket = null;
        Menu menu = new Menu();

        LogicaServer logicaServer = new LogicaServer();
        int iMsg = 0;
        try
        {

            server = new ServerSocket(port);
            System.out.println("Servidor iniciado");

            while (true)
            {
                socket = server.accept();
                System.out.println("cliente conectado");
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());


                String mensaje = dataInputStream.readUTF();
                System.out.println(">>> " + mensaje + "\n" +
                        "Mensaje numero: " + iMsg);
                dataInputStream.readUTF();


                if (mensaje.contains("hola"))
                {
                    dataOutputStream.writeUTF(">>>Hola buenas tardes\n" +
                            "" + menu.showMenu());
                    this.setChanged();
                    this.notifyObservers(mensaje);
                    this.clearChanged();
                }
                if (mensaje.contains("desconectar"))
                {
                    socket.close();
                    System.out.println(" el cliente desconectado");
                }
                if (logicaServer.checkOnStringNumber(mensaje))
                {
                    int a = logicaServer.retNumber(Integer.parseInt(mensaje));
                    dataOutputStream.writeUTF("toma tu ticket: " + a);
                    dataOutputStream.writeUTF("Mensaje numero: " + iMsg);
                }
                iMsg = +1;
//                dataOutputStream.writeUTF("Mensaje numero: "+ iMsg);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

