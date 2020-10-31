import java.io.DataInputStream;
import java.io.IOException;

public class HiloEscuchar extends Thread {
	
	DataInputStream entrada;
	
	public HiloEscuchar(DataInputStream entrada) {
	 this.entrada = entrada;
	}
	
	@Override
	public void run()
	{	
		try {
			String msj;
			while(!(msj = entrada.readUTF()).equals("--Salir"))
			{	
				System.out.println(msj);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
