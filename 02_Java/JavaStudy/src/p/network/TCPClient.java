package p.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = null;
		PrintWriter pw = null;
		
		//요청학고자하는 서버 ip : 172.28.192.1
		//port번호 : 3000

		int port = 3000;
		String serverIP = "192.168.20.9";
		
		//1) 서버로 연결요청을 보내자!(연결을 요청하고자하는 서버의 ip와 port를 전달)
		try {
			
			
			Socket socket = new Socket(serverIP, port);
			if(socket != null) {
				System.out.println("서버와 연결 성공");
				
				//입력용 스트림(클라이언트로부터 전달된 값을 한줄단위로 읽어드리기 위한 스트림)
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				//출력용스트림
				pw = new PrintWriter(socket.getOutputStream());
				
				while(true) {
					System.out.print("서버로 보낼 내용 : ");
					String sendMessage = sc.nextLine();
					pw.println(sendMessage); //클라이언트로 출력
					pw.flush();// 스트림에 있는 데이터 전부 내보내기
					
					String message = br.readLine();
					System.out.println("서버부터 전달받은 메세지 : " + message);
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				pw.close();
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
