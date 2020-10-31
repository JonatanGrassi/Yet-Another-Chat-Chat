package cliente;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	
	private String ip;
	private int puerto;
	private BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
	private DataInputStream dis;
	private DataOutputStream dos;
	private Socket socket;
	
	
	public Cliente(String ip, int puerto) throws UnknownHostException, IOException {
		this.puerto = puerto;
		this.ip = ip;
		conectarse();
	}
		
	public void conectarse()
	{
		try {
			socket = new Socket(ip, puerto);
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			HiloEscuchar hiloEscucha = new HiloEscuchar(dis);
			hiloEscucha.start();
			while (hiloEscucha.isAlive()) {
				String msj = keyRead.readLine();
				dos.writeUTF(msj);
				Thread.sleep(200);
			}
			cerrarConexion();
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion()
	{
		try {
			keyRead.close();
			dis.close();
			dos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
