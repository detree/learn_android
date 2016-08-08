/*****************************************************************************
*  Copyright (c) 2004-2008, 2013 Digi International Inc., All Rights Reserved
*
*  This software contains proprietary and confidential information of Digi
*  International Inc.  By accepting transfer of this copy, Recipient agrees
*  to retain this software in confidence, to prevent disclosure to others,
*  and to make no use of this software other than that for which it was
*  delivered.  This is an unpublished copyrighted work of Digi International
*  Inc.  Except as permitted by federal law, 17 USC 117, copying is strictly
*  prohibited.
*
*  Restricted Rights Legend
*
*  Use, duplication, or disclosure by the Government is subject to
*  restrictions set forth in sub-paragraph (c)(1)(ii) of The Rights in
*  Technical Data and Computer Software clause at DFARS 252.227-7031 or
*  subparagraphs (c)(1) and (2) of the Commercial Computer Software -
*  Restricted Rights at 48 CFR 52.227-19, as applicable.
*
*  Digi International Inc. 11001 Bren Road East, Minnetonka, MN 55343
*
*****************************************************************************/
package test.chat.serv;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ChatServerActivity extends Activity {
    private static final String host = null;
	private int port;
	String str=null;
	/** Called when the activity is first created. */
	TextView txt5,txt1;
	byte[] send_data = new byte[1024];
	byte[] receiveData = new byte[1024];
	String modifiedSentence;
	Button bt1,bt2,bt3,bt4;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        txt1   = (TextView)findViewById(R.id.textView1); 
        txt5   = (TextView)findViewById(R.id.textView5); 
        
        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);
        //textIn.setText("oncreate");
       
			
	
	
        
        bt1.setOnClickListener(new View.OnClickListener(){             
        	public void onClick(View v) {                 
        		// Perform action on click 
        		//textIn.setText("test"); 
        		//txt2.setText("text2");
        		//task.execute(null);
        		str="temp";
        	 	 try {
					client();
					//txt1.setText(modifiedSentence);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			  }  
        	}  
        	
         }); 
        
        bt2.setOnClickListener(new View.OnClickListener(){             
        	public void onClick(View v) {                 
        		// Perform action on click 
        		//textIn.setText("test"); 
        		//txt2.setText("text2");
        		//task.execute(null);
              
        	 	 str="test";
        	 	try {
					client();
					//txt1.setText(modifiedSentence);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}        	 	 
        	 	 
        	}  
         }); 
        
        bt3.setOnClickListener(new View.OnClickListener(){             
        	public void onClick(View v) {                 
        		// Perform action on click 
        		//textIn.setText("test"); 
        		//txt2.setText("text2");
        		//task.execute(null);
               
        	 	str="humi";
        	 	try {
					client();
					//txt1.setText(modifiedSentence);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}        	 	 
        	 	 
        	}  
        	
         }); 
        
        bt4.setOnClickListener(new View.OnClickListener(){             
        	public void onClick(View v) {                 
        		// Perform action on click 
        		//textIn.setText("test"); 
        		//txt2.setText("text2");
        		//task.execute(null);
               	txt1.setText("null");
        		txt5.setText("null");
        		
        	}  
        	
         }); 
        
        
        
      
        
   		
        
    }
   
    
    public void client() throws IOException{
    
    	   	
    	 DatagramSocket client_socket = new DatagramSocket(2362);
         InetAddress IPAddress =  InetAddress.getByName("192.168.1.11");
  	          
        //while (true)
       // {
        	send_data = str.getBytes();
        	//System.out.println("Type Something (q or Q to quit): ");
        	             	
            DatagramPacket send_packet = new DatagramPacket(send_data,str.length(), IPAddress, 9876);
            client_socket.send(send_packet);                      
          //chandra 
    		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    		client_socket.receive(receivePacket);
    		modifiedSentence = new String(receivePacket.getData());
    		//System.out.println("FROM SERVER:" + modifiedSentence);
    		if(modifiedSentence.charAt(2)=='%')
    		 txt5.setText(modifiedSentence.substring(0, 3)); 
    		else
    			txt1.setText(modifiedSentence);
    		modifiedSentence=null;
    		client_socket.close();
    	 		  
         // }
       
        }       
      
}
    
