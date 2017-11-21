package edu.ucam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{

	private Socket socket;
	private Server server;
	private PrintWriter pw;
	private BufferedReader br;

	public ServerThread(Socket socket, Server server) throws IOException {
		this.socket = socket;
		this.server = server;
		this.pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
		this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
	}
	
	public void run(){
		try {
			String msg = "";
			while(true){
				//Escucha mensaje de cliente
				msg = this.br.readLine();
				
				System.out.println("Mensaje de cliente: " + msg);
				
				//Envía el mensaje al servidor para que lo reenvie a todos los clientes
				this.server.sendMessageToAll(msg);
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessageToClient(String msg) {
		this.pw.println(msg);
		this.pw.flush();
		
	}

}
