package ttest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws Exception{
	
			Socket socket = new Socket("192.168.0.78", 11112);
			System.out.println("클라이언트 ");
			
			OutputStream os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);

			ClassInfo ci = new ClassInfo();
			ci.setId("0000000");
			ci.setName("최대현");
			ci.setKor(100);
			ci.setEng(100);
			ci.setMath(100);
			
			oos.writeObject(ci);
			oos.flush();	
			
			oos.close();
			os.close();
			socket.close();
		
	}

}
