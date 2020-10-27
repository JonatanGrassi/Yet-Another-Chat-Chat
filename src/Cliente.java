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
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		System.out.print("Quiere comenzar a chatear: ");
		String msj = entradaTeclado.nextLine();
		HiloEscuchar hiloEscucha= new HiloEscuchar(entrada);
		hiloEscucha.start();
		while (!msj.equals("salir")) {
			//System.out.print("Escriba Mensaje: ");
			salida.writeUTF(msj = entradaTeclado.nextLine());
			//if (!msj.equals("salir"))	
		}
		try {
			hiloEscucha.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		entradaTeclado.close();
		salida.close();
		entrada.close();
		socket.close();

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
