import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CrearSala implements ComandosServer {

	private ComandosServer siguiente;

	public void establecerSiguiente(ComandosServer siguiente) {
		this.siguiente = siguiente;

	}

	public void procesar(Paquete paquete) {
		String msj;

		if (paquete.getMsj().equals("--CrearSala")) {
			try {
				paquete.getSalida().writeUTF("Ingrese nombre de la sala: ");
				msj=paquete.getEntrada().readUTF();
				paquete.setSala(msj);
				Servidor.agregarClienteSala(paquete);
				paquete.getSalida().writeUTF("\n" + "Sala creada con exito ");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			siguiente.procesar(paquete);
	}

}
