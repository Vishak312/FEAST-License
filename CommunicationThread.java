package server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

class CommunicationThread extends Thread
{
	static Logger logger = Logger.getLogger(CommunicationThread.class.getName());  
	static private Lock userLock = new ReentrantLock( );
	static FileHandler fh;
	String Syspath=System.getenv("TEMP");
	String WinPath=System.getenv("windir");
	public static int counter=0;
	 int login=1;
	Socket socket;	
	Runtime r=Runtime.getRuntime();

	String fs;
	Process p;
	int flag=0;
	CommunicationThread(Socket s) 
	{
		socket=s;
		start();
	}		
	
	public void run()
	{		
		try
		{	
			
			//String FEASTPath=System.getenv("FEASTDIR");		 		//FEAST Directory path
			//System.out.println("the feast path is "+FEASTPath);
			StringBuilder sb = new StringBuilder();	
			try
			{
				
				//newly adding logger code				

			    try 
			    {  
			        fh = new FileHandler(Syspath+"/CommunicationtThreadlog.log");//LOG file  
			        logger.addHandler(fh);
			        SimpleFormatter formatter = new SimpleFormatter();  
			        fh.setFormatter(formatter);  			         
			    } 
			    catch (SecurityException e) 
			    {  
			        e.printStackTrace();  
			    } 
			    catch (IOException e) 
			    {  
			        e.printStackTrace();  
			    }  
				InetAddress ip = InetAddress.getLocalHost();			
				System.out.println("Current IP address : " + ip.getHostAddress());
				logger.info("the ip address of the server "+ip);
				NetworkInterface network = NetworkInterface.getByInetAddress(ip);	 
				byte[] mac = network.getHardwareAddress(); 
				System.out.print("Current MAC address : " );				
				for (int i = 0; i < mac.length; i++) {
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "." : ""));		
				}
				System.out.println(sb.toString());
			}
			catch (UnknownHostException e) {
	 
				e.printStackTrace();
	 
			} catch (SocketException e)
			{
	 
				e.printStackTrace();
			}
			System.out.println("The mac address of server is " +sb);
			String ip=InetAddress.getLocalHost().getHostAddress();			
			System.out.println("the ip address of the server is :" +ip);
			InputStream source=socket.getInputStream();
			OutputStream des=socket.getOutputStream();
			DataInputStream dis=new DataInputStream(source); 
			PrintStream ps=new PrintStream(des, true);	
			DataOutputStream dos= new DataOutputStream(des);
			OutputStream os = socket.getOutputStream();
			String count=dis.readLine();			
			System.out.println("request: " +count);
			logger.info("the request "+count);
			File setupfile=new File(WinPath+"/setup.txt");
			if(setupfile.exists())
			{
			BufferedReader reader = new BufferedReader(new FileReader(WinPath+"/setup.txt"));
	    	String line1=null;	    	    	
	    	if((line1 = reader.readLine()) != null) 
	    	{

	    		System.out.println("encrypted licence for the user is : " +line1);
	    		 
	    	}
	    	
	    	System.out.println(line1);
	    	String[] key=new String[4];
	    	server svr=new server();//object for class server
	    	String passwordEnc1 = svr.encrypt(line1);
			System.out.println("Encrypted Text : " + passwordEnc1);
			String newenctext = line1.replaceFirst(line1, passwordEnc1);
			System.out.println("the encrypted text is " +newenctext);      
			String passwordDec = svr.decrypt(line1);
			System.out.println(" the decrypted value is : " +passwordDec);
			String licencekey=passwordDec;
		   key=licencekey.split("-");
		   System.out.println("the first key is " + key[0]);
       	   System.out.println("the second key is " +key[1]);
	       System.out.println("the third key is " +key[2]);
	       System.out.println("the  fourth key is "+key[3]);
	       System.out.println("the macaddress is " +key[4]);
	       System.out.println("the final key is " +key[5]);
	       System.out.println("licence key value : " + licencekey);
	       int value=Integer.parseInt(key[5]);
	       String data[];
	       System.out.println(sb);
	       System.out.println(key[4]);
	       if(key[4].contains(sb))
	       {
		    	if(counter<value)
				{
		    		logger.info("sucess same server");
					ps.println("Licence");					
					String requ=dis.readLine();
					System.out.println("the request from client" +requ);
					logger.info("the request for license is "+requ);
					System.out.println("the counter value before getting request " +counter);
					File myFile = new File(WinPath+"/License"+"/software.zip");	
					boolean FileExist=myFile.exists();
					System.out.println("the file exist status is "+FileExist);
					logger.info("exist status " +FileExist);
					if(FileExist==true)
					{
					switch(requ)
					{
						
						case "NEW":
								logger.info("with in new request");
				            	String path=System.getenv("TEMP");
							    String filename=path+"/USERS.txt";						  							   						  
								File file= new File(filename);
								counter=counter+1;							
								System.out.println("IPAddress added");
								System.out.println("counter value is : " +counter);													
								System.out.println("count updated successfully");			
								System.out.println("Sending SUCCESS");
								ps.println("SUCCESS");												
								int len=(int) myFile.length();
								System.out.println("the length of the file is " +len);
								byte[] mybyte = new byte[len];
								FileInputStream fis = new FileInputStream(myFile);
								BufferedInputStream bis = new BufferedInputStream(fis);
								DataInputStream dis_1 = new DataInputStream(bis);   
								dis_1.readFully(mybyte, 0, mybyte.length);				    
								dos.writeUTF(myFile.getName());      
								dos.writeLong(mybyte.length);   
						        dos.write(mybyte, 0, mybyte.length); 							
						        String ipaddress=dis.readLine();
						        System.out.println("the ipaddress of the client is " +ipaddress);
						        System.out.println("the ipaddress is " +ipaddress);						        					 
							    FileWriter fw = null;
						    	PrintWriter pw = null;
						    	try 
						    	{
						    	   userLock.lock();
					    		   fw = new FileWriter(file,true);
					    	       pw = new PrintWriter(fw);
					    	       pw.write(ipaddress+" ");
					    	       pw.close();
					    	       fw.close();
					    	       userLock.unlock();
						    	} 
						    	catch (IOException ex) 
						    	{
					    	    	System.out.println(ex);
					    	    	logger.info(" file write exception "+ex);
						    	}
						    	System.out.println("the file printed successfully with in the new request");
						    	logger.info("the file printed successfully with in the new request");
							    				   
						       String status=dis.readLine();
						       System.out.println("laststatus: " +status);
						       logger.info("laststatus "+status);
						       if(status.equals("LOGOUT"))
						       {														
									
						    	   BufferedReader br = new BufferedReader(new FileReader(file));
							       try 
							       {
							        	System.out.println("reading the file contents from new request");
							            StringBuilder sb_1 = new StringBuilder();
							            userLock.lock();	
							            String line = br.readLine();
	
							            while (line != null) 
							            {
							                sb_1.append(line);
							                sb_1.append(System.lineSeparator());
							                line = br.readLine();
							            }
							            userLock.unlock();
							            String everything = sb_1.toString();
							            everything=everything.trim();
							            System.out.println("the contents are " +everything);
	//new code						   
							            data=everything.split(" ");		
							            System.out.println("the array values are "+data[0]);						         
							            int val=data.length;
							            System.out.println("the length of the array is "+val);
							            String ip_1=dis.readLine();
							            System.out.println("the ip is "+ip_1);
							            logger.info("the client ip is "+ip_1);
							            	try
							            	{
																							
												counter=counter-1;
												System.out.println("the counter value after closing the applictaion with in the new request is :" +counter);											
												logger.info("counter value is "+counter);
												for (int i = 0; i < data.length; i++)
										    	{
										    	    if (data[i].contains(ip_1))
										    	    {
										    	        data[i] ="";									    	        
										    	        System.out.println("the ip address removed successfully");
										    	        try
										    	        {
										    	        	userLock.lock();
										    	            FileWriter fr = new FileWriter(file);
										    	            BufferedWriter br_1 = new BufferedWriter(fr);
										    	            PrintWriter out = new PrintWriter(br_1);
										    	            for(int k=0; k<data.length; k++)
										    	            {
										    	                if(data[k] != "")
										    	                {
										    	                	System.out.println("the new value is "+data[k]); 		
										    	                	data[k]=data[k].trim();
										    	                	out.write(data[k]+" ");
										    	                	everything=everything.trim();
										    	                }
										    	                else
										    	                {
										    	                	data[k]="";
										    	                	System.out.println("the new value is "+data[k]);
										    	                	data[k]=data[k].trim();
										    	                	out.write(data[k]);
										    	                	everything=everything.trim();
										    	                }
										    	            }
										    	            out.close();
										    	            userLock.unlock();
										    	        }
										    	         
										    	        catch(IOException e)
										    	        {
										    	         System.out.println(e);   
										    	         logger.info("Exception " +e);
										    	        }
										    	        break;
										    	    }
										    	    else
										    	    {
										    	    	System.out.println("ip can't removed");
										    	    }
										    	}										
										}
										catch(Exception e)
										{
											System.out.println("the counter updated and nothing in the file");
										}			            
						        }
						        catch(Exception e)
						        {
						        	System.out.println("exception occured " +e);
						        	logger.info("exception "+e);
						        } 
							     br.close();
						      }
						        dos.flush();
						       	dis_1.close();
						       	fis.close();
						       	bis.close();	
						       	reader.close();						      
								os.flush();				        
				            break;
												
					        default:
					        	System.out.println("request received is not FEAST");
					        	ps.println("FAIL");	
					        	logger.info("received request is not correct");
						}
					}
					else
					{
						System.out.println("The feast software is not found ");
						ps.println("filenotfound");
						logger.info("file not found");
					}
					}
					else
					{
						ps.println("FAILED");
						System.out.println("no more licences are avialable");
						logger.info("no more license are available");
					}
				}
				else
				{
					ps.println("ERROR");
					System.out.println("not a registered user");
					logger.info("not a registered server");
				
				}			
				dos.close();
				os.close();
				ps.close();
				dis.close();
				des.close();
				source.close();
				socket.close();	
			}
			else
			{
				System.out.println("the setup file is not existing");
				ps.println("setup");
				logger.info("Setup missing");
			}
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, "Exception Occuered while using the software!!!! Error Executing FEAST","Failure", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Exception "+e1);
				logger.info("Exception "+e1);
				
			}
		}			
}

