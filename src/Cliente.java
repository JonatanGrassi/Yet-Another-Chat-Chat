import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Cliente {

	public Cliente(String ip, int puerto) throws UnknownHostException, IOException {
		Socket socket = new Socket(ip, puerto);
		Scanner entradaTeclado = new Scanner(System.in);
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		
		System.out.print("Escriba Mensaje: ");
		salida.writeUTF(entradaTeclado.nextLine());
		System.out.println(entrada.readUTF());
		
		entrada.close();
		socket.close();
		entradaTeclado.close();

	}

	public static void main(String[] args) {
		try {
			new Cliente("localhost", 20000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
