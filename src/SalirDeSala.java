import java.io.IOException;

public class SalirDeSala implements ComandosServer {

	private ComandosServer siguiente;

	@Override
	public void establecerSiguiente(ComandosServer siguiente) {
		this.siguiente = siguiente;
	}

	@Override
	public String procesar(Paquete paquete, String msj) {
		String rta = "n";
		boolean existeSala = false;
		if (msj.equals("4")) {
			try {
				paquete.getSalida().writeUTF("Ingrese la sala de la cual quiere salir");
				for (String salas : paquete.getSala()) {
					paquete.getSalida().writeUTF("---" + salas);
				}

				String sala = paquete.getEntrada().readUTF();
				for (String salas : paquete.getSala()) {
						if (salas.equals(sala))
							existeSala = true;
				}
				if(existeSala)
				{
				paquete.dejarSala(sala);
				Servidor.eliminarClienteDeSala(paquete, sala);
				paquete.getSalida().writeUTF("desea crear/ingresar o salir a una sala[y/n]");
				}
				else
					paquete.getSalida().writeUTF("Sala inexistente");
				rta = paquete.getEntrada().readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return rta;
		} else
			return siguiente.procesar(paquete, msj);
	}

}
