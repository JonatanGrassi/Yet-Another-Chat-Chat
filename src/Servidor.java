import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

	private static ArrayList<Socket> listaClientes = new ArrayList<>();

	public Servidor(int puerto) throws IOException {
		ServerSocket servidor = new ServerSocket(puerto);
		int id = 0;
		System.out.println("Server inicializando...");

		for (int i = 1; i <= 3; i++) {
			Socket cliente = servidor.accept();

			listaClientes.add(cliente);
			new HiloAtencionCliente(cliente,++id).start();

//			DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
//			DataInputStream entrada = new DataInputStream(socket.getInputStream());
//			
//			salida.writeUTF("Sos el cliente Nro: " + i);
//
//			salida.close();
//			socket.close();
		}

		System.out.println("Server Finalizado");
		for (Socket cliente : listaClientes) {
			cliente.close();
		}
		listaClientes.clear();
		servidor.close();
	}

	static public ArrayList<Socket> getListaClientes() {
		return listaClientes;
	}

	static public void sacarCliente(int i) {
		listaClientes.remove(i-1);
	}

	public static void main(String[] args) {

		try {
			new Servidor(20000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
