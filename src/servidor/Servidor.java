package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

	private static ArrayList<AtencionCliente> listaClientes = new ArrayList<>();
	private int id = 0;
	private boolean started;

	public Servidor(int puerto) throws IOException {
		ServerSocket servidor = new ServerSocket(puerto);
		
		System.out.println("Server inicializando...");
		started = true;
		Socket socket;
		while(started) {
			socket = servidor.accept();
			
			System.out.println("Llego nuevo cliente");
			
			DataInputStream dis = new DataInputStream(socket.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            AtencionCliente atCliente = new AtencionCliente(socket, "client "+ ++id, dis, dos);            
            listaClientes.add(atCliente);
            
            Thread hiloAtCliente = new Thread(atCliente);
            hiloAtCliente.start();           
            
		}



		System.out.println("Server Finalizado");
		
		listaClientes.clear();
		servidor.close();
	}

	static public ArrayList<AtencionCliente> getListaClientes() {
		return listaClientes;
	}

	static public void sacarCliente(int i) {
		listaClientes.remove(i-1);
	}

}
