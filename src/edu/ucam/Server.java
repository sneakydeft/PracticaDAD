package edu.ucam;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	public static final int PORT = 2017;
	
	private ArrayList<ServerThread> threads = new ArrayList<ServerThread>();
	
	public void execute(){
		try {
			
			ServerSocket ss = new ServerSocket(Server.PORT);
			Socket socket;
			
			while(true){
				
				try{
					//Escuchar nuevas peticiones
					socket = ss.accept();
					
					//Creamos y lanzamos hilo
					ServerThread serverThread = new ServerThread(socket, this);				
					serverThread.start();
					
					//Añadimos el hilo al listado de hilos
					this.threads.add(serverThread);
					
				}catch (IOException ioException){
					System.out.println("Excepción: " + ioException.getMessage());
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.execute();

	}

	/*
	 * Enviar un mensaje a todos los clientes
	 */
	public void sendMessageToAll(String msg) {
		
		//Recorro el array con todos los hilos para enviar el mensaje
		for(ServerThread serverThread : this.threads){
			System.out.println("\tMensaje a cliente ["+ serverThread.getId()+"]: " + msg);
			serverThread.sendMessageToClient(msg);
		}
		
	}

}
