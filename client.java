package client;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Enumeration;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JOptionPane;
public class client 
{	
	public static int logincount=0;
	public static void main(String[] args) throws Exception 
	{
		final String path = System.getenv("TEMP");
		String syspath=System.getenv("TEMP");			//TEMP path
		Logger logger = Logger.getLogger(client.class.getName());  
		FileHandler fh; 
		String WinPath=System.getenv("windir");
		try
		{	
				//newly adding logger code	
			System.out.println("The window directory path is "+WinPath);
		
		    try 
		    {  
		        fh = new FileHandler(syspath+"/clientlog.log");  
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
			final InetAddress ipaddress;			 
			ipaddress = InetAddress.getLocalHost();
			logger.info("IP address of Currently using client  : " + ipaddress.getHostAddress());
			BufferedReader reader = new BufferedReader(new FileReader(WinPath+"/config.txt"));
	    	String line = null; 
	    	String config = null;
	    	while((line = reader.readLine()) != null) 
	    	{ 
	    		logger.info("IP_Address of the server : " +line);
	    		config=line;
	    	}
	    	System.out.println("the value read from the text is "+config);
	    	String net[];	    	
	    	net=config.split(",");
	    	System.out.println("the Server is "+net[0]);	    	
			String host=net[0];
			int portaddress=Integer.parseInt(net[1]);
			System.out.println("the port is "+portaddress);
			int port=portaddress;
			int bytesRead;
			Runtime r=Runtime.getRuntime(); 
			Socket sc=new Socket(host,port);
			InputStream in = sc.getInputStream();	
			OutputStream des=sc.getOutputStream();
			DataInputStream dis=new DataInputStream(in);
			final PrintStream ps=new PrintStream(des, true);
			ps.println("FEAST");
			logger.info("feast");
			String ip=InetAddress.getLocalHost().getHostAddress();
			String user=dis.readLine();
			System.out.println("the licence status is " +user);
			if(user.equals("Licence"))
			{
			    System.out.println("IP Address is " +ip);
			    
				System.out.println(path);
				File feastfile=new File(path + "/software");
				
				if(feastfile.exists())					
				{
					ps.println("Exist");
					JOptionPane.showMessageDialog(null, "Duplicate User!!! Please close already running FEAST","Success", JOptionPane.INFORMATION_MESSAGE);	
					logger.info("Duplicate user login");

				}
				else
				{
					ps.println("NEW");
					logger.info("file is not existing... starting the process");					
					String status=dis.readLine();
					System.out.println(status);				
					if(status.equals("SUCCESS"))
					{					
						JOptionPane.showMessageDialog(null, "FEAST Licence Obtained!!!! Waiting for Executing FEAST","Success", JOptionPane.INFORMATION_MESSAGE);	
						String fileName = dis.readUTF(); 
						System.out.println(fileName);
						OutputStream output = new FileOutputStream(path+"/"+fileName);   
						long size = dis.readLong();   
						byte[] buffer = new byte[1024];   
						while (size > 0 && (bytesRead = dis.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)   
						{  
							output.write(buffer, 0, bytesRead);   
						    size -= bytesRead;   
						}
						
						// Closing the FileOutputStream handle
						output.close();
						output.flush();
						System.out.println("file printed successfully");	
						logger.info("file printed successfully");
						ZipEntry zipEntry = null;				   
						try 
						{
							System.out.println(path);
							ZipFile zipFile = new ZipFile( path+"/"+"software.zip");
							Enumeration<?> enu = zipFile.entries();
							while (enu.hasMoreElements()) 
							{
								zipEntry = (ZipEntry) enu.nextElement();		
								String name = zipEntry.getName();
								System.out.printf("name: %-20s |\n", name);
								File file = new File(path+"/"+name);
								if (name.endsWith("/")) 
								{
							 		file.mkdirs();
							 		
									continue;
								}	
								File parent = file.getParentFile();
								if (parent != null) 
								{
									parent.mkdirs();
								}		
								InputStream is = zipFile.getInputStream(zipEntry);
								OutputStream fos = new FileOutputStream(file);
								byte[] bytes = new byte[1024];
								int length;
								while ((length = is.read(bytes)) >= 0) 
								{
									fos.write(bytes, 0, length);
								}
								is.close();
								fos.close();								
							}								
							zipFile.close();
							File f=new File(path+"/"+"software.zip");						
							boolean read=f.setReadable(true);
							System.out.println("readable : " +read);
							boolean success=f.delete();
							System.out.println(success);
						} 
						catch (IOException ioe) 
						{
							logger.info("Unhandled exception:" +ioe);
							return;				      
						}
						ProcessBuilder pb = new ProcessBuilder(path+"/"+"software"+"/"+"PreWin.exe");
						pb.redirectErrorStream(true);
						Process p = pb.start();
						logger.info("feast executed successfully");						
						String file =path+"/"+"software";							
						String cmd1[] = {"attrib","+h",file};
						Runtime.getRuntime().exec(cmd1);
						//logger.info("hide successfully");
						InetAddress ipaddr=InetAddress.getLocalHost();
						ps.println(ipaddr);
						Runtime.getRuntime().addShutdownHook(new Thread() 
				        {
	
						      @Override
						      public void run() 
						      {
						    	  File directory = new File(path + "/"+"ip.txt");
						             directory.deleteOnExit();
						             System.out.println(directory);
						      }
						 });
						System.out.println("ip address send");
						
						
						BufferedReader read = new BufferedReader(new InputStreamReader(p.getInputStream()));
						String line1;
						while((line1 = read.readLine()) != null)
						{
							System.out.println("Executable"+line1);
							
						}
						int ret = p.waitFor();

						ps.println("LOGOUT");		
						ps.println(ipaddress);
						logger.info("Logout status "+ret);																																		
						String pat=path+"/"+"software";	
						File fi=new File(path+"/"+"software");
						if(fi.exists())
						{
							String filepath=pat;
							File directory=new File(filepath);				
					
							String[] list = directory.list();
							if (list != null) 
							{
								
								for (int i = 0; i < list.length; i++) 
								{
									File entry = new File(directory, list[i]);
									System.out.println("\t removing entry " + entry);
									if (entry.isDirectory())
									{
										logger.info("deleting");
										
										return;
									}
									else
									{
										if (!entry.delete())
										{
											logger.info("not deleted");
											return ;
											
										}
									}
								}
								System.out.println("directory removed successfully");
								System.out.println(directory.delete());
								logger.info("Successfully complted");
								
							}
							
							
										
						}
					}
				
					else if(status.equals("FAILED"))
					{
						logger.info("no more licences are available");
						JOptionPane.showMessageDialog(null,"No more Licences are available!!!! Contact your Administrator" ,"Error", JOptionPane.INFORMATION_MESSAGE);
					}	
				
					else if(status.equals("filenotfound"))
					{
						logger.info("Source not Found or Corrupted");
						JOptionPane.showMessageDialog(null,"The software has been Corrupted or may be not installed on your server !!!!\n                                 Please  Contact your Administrator" ,"Error", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			}
			else if(user.equals("ERROR"))
			{
				logger.info("No registered Servers are available for the process");
				JOptionPane.showMessageDialog(null,"The server is not a registered server Please Contact FEAST Administrator!!!!                                            \nError executing Feast" ,"Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(user.equals("setup"))
			{
				logger.info("Setup file not found");
				JOptionPane.showMessageDialog(null,"The setup file is missing Please contact Administrator!!!! \n                            error executing Feast" ,"Error", JOptionPane.INFORMATION_MESSAGE);

			}
			else
			{
				logger.info("No More Licences are Available");
				JOptionPane.showMessageDialog(null,"No More Licences are Available!!!! Contact your Administrator!!!" ,"Error", JOptionPane.INFORMATION_MESSAGE);
			}
			
			ps.close();
			dis.close();
			des.close();
			sc.close();
			reader.close();
			
		}
		catch(Exception E)
		{
		
			System.out.println("exception : " +E);
			String pat=path+"/"+"software";	
			File fi=new File(path+"/"+"software");
			if(fi.exists())
			{
				String filepath=pat;
				File directory=new File(filepath);							
				String[] list = directory.list();
				if (list != null) 
				{
					for (int i = 0; i < list.length; i++) 
					{
						File entry = new File(directory, list[i]);
						System.out.println("\t removing entry " + entry);
						if (entry.isDirectory())
						{
							System.out.println("deleting");
							
							System.out.println("the status of software send : deleted");
							return;
						}
					else
						{
							if (!entry.delete())
							{
								System.out.println("not deleted");
								
								System.out.println("the status of feast software send : not deleted");
								return ;											
							}
						}
					}
					System.out.println("directory removed successfully");
					System.out.println(directory.delete());
				}
				System.out.println("Temporary files are removed successfully");	
			}
			logger.info("Server is not yet opened!!! run the server first"); 
			JOptionPane.showMessageDialog(null,"Unfortunately Server is closed or not yet opened !!!! No connections are available" ,"Error", JOptionPane.INFORMATION_MESSAGE);
		}			
	}
}