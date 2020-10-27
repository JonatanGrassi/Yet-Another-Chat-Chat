import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloEscuchar extends Thread {
	
	DataInputStream entrada;
	
	public HiloEscuchar(DataInputStream entrada) {
	 this.entrada = entrada;
	}
	
	
	@Override
	public void run()
	{	
		try {
			while(true)
			{
				System.out.println("\nMensaje de cliente2 es:  " + entrada.readUTF());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
