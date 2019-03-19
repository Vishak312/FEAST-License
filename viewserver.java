package server;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.Timer;
public class viewserver 
{	
	public static void main(String[] args) throws Exception
	{
		ServerSocket clientviewRequest=new ServerSocket(1125);
		boolean flag=true;
		JFrame f = new JFrame();
        final JDialog dialog = new JDialog(f, "VIEW SERVER STARTED", true);
        dialog.setSize(300, 0);
        dialog.setLocation(800, 200);
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
			System.out.println("waiting for client show request");
			Socket clientviewsocket=clientviewRequest.accept();
			new CommunicationclientviewThread(clientviewsocket);
		}
		clientviewRequest.close();
	}
}
class CommunicationclientviewThread extends Thread
{	
	Socket socket;
	
	
	CommunicationclientviewThread(Socket s)
	{
		socket=s;
		start();
		
	}
	public void run()
	{
		try
		{
			String SysPath=System.getenv("TEMP");
			InputStream source=socket.getInputStream();
			OutputStream des=socket.getOutputStream();
			DataInputStream dis=new DataInputStream(source); 
			PrintStream ps=new PrintStream(des, true);	
			DataOutputStream dos= new DataOutputStream(des);
			OutputStream os = socket.getOutputStream();
			String show=dis.readLine();			
			System.out.println("request received from the user is : " +show);
			if(show.equals("userview"))
			{
				ps.println("success");
				System.out.println("Sending success message");
//				String path=System.getenv("TEMP");
				String filepath=SysPath+"/USERS.txt";
				File myFile = new File(filepath);	
				int len=(int) myFile.length();
				System.out.println("the length of the file is " +len);
				byte[] mybyte = new byte[len];
				FileInputStream fis = new FileInputStream(myFile);
				BufferedInputStream bis = new BufferedInputStream(fis);
				//bis.read(mybytearray, 0, mybytearray.length);											
				DataInputStream dis_1 = new DataInputStream(bis);   
				dis_1.readFully(mybyte, 0, mybyte.length);				    
				dos.writeUTF(myFile.getName());      
				dos.writeLong(mybyte.length);   
		        dos.write(mybyte, 0, mybyte.length); 							        
		        dos.flush();
		       	dis_1.close();
		       	fis.close();
		       	bis.close();							      
				os.flush();
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
}
