package edu.ucam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {

	private Socket socket;

	public ClientThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			
			while(true){
				System.out.println("Recibido de otro cliente: " + br.readLine());	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
