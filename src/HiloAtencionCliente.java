import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class HiloAtencionCliente extends Thread {

	private Socket cliente;
	// private ArrayList<Socket> listaClientes = new ArrayList<>();
	private int id;

	public HiloAtencionCliente(Socket socket, int id) {
		this.cliente = socket;
		this.id = id;

	}

//	public void agregarClientes(Socket cliente)
//	{
//		listaClientes.add(cliente);
//	}

	@Override
	public void run() {
		try {
			// DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			String msj;
			// String msjRecibido;
			// msjRecibido = entrada.readUTF();
			// System.out.println("Recibi: " + msjRecibido);
			// for (Socket client : Servidor.getListaClientes()) {
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
			while (!(msj = entrada.readUTF()).equals("salir")) {
				for (Socket client : Servidor.getListaClientes()) {
					if (!cliente.equals(client)) {
						salida = new DataOutputStream(client.getOutputStream());
						salida.writeUTF(msj);
					}
					// salida.close();
				}

			}
			entrada.close();
			// salida.close();
			cliente.close();

		} catch (IOException ex) {
			ex.getStackTrace();
		}

	}
}
