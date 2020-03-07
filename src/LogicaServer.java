public class LogicaServer
{
    int retNumber(int a)
    {
        a = a * 3;
        return a;
    }

    String welcomeMsg()
    {
        String welcome = "Conexion exitosa";
        return welcome;
    }

    boolean checkOnStringNumber(String mensaje)
    {
        boolean resultado = false;
        try

        {
            Integer.parseInt(mensaje);
            resultado = true;
            return resultado;
        }
        catch (
                NumberFormatException e)

        {
            resultado = false;
            return resultado;
        }

    }
}
