package q.thread.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
 
public class ServerReceive extends Thread{
	private Socket socket;

	public ServerReceive(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			while(true) {
				String message = br.readLine();
				System.out.println("\n클라이언트로부터 전달받은 메세지 : " + message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
