
import java.io.IOException;

public class EnviarMsjAllSala implements ComandosServer {

	private ComandosServer siguiente;

	public void establecerSiguiente(ComandosServer siguiente) {
		this.siguiente = siguiente;

	}

	public String procesar(Paquete paquete, String msj) {
		String dejarSala = "dejarSala";
		if (msj.equals("5")) {
			try {
				paquete.getSalida().writeUTF("Para cambiar de sala de chat ingrese [--salir]");
				while (!(msj = paquete.getEntrada().readUTF()).equals("--salir")) {
					for (Paquete paqueteCliente : Servidor.darClientesDeSala(paquete.getSalaActiva())) {
						if (!paqueteCliente.getCliente().equals(paquete.getCliente()) && paqueteCliente.getCliente().isConnected() && !paquete.isEnChatPrivado()) {
							paqueteCliente.getSalida().writeUTF(paquete + msj);
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			return dejarSala;
		} else
			return siguiente.procesar(paquete, msj);
	}

}
