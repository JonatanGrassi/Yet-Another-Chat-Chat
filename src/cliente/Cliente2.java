package cliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Cliente2 {

	public Cliente2(String ip, int puerto) throws UnknownHostException, IOException {
		Socket socket = new Socket(ip, puerto);
		Scanner entradaTeclado = new Scanner(System.in);
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
		DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
		System.out.print("Quiere comenzar a chatear: ");
		String msj = entradaTeclado.nextLine();
		HiloEscuchar hiloEscucha= new HiloEscuchar(entrada);
		hiloEscucha.start();
		while(!msj.equals("salir"))
		{
			//System.out.print("Escriba Mensaje: ");
			salida.writeUTF(msj = entradaTeclado.nextLine());	
		}
		try {
			hiloEscucha.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		entradaTeclado.close();
		entrada.close();
		socket.close();

	}

	public static void main(String[] args) {
		try {
			new Cliente2("localhost", 20000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

