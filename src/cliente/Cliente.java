package cliente;
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
//		String msj = entradaTeclado.nextLine();
//		HiloEscuchar hiloEscucha= new HiloEscuchar(entrada);
//		hiloEscucha.start();
//		while (!msj.equals("salir")) {
//			//System.out.print("Escriba Mensaje: ");
//			salida.writeUTF(msj = entradaTeclado.nextLine());
//			//if (!msj.equals("salir"))	
//		}
//		try {
//			hiloEscucha.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		Thread enviarMensaje = new Thread(new Runnable()  
        { 
            @Override
            public void run() { 
                while (true) { 
  
                    // read the message to deliver. 
                    String msg = entradaTeclado.nextLine(); 
                      
                    try { 
                        // write on the output stream 
                        salida.writeUTF(msg); 
                    } catch (IOException e) { 
                        e.printStackTrace(); 
                    } 
                } 
            } 
        }); 
          
        Thread leerMesanje = new Thread(new Runnable()  
        { 
            @Override
            public void run() { 
  
                while (true) { 
                    try { 
                        // read the message sent to this client 
                        String msg = entrada.readUTF(); 
                        System.out.println(msg); 
                    } catch (IOException e) { 
  
                        e.printStackTrace(); 
                    } 
                } 
            } 
        });
        
        enviarMensaje.start(); 
        leerMesanje.start();
        
		entradaTeclado.close();
		salida.close();
		entrada.close();
		socket.close();

	}

	
}
