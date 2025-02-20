package q.thread.chat;

import java.io.IOException;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) {
		//소프트웨가 실행중인 본인의 컴퓨터의 ip는 
		// 127.0.0.1 or localhost라는 대명사로 작성 가능
		try {
			Socket socket = new Socket("localhost", 3000);
			
			ClientReceive cr = new ClientReceive(socket);
			cr.start(); //계속해서 값을 받아와서 출력하는 동작을 쓰레드로 실행
			
			ClientSend cs = new ClientSend(socket);  
			cs.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
