# README #

This project implements a socket connection from an Android phone to the PC via USB. The idea was taken from:

        http://www.florescu.org/archives/2010/10/15/android-usb-connection-to-pc/

To make this work you will first have to setup port forwarding via ADB:

	./adb forward tcp:38300 tcp:38300
	
Then run the app and hit the "connect" button (note that the app will only be accepting connections in the next few seconds). Finally, you run the following code on your computer: 


```
#!java

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Test {

	private Socket socket;
	private PrintWriter out;
	private Scanner sc;

	/**
	 * Initialize connection to the phone
	 *
	 */
	public void initializeConnection(){
		//Create socket connection
		try{
			socket = new Socket("localhost", 38300);
			out = new PrintWriter(socket.getOutputStream(), true);
			//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sc = new Scanner(socket.getInputStream());
		
			// add a shutdown hook to close the socket if system crashes or exists unexpectedly
			Thread closeSocketOnShutdown = new Thread() {
				public void run() {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			
			Runtime.getRuntime().addShutdownHook(closeSocketOnShutdown);
		
		} catch (UnknownHostException e) {
			System.err.println("Socket connection problem (Unknown host)" + e.getStackTrace());
		} catch (IOException e) {
			System.err.println("Could not initialize I/O on socket " + e.getStackTrace());
		}
	}
	
	public static void main(String[] args) {
    
		Test t = new Test();
		t.initializeConnection();
		
		while(t.sc.hasNext()) {
		System.out.println(System.currentTimeMillis() + " / " + t.sc.nextLine());
		}
	}
}
```

