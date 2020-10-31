import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Servidor {

	private static Map<String, ArrayList<Paquete>> salas = new HashMap<String, ArrayList<Paquete>>();

	public Servidor(int puerto) throws IOException {
		ServerSocket servidor = new ServerSocket(puerto);
		System.out.println("Server inicializando...");
		for (int i = 1; i <= 200; i++) {
			Socket cliente = servidor.accept();

			HiloAtencionCliente hc = new HiloAtencionCliente(cliente);
			hc.start();
		}
		System.out.println("Server Finalizado");
		servidor.close();
	}

	static public void eliminarClienteDeSalas(Paquete paqueteClient) {
		for (String salaSal : paqueteClient.getSala()) {
			ArrayList<Paquete> listaClientSala = salas.get(salaSal);
			for (Iterator<Paquete> iterator = listaClientSala.iterator(); iterator.hasNext();) {
				Paquete paquete = (Paquete) iterator.next();
				if (paqueteClient.getCliente().equals(paquete.getCliente()))
					iterator.remove();
			}
		}
	}

	public static boolean agregarClienteSala(Paquete paqueteClient, String salaAingresar) {
		boolean existiaSala = false;
		if (salas.containsKey(salaAingresar)) {
			salas.get(salaAingresar).add(paqueteClient);
			existiaSala = true;
		}
		return existiaSala;
	}

	static public void eliminarClienteDeSala(Paquete paqueteClient, String Sala) {
		for (Iterator<Paquete> iterator = salas.get(Sala).iterator(); iterator.hasNext();) {
			Paquete paquete = (Paquete) iterator.next();
			if (paqueteClient.getCliente().equals(paquete.getCliente()))
				iterator.remove();
		}
	}

	public static void crearSala(Paquete paqueteClient, String salaAcrear) {
		ArrayList<Paquete> client = new ArrayList<>();
		client.add(paqueteClient);
		if (!salas.containsKey(salaAcrear))
			salas.put(salaAcrear, client);
		else
			agregarClienteSala(paqueteClient, salaAcrear);
	}

	public static Map<String, ArrayList<Paquete>> getSalas() {
		return salas;
	}

	static ArrayList<Paquete> darClientesDeSala(String sala) {
		return salas.get(sala);
	}

	public static void main(String[] args) {

		try {
			new Servidor(20000);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
