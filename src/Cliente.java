import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente
{
    public static void main(String[] args)
    {
        final String ip = "127.0.0.1";
        final int port = 5000;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;
        boolean con = true;

        while (con)
        {
            try
            {

                Socket socket = new Socket(ip, port);
                dataInputStream = new DataInputStream(socket.getInputStream());
                String input = dataInputStream.readUTF();
                System.out.println(input);

                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in);
                String msgOut = scanner.nextLine();

                System.out.println("<<< " + msgOut);

                dataOutputStream.writeUTF(msgOut);
                System.out.println(dataInputStream.readUTF());

            }
            catch (IOException e)
            {
                System.out.println("Error al conectar");
            }


        }
    }
}
