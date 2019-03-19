package singleuserlicence;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class licence 
{	
	public static int counter=0; 
	static Logger logger = Logger.getLogger(licence.class.getName());  
	static FileHandler fh;	
	static String SysPath=System.getenv("TEMP");	
	static String WinPath=System.getenv("windir");
	private static final String ALGO = "AES";
    private static final byte[] keyValue =new byte[] { 'K', 'e', 'y', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't','T', 'h', 'e' };
    private static SecretKeySpec generateKey() throws Exception 
    {      
     	SecretKeySpec key = new SecretKeySpec(keyValue, ALGO);
        return key;  
    }
    public String encrypt(String Data) throws Exception 
    {
       SecretKeySpec key = generateKey();
       Cipher c = Cipher.getInstance(ALGO);//Get a cipher object
       c.init(Cipher.ENCRYPT_MODE, key);
       byte[] encVal = c.doFinal(Data.getBytes());
       String encryptedValue = new BASE64Encoder().encode(encVal);
       return encryptedValue;
   }
     public String decrypt(String encryptedData) throws Exception {
        SecretKeySpec key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
	public static void main(String[] args) throws Exception 
	{	
		try 
	    {  
	        fh = new FileHandler(SysPath+"/License.log");//LOG file  
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
		String feastPath=WinPath+"/License";
		String folderPath=feastPath+"/software";
		String cmd[] = {"attrib","+h",folderPath};
		Runtime.getRuntime().exec(cmd);
		System.out.println("hide successfully");						

		try
		{	
			File licenseFile=new File(WinPath+"/FEAST-KEY.txt");			
			if(licenseFile.exists())
			{
				StringBuilder sb = new StringBuilder();					
				try
				{
					InetAddress ip = InetAddress.getLocalHost();				
					System.out.println("Current IP address : " + ip.getHostAddress());
					logger.info("user ip address "+ip);
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
				} catch (SocketException e){		 
					e.printStackTrace();
				}
				System.out.println("The mac address of user is " +sb);
				InetAddress ipaddress;			 
				ipaddress = InetAddress.getLocalHost();
				System.out.println("Current IP address : " + ipaddress.getHostAddress());				
//				Runtime r=Runtime.getRuntime(); 

//	            Process p=null;				
//				String filepath=WinPath+"/setup.txt";
//				String path1=System.getenv("TEMP");
//				System.out.println("The path is "+path1);
//				String countertext=SysPath+"/"+"countertextfile.txt";
//				File countertextfile=new File(countertext);
//				if(countertextfile.exists())
//				{
//					System.out.println("the file is existing");
//					logger.info("existing");
//				}else{
//					System.out.println("the file is not existing ");
//					logger.info("not existing");
//					try(PrintWriter output=new PrintWriter(new FileWriter(countertextfile))) 
//					{
//						output.printf("%s\r\n", "0" );
//					    System.out.println("the file created successfully");
//					}
//					catch(Exception e){}
//				}
				
				BufferedReader pathread=new BufferedReader(new FileReader(licenseFile.getAbsolutePath()));
				String licence=null;
				boolean found = false;
				while((licence=pathread.readLine())!=null)
				{
					System.out.println("the encrypted licence for the user is " +licence);
					System.out.println(licence);
					String[] key=new String[4];
					licence lice=new licence();//object for class server
					String passwordEnc1 = lice.encrypt(licence);
					System.out.println("Encrypted Text : " + passwordEnc1);
					String newenctext = licence.replaceFirst(licence, passwordEnc1);
					System.out.println("the encrypted text is " +newenctext);	     
					String passwordDec = lice.decrypt(licence);
					System.out.println(" the decrypted value is : " +passwordDec);
					String licencekey=passwordDec;
					key=licencekey.split("-");
					System.out.println("the first key is " +key[0]);
					System.out.println("the second key is " +key[1]);
					System.out.println("the  third key is " +key[2]);
					System.out.println("the fourth key is " +key[3]);
					System.out.println("the fifth key is " +key[4]);
					System.out.println("the last key is "+key[5]);
					int value=Integer.parseInt(key[5]);
					System.out.println("the integer value is " +value);				
//					String content=null;
//					String datainfile=null;
//					BufferedReader filecontent=new BufferedReader(new FileReader(countertext));
//					while((content=filecontent.readLine())!=null)
//					{
//						System.out.println("the text value is " +content);
//						datainfile= content;
//					}		
//					filecontent.close();
//					System.out.println("the value is "+datainfile);
//					int contentvalue=Integer.parseInt(datainfile);
//					System.out.println("the value is " +contentvalue);
					if(key[4].contains(sb))
					{
						found=true;
						break;
					}
				}
				pathread.close();
				
				if(found)
				{
					System.out.println("mac matched");	
					
					File folder=new File(SysPath+"/software");
					String foldervalue=SysPath+"/software";
					if(folder.exists())
					{
						System.out.println("Exist");
						JOptionPane.showMessageDialog(null, "FEAST Licence Obtained!!!!Waiting for executing Feast","Success", JOptionPane.INFORMATION_MESSAGE);				
						ProcessBuilder pb = new ProcessBuilder(SysPath+"/software/"+"PreWin.exe");
						pb.redirectErrorStream(true);
						Process p = pb.start();
						System.out.println("feast executed successfully");
						logger.info("feast executed successfully");
						String cmd1[] = {"attrib","+h",foldervalue};
						Runtime.getRuntime().exec(cmd1);
						System.out.println("hide successfully");
						BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
						String line;
						while((line = reader.readLine()) != null)
						{
							System.out.println("Executable"+line);
				
						}
						int ret = p.waitFor();
						System.out.println(ret);
						System.out.println("logged out successfully");	
						logger.info("the logout status is "+ret);		
						if(folder.exists())
						{
							String[] list = folder.list();
							if (list != null) 
							{
								for (int i = 0; i < list.length; i++) 
								{
									File entry = new File(folder, list[i]);
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
								System.out.println(folder.delete());
								logger.info("Successfully complted");
								JOptionPane.showMessageDialog(null, "Logged out successfully!!!!","success", JOptionPane.INFORMATION_MESSAGE);
								logger.info("successfully logged out");
							}
							//System.out.println("deleting "+folder.delete());
						}
						else
						{
							System.out.println("cannot delete");
//							JOptionPane.showMessageDialog(null, "Logged out successfully!!!!","success", JOptionPane.INFORMATION_MESSAGE);
//							logger.info("successfully logged out");
						}
						
						
						
					}
					
					else
					{
					JOptionPane.showMessageDialog(null, "FEAST Licence Obtained!!!!Waiting for executing Feast","Success", JOptionPane.INFORMATION_MESSAGE);				
						
					ZipEntry zipEntry = null;				   
					try 
					{
						System.out.println(SysPath);
						ZipFile zipFile = new ZipFile( feastPath+"/"+"software.zip");
						Enumeration<?> enu = zipFile.entries();
						while (enu.hasMoreElements()) 
						{
							zipEntry = (ZipEntry) enu.nextElement();		
							String name = zipEntry.getName();
							System.out.printf("name: %-20s |\n", name);
							File file = new File(SysPath+"/"+name);
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
					} 
					catch (IOException ioe) 
					{
						logger.info("Unhandled exception:" +ioe);
						return;				      
					}
					ProcessBuilder pb = new ProcessBuilder(SysPath+"/software/"+"PreWin.exe");
					pb.redirectErrorStream(true);
					Process p = pb.start();
					System.out.println("feast executed successfully");
					logger.info("feast executed successfully");
					BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line;
					while((line = reader.readLine()) != null)
					{
						System.out.println("Executable"+line);
			
					}
					int ret = p.waitFor();
					System.out.println(ret);
					System.out.println("logged out successfully");	
					logger.info("the logout status is "+ret);

					String dirPath=SysPath+"/software";
					File directory=new File(dirPath);
					if(directory.exists())
					{
					
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
						
//					String fileval="";
//					String value_1=""; 
//					BufferedReader br=new BufferedReader(new FileReader(countertextfile));
//					if((fileval=br.readLine())!=null)
//					{
//							System.out.println("the value of the file is "+fileval);
//							value_1=fileval;
//						}
//						br.close();
//						System.out.println("the value in file is "+value_1);
//						int intvalue=Integer.parseInt(value_1);
//						intvalue=intvalue-1;
//						System.out.println("the value after closing the application is " +intvalue);
//						logger.info("after closing "+intvalue);
//						try(PrintWriter output=new PrintWriter(new FileWriter(countertextfile))) 
//						{
//						    output.printf("%s\r\n",intvalue);
//	
//						}
//						catch(Exception e){}					
					JOptionPane.showMessageDialog(null, "Logged out successfully!!!!","success", JOptionPane.INFORMATION_MESSAGE);
					logger.info("successfully logged out");
//						if(intvalue==0)
//						{
//							boolean delete=countertextfile.delete();
//							System.out.println("the countertext file delete is "+delete);
//							logger.info("counter file remove status is "+delete);
//					    		
//						}
//						else
//						{
//							System.out.println("Can not delete the file");
//							logger.info("counter file status is not removed ");
//						}
//					
//					}
//					else
//					{
//						System.out.println("no more Licence Available");
//						JOptionPane.showMessageDialog(null,"No more Licences are available!!!! error executing Feast" ,"Failure", JOptionPane.INFORMATION_MESSAGE);
//						logger.info("No more License");
//					}	
				}
				
			}
			else
			{
				System.out.println("Not a registered user");
				JOptionPane.showMessageDialog(null,"The server is not a registered server \n contact FEAST Administrator!!!! error executing Feast" ,"Failure", JOptionPane.INFORMATION_MESSAGE);
				logger.info("trying by other systems or not a registered user");
			}
//			
		}
			else
				{
					System.out.println("the license file is not existing");
					JOptionPane.showMessageDialog(null,"The License file is missing or corrupted \n Please contact FEAST Administrator!!!! error executing Feast" ,"Failure", JOptionPane.INFORMATION_MESSAGE);
					logger.info("license corrupted");
				}
		}
		catch(Exception E)
		{
			System.out.println("exception : " +E);
			logger.info("Exceptiopn in Initial case "+E);
			//JOptionPane.showMessageDialog(null,"Unfortunately Server is closed or not yet opened !!!! No connections are available" ,"Failure", JOptionPane.INFORMATION_MESSAGE);
		}			
	}
}
