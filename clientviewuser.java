package client;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
public class clientviewuser 
{
	static String WinDir=System.getenv("windir");
	public static void main(String[] args)throws Exception
	{	
		final JFrame frame1=new JFrame("FEAST Users");
		frame1.setUndecorated(true);
		frame1.setSize(300,300);
		frame1.setLocation(300, 300);
		frame1.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frame1.setResizable(true);
		JPanel panel=new JPanel();
		//frame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame1.add(panel);
		placeComponents(panel);
		frame1.setVisible(true);
		
		//JLabel label=new JLabel("feast users");
		//label.setBounds(10, 10, 50, 25);
		//panel.add(label);
		
	}
	
	private static void placeComponents(JPanel panel) throws Exception {
		// TODO Auto-generated method stub
		//panel.setBackground(Color.CYAN);
		try
		{
		panel.setLayout(null);
		InetAddress ipaddress;			 
		ipaddress = InetAddress.getLocalHost();
		System.out.println("Current IP address : " + ipaddress.getHostAddress());
		BufferedReader reader = new BufferedReader(new FileReader(WinDir+"/config.txt"));
    	String line1 = null; 
    	if((line1= reader.readLine()) != null) 
    	{ 
    		System.out.println("server IP_Address : " +line1);
    	}
    	String net[];
    	net=line1.split(",");    	
		String host=net[0];
		int portaddress=Integer.parseInt(net[2]);
		int port=portaddress;
		int bytesRead;
	//	Runtime r=Runtime.getRuntime(); 
    //    Process p=null;
        String path=System.getenv("TEMP");
        System.out.println("the path of the temprory folder is " +path);
		//@SuppressWarnings("resource")
		Socket sc=new Socket(host,port);
		InputStream in = sc.getInputStream();	
		OutputStream des=sc.getOutputStream();
		DataInputStream dis=new DataInputStream(in);
		PrintStream ps=new PrintStream(des, true);
		ps.println("userview");
		System.out.println("Request send is userview");
		String received=dis.readLine();
		System.out.println("th received value from server is "+ received);
		if(received.equals("success"))
		{
			
			String fileName = dis.readUTF(); 
			System.out.println(fileName);
			OutputStream output = new FileOutputStream(path+"/"+fileName);   
			long size = dis.readLong();   
			byte[] buffer = new byte[1024];   
			while (size > 0 && (bytesRead = dis.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)   
			{   output.write(buffer, 0, bytesRead);   
			    size -= bytesRead;   
			}
			
			// Closing the FileOutputStream handle
			output.close();
			output.flush();						
		}
	
		System.out.println("file printed successfully");
		String file=path +"/" +"USERS.txt";
        
        System.out.println("Reading File line by line using BufferedReader");
        BufferedReader br = new BufferedReader(new FileReader(file));
        try 
        {
        	System.out.println("reading the file contents");
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) 
            {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            
            String everything = sb.toString();
            System.out.println("the contents are " +everything);
	        String data[];
	        data=everything.split(" ");
	        int length=data.length;
	        System.out.println("the length of the file is " +length);
	        panel.setLayout(new BorderLayout());
	        @SuppressWarnings({ "rawtypes", "unchecked" })
			final JList list = new JList(data);
		    JScrollPane pane = new JScrollPane(list);
		    pane.setBounds(10, 10, 10, 1000);
		    panel.add(pane);			    
	    	br.close();
        }
        catch(Exception e)
        {} 
        reader.close();
        sc.close();
        
    	
		}
		catch(Exception E)
		{
			System.out.println("exception : " +E);
			JOptionPane.showMessageDialog(null,"Unfortunately Server is closed or not yet opened !!!! No connections are available" ,"Error", JOptionPane.INFORMATION_MESSAGE);

		}
	}	
}


	
					

