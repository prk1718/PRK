package application;

import java.io.*;
import java.net.*;

import javafx.scene.control.TextArea;

public class Server {

	public ServerSocket myServerSocket;
	public Socket skt;
	public BufferedReader Input;
	public PrintStream Output;
	public String textOdb="";
	
	public Server(TextArea text){
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				  try{
			            myServerSocket = new ServerSocket(3344); 

			            skt = myServerSocket.accept();
			           
			            Input = new BufferedReader(new InputStreamReader(skt.getInputStream())); 
			            Output = new PrintStream(skt.getOutputStream());                          
			            
			            while(true)
			             {
			            	
			            	try
	            	 		{
	            	 		   String buf=Input.readLine();

						        if(buf !=null)
						        {
			                    	 textOdb = buf;
			                    	 text.setText(textOdb+"\r\n"+text.getText());
						        }
						        
	            	 		}catch(Exception e)
	            	 		{
	            	 			
	            	 		}
			                
			             }
			           

			        }
			        catch (Exception ex)
				    {			    
			        	
			        }	
			}
			
			
		}).start();
		
      
	}
	
	public void close()
	{
		   try{
			   skt.close();
              
	        }
	        catch (Exception ex){
	         
	        }
		   
		   try{
			   myServerSocket.close();
              
	        }
	        catch (Exception ex){
	         
	        }
		   
		   try{
			   Input.close();
              
	        }
	        catch (Exception ex){
	         
	        }
		   
		   try{
			   Output.close();
              
	        }
	        catch (Exception ex){
	         
	        }
		
	}
	
	public void send(String info)
	{
        try {
        	
        	
	        Output.println(info);

			
		} catch (Exception e) {
			

		}                    


		
	}

}
