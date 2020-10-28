package servidor;

import java.io.IOException;

public class ServerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new Servidor(20000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
