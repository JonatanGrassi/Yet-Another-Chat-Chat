package comandos;
import java.io.IOException;

import servidor.Paquete;

public class Default implements ComandosServer {

	@Override
	public void establecerSiguiente(ComandosServer siguiente) {
		
	}

	@Override
	public String procesar(Paquete paquete, String msj) {
		try {
			paquete.getSalida().writeUTF("Comando invalido ingrese nuevamente");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "y";
	}

}
