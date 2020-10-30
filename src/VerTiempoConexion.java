import java.io.IOException;

public class VerTiempoConexion implements ComandosServerSala {
	
	private ComandosServerSala siguiente;
	
	@Override
	public void establecerSiguiente(ComandosServerSala siguiente) {
		this.siguiente = siguiente;
		
	}

	@Override
	public void procesar(Paquete paquete) {
		if (paquete.getMsj().equals("--verTiempoConexion")) {
			try {
				paquete.getSalida().writeUTF("Desconexion Finalizada:...");
				paquete.getSalida().writeUTF("--Salir");
				paquete.getSalida().close();
				paquete.getEntrada().close();
				paquete.getCliente().close();
				if (!paquete.getSala().equals("--"))
					Servidor.eliminarClienteDeSala(paquete);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			siguiente.procesar(paquete);	
	}

}
