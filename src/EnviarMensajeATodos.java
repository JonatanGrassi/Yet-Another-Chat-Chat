import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EnviarMensajeATodos implements ComandosServerSala {

	private ComandosServerSala siguiente;

	public void establecerSiguiente(ComandosServerSala siguiente) {
		this.siguiente = siguiente;

	}

	public void procesar(Paquete paquete) {
		if (paquete.getMsj().equals("--all")) {
			DataOutputStream salida;
			try {
				//salida = new DataOutputStream(paquete.getOutputStream());
				for (Socket client : Servidor.getListaClientes()) {
					if (!paquete.getCliente().equals(client) && client.isConnected()) {
						salida = new DataOutputStream(client.getOutputStream());
						salida.writeUTF(paquete.getMsj());
					}
				}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
			siguiente.procesar(paquete);
	}

}
