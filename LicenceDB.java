import java.sql.*;
public class LicenceDB 
{
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    LicenceDB()
    {
        try{
			String url="jdbc:mysql://localhost:3306/Licencemanager";
			String username = "root";
			String password="root";
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
			System.out.println("Database connection established");
//			pst=con.prepareStatement("select * from login where username=? and password=?");			
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }   
    //admin login
    public boolean checkadminLogin(String uname,String pwd)
    {
    	try {
        	pst=con.prepareStatement("select * from login where name=? and password=?");
            System.out.println("Administrator = "+uname);
            System.out.println("Password = "+pwd);
            pst.setString(1, uname);
            pst.setString(2, pwd);
            rs=pst.executeQuery();
            if(rs.next())
            {
            	System.out.println("admin password matched");
            	
            	return true;
            	
            }
            else
            {
            	System.out.println("admin password not matched");
            	return false;
            }
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
		return true;
    }
    
    //adding new user
    
    public Boolean adduser(String user , String licence,String dept,String phno, String mailid)
    {
    	try
    	{
    		pst=con.prepareStatement("insert into licencetable(name,licence,department,phoneno,mailid)values ( ? , ? , ? , ? , ? ) ");
			System.out.println("Name " + user);	
			System.out.println("Licence" + licence);
			System.out.println("Department " + dept);
			System.out.println("phno " + phno);
			System.out.println("mailid " + mailid);
			//System.out.println("CDate " + cdate);
		//	System.out.println("CTime " + ctime);
			//System.out.println("status " + status);
			//System.out.println("last status " + laststatus);
			pst.setString(1, user);
			pst.setString(2, licence);
			pst.setString(3, dept);
			pst.setString(4, phno);
			pst.setString(5, mailid);
			//pst.setString(8, cdate);
			//pst.setString(9, ctime);
			//pst.setString(10, status);
			//pst.setString(11, laststatus);
			
			if(pst.executeUpdate()==1)
			{
				System.out.println("new user's licence added");
			}
			
			else
			{
				System.out.println("new user's licence  not added");
			}
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    	return true;
    	
    }
    public Boolean createtime(String user,String ctime)
    {
    	try
    	{
    		pst=con.prepareStatement("update licencetable set C_Time= ? where name= ?");
    		System.out.println("name =" +user);
			System.out.println("C_Time= " +ctime);
			pst.setString(2, user);
			pst.setString(1, ctime);		
			if(pst.executeUpdate()==1)
			{
				System.out.println("creation time added");
			}
			else
			{
				System.out.println("creation time not added");
			}
//			con.commit();
		}
    		
    	
    	catch(Exception e)
    	{
    		System.out.println("exception" +e);
    	}
    	return true;
    }
    public Boolean createdate(String user,String cdate)
    {
    	try
    	{
    		pst=con.prepareStatement("update licencetable set C_Date= ? where name= ?");
    		System.out.println("name =" +user);
			System.out.println("C_Time= " +cdate);
			pst.setString(2, user);
			pst.setString(1, cdate);		
			if(pst.executeUpdate()==1)
			{
				System.out.println("Creation date added");
			}
			else
			{
				System.out.println("not added");
			}
//			con.commit();
		}
    		
    	
    	catch(Exception e)
    	{
    		System.out.println("exception" +e);
    	}
    	return true;
    }
    
 //   public boolean insertlog(String user)
  //  {
 //	  try
 //	  {
 		   
 //		   pst=con.prepareStatement("insert into usagestat (userID) values(?)");
 //		   System.out.println("Username : " + user);
 //		   pst.setString(1,user);
 //		   pst.executeUpdate();
 //	   }   
 //	   catch(Exception e)
 //	   {
 //		   System.out.println("over writting the details");
 //	   }
 //	return true;
 	   
//    }
   
    
    //delete user
   
    public String deletecheckuser()
    {
    	String str = "";
    	try
    	{
    		pst = con.prepareStatement("select name from licencetable");
    		rs=pst.executeQuery();
    		while(rs.next())
    		{
    			 
    			str+= rs.getString(1);
    			str +=",";
    			System.out.println(str);
    		}
    		int a=rs.getRow();
    		System.out.println("number of Rows : " +a);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    	return str;
    	
    }
    
    public Boolean deleteuser(String name)
    {
    	try
    	{
    		pst=con.prepareStatement("delete from licencetable where name= ? ");
    		System.out.println("username : " +name);
    		pst.setString(1,name);
    		if(pst.executeUpdate()==1)
    		{
    			System.out.println("user deleted successsfully");
    		}
    		else
    		{
    			System.out.println("user not deleted successfully");
    		}
    			
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    	return true;
    }
    
   
    //updating admin password
   
    public Boolean updateadminpwd(String admin,String conpwd)
    {
		try
		{
			pst=con.prepareStatement("update login set password=? where name=?");
			System.out.println("LicenceAdminID=" +admin);
			System.out.println("Password=" +conpwd);
			pst.setString(2, admin);
			pst.setString(1, conpwd);		
			if(pst.executeUpdate()==1)
			{
				System.out.println("Password updated");
			}
			else
			{
				System.out.println("not updated");
			}
//			con.commit();
		}
		catch(Exception e)
		{
			System.out.println("error while updating");
		}
		return true;
		
    	
    	//return true;    	
    }
    
    //updating user
    public Boolean updateusers(String user,String licence,String dept,String phno,String mailid,String cdate,String ctime ) 
	{
		try
		{
			pst=con.prepareStatement("update licencetable set name= ?, licence= ?, department= ?, phoneno= ?, mailid= ?, C_Date= ?, C_Time= ? where name= ? ");
			pst.setString(1, user);
			pst.setString(2, licence);
			pst.setString(3, dept);
			pst.setString(4, phno);
			pst.setString(5, mailid);
			pst.setString(6, cdate);
			pst.setString(7, ctime);			
			pst.setString(8, user);
			if(pst.executeUpdate()==1)
			{
				System.out.println("user updated");
			}
			else
			{
				System.out.println("user not updated");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
    	
    	return true;
		
	}
    
    public String userupdatecheck()
    {
    		String str = "";
        	try
        	{
        		pst = con.prepareStatement("select name from licencetable");
        		rs=pst.executeQuery();
        		while(rs.next())
        		{
        			 
        			str+= rs.getString(1);
        			str +=",";
        			System.out.println(str);
        		}
        		int a=rs.getRow();
        		System.out.println("number of Rows : " +a);
        	}
        	catch(Exception e)
        	{
        		System.out.println(e);
        	}
        	return str;    	
    }
    
    public String selectuser(String name)
    {
    	String str = null;
    	 try 
    	 {
    		String user = null;
    		String licence = null;
    		String dept = null;
       		String phno = null;
    		String mailid = null;
    		String cdate = null;
    		String ctime = null;    		
    		pst=con.prepareStatement("select * from licencetable where name= ? ");
            System.out.println("username : " +name);
            pst.setString(1, name);
            rs=pst.executeQuery();
            if(rs.next())
            {
	            user=rs.getString(1);
	            licence=rs.getString(2);
	            dept=rs.getString(3);
	            phno=rs.getString(4);
	            mailid=rs.getString(5);
	            cdate=rs.getString(6);
	            ctime=rs.getString(7);            
				System.out.println("Username = "+user);
				System.out.println("Licence = "+licence);
				System.out.println("department = "+dept);
				System.out.println("PhNo = "+phno);
				System.out.println("MailID = "+mailid);
				System.out.println("C_date = "+cdate);
				System.out.println("C_time = "+ctime);
  //  pst.setString(1, userid);
          //  System.out.println("userID :" +userid);
            }
            str=user.trim()+","+licence.trim()+","+dept.trim()+","+ phno.trim()+","+mailid.trim()+","+cdate.trim()+","+ctime.trim();
            return str;
             
            
    	 }
    	 catch(Exception e)
    	 {
    		 
    	 }
		
    	return str;
    }
    
//viewing user details
    
    public String view()
    {
    	String str = "";
    	try
    	{
    		pst = con.prepareStatement("select name from licencetable");
    		rs=pst.executeQuery();
    		while(rs.next())
    		{
    			 
    			str+= rs.getString(1);
    			str +=",";
    			System.out.println(str);
    		}
    		int a=rs.getRow();
    		System.out.println("number of Rows : " +a);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    	return str;
    }
    
    public String viewuserdetails(String userid)
    {
    	String str = null;
    	 try 
    	 {
    		String user = null;
    		String licence = null;
    		String dept = null;
    		String phno = null;
    		String mailid = null;
    		String cdate = null;
    		String ctime = null;
    		
    		pst=con.prepareStatement("select * from licencetable where name= ? ");
            System.out.println("name : " +userid);
            pst.setString(1, userid);
            rs=pst.executeQuery();
            if(rs.next())
            {
	            user=rs.getString(1);
	            licence=rs.getString(2);
	            dept=rs.getString(3);
	            phno=rs.getString(4);
	            mailid=rs.getString(5);
	            cdate=rs.getString(6);
	            ctime=rs.getString(7);
            
				System.out.println("Username = "+user);
				System.out.println("licence = "+licence);
				System.out.println("department = "+dept);
				System.out.println("phonenumber = "+phno);
				System.out.println("Emailid = "+mailid);
				System.out.println("C_date = "+cdate);
				System.out.println("C_time = "+ctime);
  //  pst.setString(1, userid);
          //  System.out.println("userID :" +userid);
            }
            str=user.trim()+","+licence.trim()+","+dept.trim()+","+phno.trim()+","+mailid.trim()+","+cdate.trim()+","+ctime.trim();
            return str;
             
            
    	 }
    	 catch(Exception e)
    	 {
    		 System.out.println(e);
    	 }
		
    	return str;
    }
}
