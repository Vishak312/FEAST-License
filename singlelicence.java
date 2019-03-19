import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class singlelicence {	
	public static int counter=0;
	public static Process p=null;
	static Runtime r=Runtime.getRuntime();
	private static final String ALGO = "AES";
    private static final byte[] keyValue =new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
    private static SecretKeySpec generateKey() throws Exception 
    {      
     	SecretKeySpec key = new SecretKeySpec(keyValue, ALGO);
        return key;  
    }
    public static String encrypt(String Data) throws Exception 
    {
       SecretKeySpec key = generateKey();
       Cipher c = Cipher.getInstance(ALGO);//Get a cipher object
       c.init(Cipher.ENCRYPT_MODE, key);
       byte[] encVal = c.doFinal(Data.getBytes());
       String encryptedValue = new BASE64Encoder().encode(encVal);
       return encryptedValue;
   }
     public static String decrypt(String encryptedData) throws Exception {
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
			InetAddress ipaddress=InetAddress.getLocalHost();
			System.out.println("ipaddress is "  +ipaddress);
			String path1=System.getenv("TEMP");
			File ipfile = new File(path1 + "/ip.txt");
			if(ipfile.exists())
			{
				System.out.println("already logged in");
				JOptionPane.showMessageDialog(null, "Already Logged in!!! try after closing FEAST", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				
			
			System.out.println("file is not existing... starting the process");	
	//		try 
	//			{
//					ps.println("SUCCESS");					
				System.out.println("the current ip address : " +ipaddress );
				String address=ipaddress.toString();
				String oldtext="";
				String newtext = oldtext.replace(oldtext,address);
				FileWriter writer = new FileWriter(path1 + "/ip.txt");
			    writer.write(newtext);
			    writer.close();					    						
				FileReader fileReader = new FileReader(ipfile);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer stringBuffer = new StringBuffer();
				String line11;
				while ((line11 = bufferedReader.readLine()) != null) 
				{
					stringBuffer.append(line11);
					stringBuffer.append("\n");
				}
				fileReader.close();
				System.out.println("Contents of file:");
				if(stringBuffer.toString().contains(address))
				{
					System.out.println(stringBuffer.toString());
				}
				else
				{
					System.out.println("error");
				}													
			
			
			
			final String status=null;
			StringBuilder sb = new StringBuilder();	
			
			try
			{
				InetAddress ip = InetAddress.getLocalHost();			
				System.out.println("Current IP address : " + ip.getHostAddress());	 
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
			System.out.println("The mac address of server is " +sb);
			String ip=InetAddress.getLocalHost().getHostAddress();			
			System.out.println("the ip address of the server is :" +ip);			
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader("//10.43.15.29/new/vishak/FEAST/licence.txt"));
	    	String line1=null;
	    	if((line1 = reader.readLine()) != null) {

	    		System.out.println("encrypted licence for the user is : " +line1);
	    		 
	    	}
	    	
	    	System.out.println(line1);
	    	String[] key=new String[4];
	    	singlelicence svr=new singlelicence();//object for class server
	    	@SuppressWarnings("static-access")
			String passwordEnc1 = svr.encrypt(line1);
			System.out.println("Encrypted Text : " + passwordEnc1);
			String newenctext = line1.replaceFirst(line1, passwordEnc1);
			System.out.println("the encrypted text is " +newenctext);     
	       @SuppressWarnings("static-access")
		String passwordDec = svr.decrypt(line1);
	       System.out.println(" the decrypted value is : " +passwordDec);
	       String licencekey=passwordDec;

	       key=licencekey.split("-");
	       System.out.println("the first key is " + key[0]);
       	   System.out.println("the second key is " +key[1]);
	       System.out.println("the third key is " +key[2]);
	       System.out.println("the macaddress is "+key[3]);
	       System.out.println("the final key is " +key[4]);
	       System.out.println("licence key value : " + licencekey);
	       int value=Integer.parseInt(key[4]);
	       System.out.println(sb);
	       System.out.println(key[3]);
	       if(key[3].contains(sb))
	       {
		    	if(counter<value)
				{
		    		String path="C:\\Program Files (x86)/Server/User/Licence/FEAST-32Bit/PreWin95.exe";
		    			    		
		    		p=r.exec(path);
		    		
							
			        int ret=p.waitFor();
			        
					if(ret==0)
					{
						try
								{
										
										ipfile = new File(path1 + "/ip.txt");
										FileReader fileReader1 = new FileReader(ipfile);
										BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
										StringBuffer stringBuffer1 = new StringBuffer();
										String line2 = null;
										while ((line2 = bufferedReader1.readLine()) != null) 
										{
											stringBuffer1.append(line2);
											stringBuffer1.append("\n");
										}
										fileReader1.close();
										if(ipfile.exists())
										{
											System.out.println("file identified");
											if(ipfile.delete())
											{
								    			System.out.println(ipfile.getName() + " is deleted!");
											}
											else
											{
								    			System.out.println("Delete operation is failed.");
								    		}
											
										}
										else
										{
											System.out.println("file could not identified ");
										}
									}
									catch(Exception e)
									{
										System.out.println("exception caught " +e);
									}
			        		}
						}
					}
					else
					{
						System.out.println("not a registered user");
						JOptionPane.showMessageDialog(null, "server is not matching!!!! error Starting FEAST","Failure", JOptionPane.INFORMATION_MESSAGE);

				
					}
				}
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, "server is not matching!!!! error Starting FEAST","Failure", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Exception "+e1);
			
						
			}
	
     	}

}
	

