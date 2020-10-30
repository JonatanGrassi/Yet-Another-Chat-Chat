import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Paquete {
	private Socket cliente;
	private Calendar inicioConexion = new GregorianCalendar() ;
	private String msj;
	private String sala;
	private DataInputStream entrada;
	private DataOutputStream salida;
	
	
	public Paquete(Socket cliente, String msj,DataInputStream entrada,DataOutputStream salida) {
		this.cliente = cliente;
		this.msj = msj;
		this.entrada = entrada;
		this.salida = salida;
		this.sala = "";
	}
	
//	public Calendar geTiempoConexion() {
//		inicioConexion.
//	}


	public void setInicioConexion(Calendar inicioConexion) {
		this.inicioConexion = inicioConexion;
	}

	public String getMsj() {
		return msj;
	}

	public Socket getCliente() {
		return cliente;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public DataInputStream getEntrada() {
		return entrada;
	}

	public void setEntrada(DataInputStream entrada) {
		this.entrada = entrada;
	}

	public DataOutputStream getSalida() {
		return salida;
	}

	public void setSalida(DataOutputStream salida) {
		this.salida = salida;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}
	
	
	
}
