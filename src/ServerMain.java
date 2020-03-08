import java.util.Observable;
import java.util.Observer;

public class ServerMain implements Observer
{

    public ServerMain()
    {
        Server server = new Server();
        server.addObserver(this);
        Thread thread = new Thread(server);
        thread.start();
    }
    public static void main(String[] args)
    {

    }

    @Override
    public void update(Observable o, Object arg)
    {

    }
}
