
public class GetTiempoConexion implements ComandosServerSala {
	
	private ComandosServerSala siguiente;
	
	@Override
	public void establecerSiguiente(ComandosServerSala siguiente) {
		this.siguiente = siguiente;
		
	}

	@Override
	public void procesar(Paquete paquete) {
		// TODO Auto-generated method stub
		
	}

}
