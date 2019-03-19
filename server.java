package server;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class server {	
	static Logger logger = Logger.getLogger(CommunicationThread.class.getName());  
	static FileHandler fh;
	static String path=System.getenv("TEMP");
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
		String WinPath=System.getenv("windir");	
		//String FEASTPath=System.getenv("FEASTDIR");
		
	    try 
	    {  
	        fh = new FileHandler(path+"/serverlog.log");  
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
	    BufferedReader reader = new BufferedReader(new FileReader(WinPath+"/Socket.txt"));
    	String line = null; 
    	if((line = reader.readLine()) != null) 
    	{ 
    		logger.info("IP_Address of the server : " +line);
    	}
    	int sckt=Integer.parseInt(line);
		ServerSocket clientRequest=new ServerSocket(sckt);	
		boolean flag=true;
		JFrame f = new JFrame();
        final JDialog dialog = new JDialog(f, "SERVER STARTED", true);
        dialog.setSize(300, 0);
        dialog.setLocation(600, 400);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setResizable(false);
        Timer timer=new Timer(1000,null);
        
        timer.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dialog.setVisible(false);
                dialog.dispose();					
			}
		});
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
	    System.out.println("Dialog closed");	
		while(flag)                   
		{	
			String file =WinPath+"/License";							 
			String cmd1[] = {"attrib","+h",file}; 
			Runtime.getRuntime().exec(cmd1);
			System.out.println("hide successfully");
			System.out.println("waiting for client request");
			Socket clientSocket=clientRequest.accept();
			new CommunicationThread(clientSocket);			
			
			
		}
		clientRequest.close();	
		reader.close();
		
	}
}	
	