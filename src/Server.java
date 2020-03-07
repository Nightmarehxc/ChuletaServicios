import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{

    public static void main(String[] args)
    {
        createServer();
    }

    private static void createServer()
    {
        String ip = "localhost";
        int port = 5000;
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
                System.out.println(">>> " + mensaje+ "\n" +
                        "Mensaje numero: "+iMsg);


                if (mensaje.contains("hola"))
                {
                    dataOutputStream.writeUTF(">>>Hola buenas tardes\n" +
                            ""+ menu.showMenu());

                }
                if (mensaje.contains("desconectar"))
                {
                    socket.close();
                    System.out.println(" el cliente desconectado");
                }
                if(logicaServer.checkOnStringNumber(mensaje))
                {
                    int a = logicaServer.retNumber(Integer.parseInt(mensaje));
                    dataOutputStream.writeUTF("toma tu ticket: "+ a);
                    dataOutputStream.writeUTF("Mensaje numero: "+ iMsg);
                }
                iMsg=+1;
//                dataOutputStream.writeUTF("Mensaje numero: "+ iMsg);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
