package develop.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class BaiduServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println(InetAddress.getByName("www.baidu.com"));
		Socket s = new Socket(InetAddress.getByName("www.baidu.com"), 80);
		if (s.isConnected()) {
			System.out.println(s.getInetAddress());
			System.out.println("客户端已经连上");
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				out.println("GET / HTTP/1.1");
				out.println("Host:www.baidu.com:80");
				out.println("\r\n");

				String line = "";

				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				br.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
