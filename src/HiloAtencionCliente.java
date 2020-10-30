import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class HiloAtencionCliente extends Thread {

	private Socket cliente;
	// private ArrayList<Socket> listaClientes = new ArrayList<>();
	private int id;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private ComandosServer comanSer;
	private ComandosServerSala comanSerSal;
	public HiloAtencionCliente(Socket socket, int id) {
		this.cliente = socket;
		this.id = id;
		try {
			this.entrada = new DataInputStream (cliente.getInputStream());
			this.salida = new DataOutputStream(cliente.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.comanSer = new Salir();
		ComandosServer crearSala = new CrearSala();
		comanSer.establecerSiguiente(crearSala);
		
		this.comanSerSal = new EnviarMensajeATodos();
		ComandosServerSala timeConexion = new GetTiempoConexion();
		comanSerSal.establecerSiguiente(timeConexion);
		
	}

//	public void agregarClientes(Socket cliente)
//	{
//		listaClientes.add(cliente);
//	}

	@Override
	public void run() {
		try {	
				salida.writeUTF("Ingresando al lobby" + "\n");
				for (Map.Entry<String, ArrayList<Paquete>> entry : Servidor.getSalas().entrySet()) {
				    salida.writeUTF("Nombre Sala: " + entry.getKey() + "Usuarios conetados:" + entry.getValue().size());
				}
				salida.writeUTF("Ingrese Comando: "
				+ "\n" + "--Salir"
				+ "\n" + "--CrearSala"
				+ "\n" + "--IngresarAsala");
				
				String msj = entrada.readUTF();
				Paquete paquete = new Paquete(cliente, msj, entrada, salida);
				comanSer.procesar(paquete);
				Thread.sleep(5000000);
//			while (!(msj = entrada.readUTF()).equals("--salir")) {
//				for (Socket client : Servidor.getListaClientes()) {
//					if (!cliente.equals(client)) {
//						DataOutputStream salReceptor = new DataOutputStream(client.getOutputStream());
//						salReceptor.writeUTF(msj);
//					}
//	
//				}
//			}
			//salida = new DataOutputStream(cliente.getOutputStream());
//			salida.writeUTF(msj);
//			entrada.close();
//			salida.close();
//			cliente.close();

		} catch (IOException ex) {
			ex.getStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
