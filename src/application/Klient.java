package application;

import java.io.*;
import java.net.*;

import javafx.scene.control.TextArea;

public class Klient {

	public Socket skt;
	public BufferedReader Input;
	public PrintStream Output;
	public String textOdb="";

	
    public Klient(TextArea text)
    {
    	new Thread(new Runnable() {

			@Override
			public void run() {
				
				 try{
				        skt = new Socket("127.0.0.1",3344);

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
