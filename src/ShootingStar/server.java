package ShootingStar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class server {
	
	
	
	
	
	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		Socket socket = null;
		Socket socket2 = null;
		BufferedReader in = null;
		PrintWriter out = null;
		BufferedReader in2 = null;
		PrintWriter out2 = null;

		try {
			serverSocket = new ServerSocket(8000);
			
			System.out.println("Server실행, Client연결대기중...");
			
			socket = serverSocket.accept();			
			System.out.println("Client1 연결됨.");
			
			socket2 = serverSocket.accept();
			System.out.println("Client2 연결됨.");
			
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
			out2 = new PrintWriter(socket2.getOutputStream());
			String inputMessage,inputMessage2;
			

			
			while(true) {
				
				inputMessage = in.readLine();	
				inputMessage2 = in2.readLine();	
				out.println(inputMessage2);
				out2.println(inputMessage);
				out.flush();
				out2.flush();
		
			}


			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				socket.close();			
				serverSocket.close();		
				System.out.println("연결종료");
			} catch (IOException e) {
				System.out.println("에러발생");
			}
		}
	}
	




}
