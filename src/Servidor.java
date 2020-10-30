import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Servidor {

	private static ArrayList<Socket> listaClientes = new ArrayList<>();
	private static Map<String, ArrayList<Paquete>> salas = new HashMap<String, ArrayList<Paquete>>();

	public Servidor(int puerto) throws IOException {
		ServerSocket servidor = new ServerSocket(puerto);
		int id = 0;
		System.out.println("Server inicializando...");
		servidor.setSoTimeout(8000000);
		for (int i = 1; i <= 200; i++) {
			Socket cliente = servidor.accept();

			listaClientes.add(cliente);
			HiloAtencionCliente hc= new HiloAtencionCliente(cliente, ++id);
			hc.start();
			// new HiloAtencionCliente(cliente,++id).start();

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
	
	static public void eliminarClienteDeSala(Paquete paqueteClient)
	{	
		ArrayList<Paquete> listaClientSala = salas.get(paqueteClient.getSala());
		for (int i = 0; i < listaClientSala.size(); i++) {
			if(listaClientSala.get(i).getCliente().equals(paqueteClient.getCliente()))
				listaClientSala.remove(i);
		salas.put(paqueteClient.getSala(), listaClientSala);
		}
	}
	
	public static Map<String, ArrayList<Paquete>> getSalas() {
		return salas;
	}


	public static void agregarClienteSala(Paquete paqueteClient) {
		ArrayList<Paquete> listaPaquetesSala = new ArrayList<>();
		
		if (salas.containsKey(paqueteClient.getSala()))
			listaPaquetesSala = salas.get(paqueteClient.getSala());

		listaPaquetesSala.add(paqueteClient);
		salas.put(paqueteClient.getSala(), listaPaquetesSala);

	}
	
	static ArrayList<Paquete>  darClientesDeSala(String sala)
	{
		return salas.get(sala);
	}

	static public void sacarCliente(int i) {
		listaClientes.remove(i - 1);
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
