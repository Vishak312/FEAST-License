
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
public class LicenceServer {
	
	
	public static void main(String [] args) throws Exception
	{
		ServerSocket adminRequest=new ServerSocket(1126);		
		boolean flag=true;
		while(flag)
		{
			System.out.println("waiting for licence manager request");
			Socket adminSocket=adminRequest.accept();
			new admin(adminSocket);	

			
		}
		adminRequest.close();
		
	}

}
class admin extends Thread
{
	Socket socket;
	LicenceDB db=new LicenceDB();	
	int flag=0;
	admin(Socket s) 
	{
		socket=s;
		start();
	}
	public  void run()
	{
		
		try
		{
			InputStream source=socket.getInputStream();
			OutputStream des=socket.getOutputStream();
			DataInputStream dis=new DataInputStream(source);
			PrintStream ps=new PrintStream(des, true);	
			DataOutputStream dos= new DataOutputStream(des);
			//OutputStream os = socket.getOutputStream();
			String line=dis.readLine();	
			System.out.println("received :" +line);
			
			String[] st;
			st=line.split(",");
			System.out.println(st[0]);
		
			switch(st[0])
			{
				case "LOGIN" :
					if(st.length < 3)
					{
						System.out.println("Invalid Input argument");
						ps.println("failed");
						return;
						
					}
					//ps.println("SUCCESS");
					if(db.checkadminLogin(st[1],st[2]))
					{
						System.out.println("Sending SUCCESS");
						ps.println("SUCCESS");
						//ps.println(st[0]);
						System.out.println("username and password matched");
						
						System.out.println("waiting for admin action...");
					}
					else
					{
						ps.println("FAILED");
						System.out.println("username and password not matching");
				
					}
					break;
			
		//		String option=dis.readLine();
		//		System.out.println(option);
		//		if(!option.equals(null))
		//		{
		//		switch(option)
		//		{
					case "ADD" :
					
						System.out.println("request for adding");
						Calendar date =Calendar.getInstance();
						int day = date.get(Calendar.DAY_OF_MONTH);
				        int month = date.get(Calendar.MONTH);
				        int year = date.get(Calendar.YEAR);
				 
				        int second = date.get(Calendar.SECOND);
				        int minute = date.get(Calendar.MINUTE);
				        int hour = date.get(Calendar.HOUR);
				        String creationtime=+hour+" : "+minute+": "+second;
				        String creationdate=+day+"/"+(month+1)+"/"+year;
				        System.out.println("Creation date is : " +creationdate);
//				        System.out.println("login time is  "+hour+" : "+minute+" : "+second);
				        System.out.println("Creation time :" +creationtime);	
				        
				        //creationdate;
				        //creationtime;
				       // st[10]="LOGOUT";
				       // st[11]="success";
						//ps.println("adding");
						//String line1=dis.readLine();
						//String[] str ;
						//str=line1.split(" ");
						System.out.println(st[0]);
						//if(st.length < 11)
						//{
						//	System.out.println("Invalid Input argument");
						//	ps.println("failed");
						//	return;
							
						//}
					
						
						if(db.adduser(st[1],st[2],st[3],st[4],st[5]))
						{
							ps.println("SUCCESS");
							System.out.println("new user added successfully");
							
						}
						else
						{
							ps.println("FAILED");
							System.out.println("not added");
						}

						if(db.createdate(st[1], creationdate))
						{
							System.out.println("creation date entered");
						}
						else
						{
							System.out.println("creationdate not entered");
						}
						
					
						if(db.createtime(st[1], creationtime))
						{
							System.out.println("creation time entered");
						}
						else
						{
							System.out.println("creation time not entered");
						}
						
//						if(db.insertlog(st[1]))
//						{
//							System.out.println("user Added");
//						}
//						else
	//					{
	//						System.out.println("user not added ");
//						}
					
					break;
					
					case "SELECTDELETE" :
						
						System.out.println("Request for selecting data to delete");						
						System.out.println(st[0]);
						//ps.println("view");
						String del=db.deletecheckuser();
						ps.println(del);
						System.out.println(del);
						
						
						break;
						
					case "DELETE" :
						System.out.println("Request for deleting");
						System.out.println(st[0]);
						System.out.println(st[1]);
						if(db.deleteuser(st[1]))
						{
							ps.println("SUCCESS");
							System.out.println(" user deleted successfully");
							
						}
						else
						{
							ps.println("FAILED");
							System.out.println("user not deleted");
						}
						
						break;
						
					case "SELECTUPDATE" :
						
						System.out.println("Request for selecting data to update");
						System.out.println(st[0]);
						String upd=db.userupdatecheck();
						ps.println(upd);
						System.out.println("selected data send successfully");
						System.out.println(upd);
					
						break;
						
					case "SELECTEDUPDATE" :
						
						System.out.println("selected for displaying details");
						System.out.println(st[0]);
						System.out.println(st[1]);
						String data=db.selectuser(st[1]);
						ps.println(data);
						System.out.println("details : " +data);
						break;
						
					case "UPDATE" :
						
						System.out.println("Request for updating");
					
						if(st.length<7)
						{
							System.out.println("invalid input argument");
							ps.println("failed");
							return;
							
						}
						
						if(db.updateusers(st[1], st[2], st[3], st[4], st[5], st[6], st[7]))
						{
							ps.println("SUCCESS");
							System.out.println("user updated successfully");
						}
						else
						{
							ps.println("FAILURE");
							System.out.println("user cannot updated");
						}
						
						break;
						
					case "VIEW" :
						System.out.println("Request for viewing the user deatils");
						System.out.println(st[0]);
						//ps.println("view");
						String list=db.view();
						ps.println(list);
						System.out.println(list);
						
						break;
						
					case "VIEWDETAILS" :
						
						System.out.println("selected for displaying details");
						System.out.println(st[0]);
						System.out.println(st[1]);
						String view=db.viewuserdetails(st[1]);
						ps.println(view);
						System.out.println("details : " +view);
						
						break;
						
					case "changepassword" :
						
						System.out.println("Request for changing password");
						System.out.println(st[0]);
						ps.println("changingpassword");

						if(db.updateadminpwd(st[1],st[2]))
						{
							System.out.println("updation success");
							ps.println("SUCCESS");
						}
						else
						{ 
							System.out.println("Updation Failed");
							ps.println("FAILED");
						}
						
						break;
						
					default:
						
						System.out.println("logout from admin");
				
				
				}	
				
				//else
			//	{
			//		System.out.println("Error receiving option");
			//	}
			
				
					//ps.println("success");
		
			
			
			//System.out.println(dis.readLine());
			//if(dis.readLine().equals("ADD"))
			
	//		String option=dis.readLine();
	//		System.out.println(option);
		//	if(option.equals("ADD"))
			
			
			
			
					
			
//			db.close();
			dos.close();
		//	os.close();
			ps.close();
			dis.close();
			des.close();
			source.close();
			socket.close();
			
			
		}
		catch(Exception e)
		{
			System.out.println("exception : " +e);
		}
	}


}