package servidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class AtencionCliente extends Thread {

	private String name; 
    final DataInputStream dis; 
    final DataOutputStream dos; 
    Socket socket; 
    boolean isloggedin; 

	public AtencionCliente(Socket socket, String name, DataInputStream dis, DataOutputStream dos) {
		this.socket = socket;
		this.name = name;
		this.dos = dos;
		this.dis = dis;
		this.isloggedin=true; 
	}

	@Override
	public void run() {
		String received; 
        while (true)  
        { 
            try
            { 
                // receive the string 
                received = dis.readUTF(); 
                  
                System.out.println(received); 
                  
                if(received.equals("logout")){ 
                    this.isloggedin=false; 
                    this.socket.close(); 
                    break; 
                } 
                  
                // break the string into message and recipient part 
                StringTokenizer st = new StringTokenizer(received, "#"); 
                String MsgToSend = st.nextToken(); 
                String recipient = st.nextToken(); 
  
                for (AtencionCliente mc : Servidor.getListaClientes())  
                { 
                    if (mc.name.equals(recipient) && mc.isloggedin==true)  
                    { 
                        mc.dos.writeUTF(this.name+" : "+MsgToSend); 
                        break; 
                    } 
                } 
            } catch (IOException e) { 
                  
                e.printStackTrace(); 
            }              
        } 
        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    }
}
