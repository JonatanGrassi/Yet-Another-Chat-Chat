
public interface ComandosServer {
	public void establecerSiguiente(ComandosServer siguiente);

	public String procesar(Paquete paquete,String msj);
}
