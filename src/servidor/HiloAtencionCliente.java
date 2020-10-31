package servidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.ArrayList;
import java.util.Map;

import comandos.ChatPrivado;
import comandos.ComandosServer;
import comandos.CrearSala;
import comandos.Default;
import comandos.EnviarMsjAllSala;
import comandos.IngresarSala;
import comandos.Salir;
import comandos.SalirDeSala;
import comandos.VerTiempoConexion;
import comandos.VolverLobby;

public class HiloAtencionCliente extends Thread {

	private Socket cliente;
	private DataInputStream entrada;
	private Date inicioConexion;
	private DataOutputStream salida;
	private ComandosServer comanSer;
	
	private final String opcionesSala = "Ingrese Comando: " + "\n" + 
										"1)-Salir"     + "\n" + 
										"2)-CrearSala" + "\n" + 
								        "3)-IngresarAsala";
	
	private final String opcionesComandos = "Ingrese Comando: " + "\n" + 
											"5)--ChatGeneral" + "\n" + 
											"6)--ChatPrivado" + "\n" + 
											"7)--verTiempoDeConexion" + "\n" +
											"8)--volverAllobby" + "\n" + 
											"1)--Salir";
	
	public HiloAtencionCliente(Socket socket) {
		this.cliente = socket;
		this.inicioConexion = new Date();
		try {
			this.entrada = new DataInputStream(cliente.getInputStream());
			this.salida = new DataOutputStream(cliente.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.comanSer = new Salir();
		ComandosServer crearSala = new CrearSala();
		ComandosServer ingresoSala = new IngresarSala();
		ComandosServer chatGeneral = new EnviarMsjAllSala();
		ComandosServer chatPrivado = new ChatPrivado();
		ComandosServer chatComDefault = new Default();
		ComandosServer chatVerTiempoConexion = new VerTiempoConexion();
		ComandosServer volverAllobby = new VolverLobby();
		ComandosServer salirDeSala = new SalirDeSala();

		comanSer.establecerSiguiente(crearSala);
		crearSala.establecerSiguiente(ingresoSala);
		ingresoSala.establecerSiguiente(chatGeneral);
		chatGeneral.establecerSiguiente(chatPrivado);
		chatPrivado.establecerSiguiente(chatVerTiempoConexion);
		chatVerTiempoConexion.establecerSiguiente(volverAllobby);
		volverAllobby.establecerSiguiente(salirDeSala);
		salirDeSala.establecerSiguiente(chatComDefault);
	}

	@Override
	public void run() {
		try {
			String msj, resultComando;
			salida.writeUTF("Ingresando su nombre de usuario: ");
			String nick = entrada.readUTF();
			Paquete paquete = new Paquete(inicioConexion, cliente, nick, entrada, salida);
			do
			{
			salida.writeUTF("Lobby" + "\n");
			for (Map.Entry<String, ArrayList<Paquete>> entry : Servidor.getSalas().entrySet()) {
				salida.writeUTF("Nombre Sala: " + entry.getKey() + "Usuarios conetados:" + entry.getValue().size());
			}

			do {
				salida.writeUTF(opcionesSala);
				if(paquete.cantidadSalas() >= 1)
					salida.writeUTF("4)-Salir de sala");
				msj = entrada.readUTF();
			} while ((resultComando = comanSer.procesar(paquete, msj)).equals("y"));

			if (!resultComando.equals("Salir")) {
				do {
					String sala;
					if (paquete.cantidadSalas() > 1) {
						salida.writeUTF("\n" + "--ElegirSalaChat");
						for (String salas : paquete.getSala())
							salida.writeUTF("--" + salas);
						sala = entrada.readUTF();
						paquete.setSalaActiva(sala);
						salida.writeUTF("!Listo para chater");
					} else
						sala = paquete.getSala().get(0);
					paquete.setSalaActiva(sala);

					salida.writeUTF(opcionesComandos);
					msj = entrada.readUTF();
				} while (!(resultComando=comanSer.procesar(paquete, msj)).equals("--VolverAlLobby"));
			}
			}while(!resultComando.equals("Salir"));

		} catch (IOException ex) {
			ex.getStackTrace();
		}

	}
}
