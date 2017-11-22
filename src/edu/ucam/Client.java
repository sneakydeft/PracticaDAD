package edu.ucam;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import edu.ucam.Server;

public class Client {

	public static void main(String[] args) {
		Client client = new Client();
		client.execute();

	}
//comennt2
	private void execute() {
		try {
			Socket socket = new Socket("127.0.0.1", Server.PORT);
			
			ClientThread clientThread = new ClientThread(socket);
			clientThread.start();
			
			Scanner keyboard = new Scanner(System.in);
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String msg = "";
			
			while(true){
				msg = keyboard.nextLine();
				System.out.println("Linea teclado: " + msg);
				pw.println(msg);
				pw.flush();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
