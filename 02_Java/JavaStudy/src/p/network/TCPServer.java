package p.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
	/*
	 * TCP
	 * - 서버, 클라이언트 1:1 소켓통신
	 * - 데이터를 교환하기에 앞서 서버, 클라이언트 연결이 되어있어야한다.(서버가 먼저 실행되어있어야 클라이언트의 요청을 받을 수 있음)
	 * 
	 * socket
	 * - 프로세스간에 통신을 담당
	 * - input/outputStream을 가지고 있음(해당 스트림을 통해 데이터 입출력이 가능)
	 * 
	 * ServerSocket
	 * - 포트와 연결되어 외부의 연결요청을 기다림 -> 요청이 들어오면 수락해줌
	 * 수락 -> 통신할 수 있는 socket생성
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BufferedReader br  = null;
		PrintWriter pw = null;
		//1) 포트번호 지정
		int port = 3000;
		
		try {
			//2) ServerSocker객체생성시 port와 결합(bind)
			ServerSocket server = new ServerSocket(port);
			
			System.out.println("클라이언트 요청을 기다리고 있습니다.");
			Socket socket = server.accept();
			System.out.println(socket.getInetAddress().getHostAddress() + "가 연결 요청함...");
			
			//입력용 스트림(클라이언트로부터 전달된 값을 한줄단위로 읽어드리기 위한 스트림)
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//출력용스트림
			pw = new PrintWriter(socket.getOutputStream());
			
			while(true) {
				String message = br.readLine();
				System.out.println("클라이언트로부터 전달받은 메세지 : " + message);
				
				System.out.print("클라이언트로 보낼 내용 : ");
				String sendMessage = sc.nextLine();
				
				pw.println(sendMessage); //클라이언트로 출력
				pw.flush();// 스트림에 있는 데이터 전부 내보내기
			}
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
