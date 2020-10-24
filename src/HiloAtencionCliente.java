import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class HiloAtencionCliente extends Thread {

	private Socket cliente;
	//private ArrayList<Socket> listaClientes = new ArrayList<>();
	private int id ;
	
	public HiloAtencionCliente(Socket socket,int id) {
		this.cliente = socket;
		this.id = id;
		
	}
	
//	public void agregarClientes(Socket cliente)
//	{
//		listaClientes.add(cliente);
//	}
	
	@Override
	public void run() {
		try
		{
			//DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			String msjRecibido;
			msjRecibido = entrada.readUTF();
			System.out.println("Recibi: " + msjRecibido);
			for (Socket client : Servidor.getListaClientes()) {
				DataOutputStream salida = new DataOutputStream(client.getOutputStream());
				salida.writeUTF("Como andas");
				salida.close();
				//cliente.close();	
			}
			entrada.close();
			cliente.close();
			Servidor.sacarCliente(id);
			//salida.writeUTF(msjRecibido);	
		}catch(IOException ex)
		{
			ex.getStackTrace();
		}
		
	}
}
