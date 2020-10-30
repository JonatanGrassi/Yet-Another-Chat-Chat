import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class IngresarSala implements ComandosServer {

	private ComandosServer siguiente;

	public void establecerSiguiente(ComandosServer siguiente) {
		this.siguiente = siguiente;

	}

	public void procesar(Paquete paquete) {
		String msj;
		if (paquete.getMsj().equals("--IngresarAsala")) {
			try {
				paquete.getSalida().writeUTF("Ingrese nombre de la sala: ");
				msj = paquete.getEntrada().readUTF();
				paquete.setSala(msj);
				Servidor.agregarClienteSala(paquete);
				
				paquete.getSalida().writeUTF("\n" + "Usted esta en la sala: " + msj);
//				paquete.getSalida().writeUTF("\n" + "Usted esta en la sala:" + msj);
//				
//				paquete.getSalida().writeUTF("Ingrese Comando: "
//						+ "\n" + "--Salir"
//						+ "\n" + "--ChatGlobal"
//						+ "\n" + "--ChatPrivado");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			siguiente.procesar(paquete);
	}
}
