
public interface ComandosServer {
	public void establecerSiguiente(ComandosServer siguiente);

	public void procesar(Paquete paquete);
}
