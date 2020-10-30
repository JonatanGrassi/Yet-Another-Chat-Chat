import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Paquete {
	private static SimpleDateFormat formatFecha = new SimpleDateFormat(" [dd/MM/yyyy HH:mm:ss] ");
	private Socket cliente;
	private Date inicioConexion;
	private String msj;
	private String sala;
	private String nick;
	private DataInputStream entrada;
	private DataOutputStream salida;
	
	
	public Paquete(Date inicioConexion,Socket cliente,String nick,String msj,DataInputStream entrada,DataOutputStream salida) {
		this.inicioConexion=inicioConexion;
		this.cliente = cliente;
		this.msj = msj;
		this.nick = nick;
		this.entrada = entrada;
		this.salida = salida;
		this.sala = "--";
	}
	
	public Date getInicioConexion() {
		return inicioConexion;
	}
	
	public String getTiempoConexion()
	{
        Date tiempoActual = new Date();
        long diffInMillies = Math.abs(inicioConexion.getTime() - tiempoActual.getTime());
        long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        int dias = (int)diff/86400;
        int horas = (int)(diff%86400)/3600;
        int minutos = (int)diff%3600/60;
        int segundos = (int)diff%60;
        return "Tiempo activo: " + " dias: " + dias + " horas: " + horas + " minutos: " + minutos + " segundos: " + segundos;
	}
	
	@Override
	public String toString() {     
		return "sala: " + sala + formatFecha.format(new Date()) + "Mensaje de " + nick + " : " ;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
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
