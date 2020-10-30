import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Salir implements ComandosServer {

	private ComandosServer siguiente;

	public void establecerSiguiente(ComandosServer siguiente) {
		this.siguiente = siguiente;

	}

	public void procesar(Paquete paquete) {
		if (paquete.getMsj().equals("--Salir")) {
			try {
				paquete.getSalida().writeUTF("Desconexion Finalizada:...");
				paquete.getSalida().writeUTF("--Salir");
				paquete.getSalida().close();
				paquete.getEntrada().close();
				paquete.getCliente().close();
				Servidor.eliminarClienteDeSala(paquete);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
			siguiente.procesar(paquete);
	}
}
