package administrator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
	public class userdetails {
		static JFrame frame = new JFrame("Licence Management");	
		public static void main(String[] args) throws Exception 
		{
			frame.setSize(450, 270);
			frame.setLocation(300, 300);
			frame.setResizable(false);
			frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
//			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			JPanel panel = new JPanel();
			frame.add(panel);
			placeComponents(panel);	
			frame.setUndecorated(true);
	       frame.setVisible(true);
		}
		private static  void placeComponents(final JPanel panel) throws IOException 
		{
//			db=new db();
			panel.setLayout(null);
			panel.setBackground(Color.lightGray);			
	        JLabel jLabel1=new JLabel();
			jLabel1.setIcon( new ImageIcon("//10.43.15.29/new/vishak/FEAST/Logo_STR.gif "));
			jLabel1.setBounds(300, 20, 120, 120);
			panel.add(jLabel1);
			
			JLabel l1=new JLabel();
			l1.setText(" Licence Manager.....");
			l1.setBounds(100,00 ,220, 30);
			l1.setForeground(Color.BLACK);
			panel.add(l1);
			
			JLabel userLabel = new JLabel("username");
			userLabel.setBounds(10, 40, 80, 25);
			userLabel.setForeground(Color.black);
			panel.add(userLabel);

			final JTextField userText = new JTextField(20);
			userText.setBounds(100, 40, 160, 25);
			//userText.set
//			userText.setBackground(Color.gray);
			panel.add(userText);
			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setBounds(10, 80, 80, 25);
			passwordLabel.setForeground(Color.black);
			panel.add(passwordLabel);

			final JPasswordField passwordText = new JPasswordField(20);
			passwordText.setBounds(100, 80, 160, 25);
//			passwordText.setBackground(Color.gray);
			panel.add(passwordText);
			
			
			//CANCEL	
			JButton cancelButton = new JButton("cancel");
			cancelButton.setBounds(180, 120, 80, 25);
			panel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
//					TODO Auto-generated method stub
//					JDialog jd=new JDialog();
//					JOptionPane.showMessageDialog(null, "re_enter your details");
					userText.setText("");
					passwordText.setText("");
					frame.setVisible(false);
					
				}
		});

		//ADMIN LOGIN
			
			final JButton loginButton = new JButton("login");
			loginButton.setBounds(60, 120, 80, 25);
			panel.add(loginButton);
			loginButton.addActionListener(new ActionListener() {
				
				@SuppressWarnings("deprecation")
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					//
					String username = new String(userText.getText());
					String password = new String(passwordText.getPassword());
//					JDialog jd=new JDialog();				
					if(e.getSource()==loginButton)
					{
						char[] pwd=passwordText.getPassword();
//						String password=null;
						username = new String(userText.getText());
						password = new String(passwordText.getPassword());
						//option = new String(optionText.getText());
						password=String.copyValueOf(pwd);
						try
						{
							
//							String user=password;
							String host="10.43.15.29";
							int port=1126;
							Socket sc=new Socket(host,port);
//							String str= (username) + " " +  (password);
							InputStream source=sc.getInputStream();
							OutputStream des=sc.getOutputStream();
							final DataInputStream dis=new DataInputStream(source);
							PrintStream ps=new PrintStream(des, true);
//							DataInputStream console=new DataInputStream(System.in);
//							String line=dis.readLine();
		//					response=console.readLine();
//							ps.println(response);	
//							console.close();			
							final String str[]=new String[3];
							//str[0]=option ;
							str[0]=username;
							str[1]=password;
							String st= "LOGIN"+"," + str[0].trim()+ "," +str[1];
							System.out.println("Senting LOGIN request :" + st);
							ps.println(st);
							String status=dis.readLine();				
							System.out.println(status);
							//String opt=dis.readLine();
							//System.out.println(opt);
							//ps.close();
							//dis.close();
							//des.close();
							//source.close();
							//sc.close();
							
							try
							{
								if(status.equals("SUCCESS"))
								{	
								
								
									JOptionPane.showMessageDialog(null, "Logged in successfully!!!!!!","Success", JOptionPane.INFORMATION_MESSAGE);													        
									//ps.println("LOGIN");
									
									System.out.println("LOGIN");
									userText.setText("");
									passwordText.setText("");	
									
									
					
									frame.setVisible(false);
									final JFrame frame0=new JFrame("Licence Manager Home");
									frame0.setUndecorated(true);
									frame0.setSize(350,250);
									frame0.setLocation(300, 300);
									frame0.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
									final JPanel panel=new JPanel();
									frame0.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
									frame0.add(panel);
									//placeComponents(panel);
									frame0.setVisible(true);	
									frame0.setResizable(false);	
									panel.setLayout(null);
									panel.setBackground(Color.PINK);
									JLabel userhead=new JLabel("Licence Management");
									userhead.setBounds(10, 00, 140, 40);
									panel.add(userhead);
									
									
									JLabel adminhead=new JLabel("Admin Management");
									adminhead.setBounds(180, 00, 140, 40);
									panel.add(adminhead);
									

									final JButton add=new JButton("ADD");
									add.setBounds(20, 40, 80, 30);
									panel.add(add);
									
									final JButton view=new JButton("VIEW");
									view.setBounds(20, 80, 80, 30);
									panel.add(view);
									
									final JButton update=new JButton("UPDATE");
									update.setBounds(20, 120, 80, 30);
									panel.add(update);
									

									final JButton delete=new JButton("DELETE");
									delete.setBounds(20, 160, 80, 30);
									panel.add(delete);
									
									final JButton cpwd=new JButton("ChangePassword");
									cpwd.setBounds(180, 40, 150, 30);
									panel.add(cpwd);
									
									JButton logout=new JButton("Logout");
									logout.setBounds(180, 80, 80, 30);
									panel.add(logout);
								
						//ADDING A NEW USER			

									add.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent arg0) {
											// TODO Auto-generated method stub
											if(arg0.getSource()==add)
											{
											
												frame0.setVisible(false);
												final JFrame frame1=new JFrame("Licence Manager Adding a new Licence !!!");
												frame1.setUndecorated(true);
												frame1.setSize(400,300);
												frame1.setLocation(300, 300);
												frame1.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
												JPanel panel=new JPanel();
												frame1.setResizable(false);
												frame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
												frame1.add(panel);
												//placeComponents(panel);
												frame1.setVisible(true);	
												
												
									
											//}
											
												panel.setLayout(null);
												JLabel head=new JLabel("Adding a new Licence!!!");
												head.setBounds(100, 00, 200, 40);
												panel.add(head);
						
												final JLabel name=new JLabel("User_Name");
												name.setBounds(50, 50, 80, 25);
												panel.add(name);
						
												final JTextField userText = new JTextField(20);
												userText.setBounds(140, 50, 160, 25);
												panel.add(userText);
						
												final JLabel licence=new JLabel("Licence");
												licence.setBounds(50, 80, 80, 25);
												panel.add(licence);
						
												final JTextField userText1 = new JTextField(20);
												userText1.setBounds(140, 80, 160, 25);
												panel.add(userText1);
						
												final JLabel department=new JLabel("Department");
												department.setBounds(50, 110, 80, 25);
												panel.add(department);
						
												final JTextField userText2 = new JTextField(20);
												userText2.setBounds(140, 110, 160, 25);
												panel.add(userText2);
						
												final JLabel phoneno = new JLabel("Phone no");
												phoneno.setBounds(50, 140, 80, 25);
												panel.add(phoneno);
						
												final JTextField userText3 = new JTextField(20);
												userText3.setBounds(140, 140, 160, 25);
												panel.add(userText3);
						
												final JLabel mailid  = new JLabel("Mail_Id");
												mailid.setBounds(50, 170, 80, 25);
												panel.add(mailid);
							
												final JTextField userText4 = new JTextField(20);
												userText4.setBounds(140, 170, 160, 25);
												panel.add(userText4);
						
//												
									
												final JButton cancelButton = new JButton("cancel");
												cancelButton.setBounds(180, 200, 80, 25);
												panel.add(cancelButton);
												cancelButton.addActionListener(new ActionListener() {
									
													@Override
													public void actionPerformed(ActionEvent arg0) {
//											TODO Auto-generated method stub
//											JDialog jd=new JDialog();
//											JOptionPane.showMessageDialog(null, "re_enter your details");
														if(arg0.getSource()==cancelButton)
														{
																frame0.setVisible(true);
																frame1.setVisible(false);
														}
											
													}
												});
									final JButton okButton=new JButton("ADD");
									okButton.setBounds(80, 200, 80, 25);
									panel.add(okButton);
									okButton.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent e) {
											// TODO Auto-generated method stub
											//String opt="ADD";
											//ps.println(opt);
											String name = new String(userText.getText());
											String licence = new String(userText1.getText());	
											String department =new String(userText2.getText());
											String phoneno=new String(userText3.getText());
											String mailid=new String(userText4.getText());
//											
											
											if(e.getSource()==okButton)
									        { 
												try
												{
													
													String host="10.43.15.29";
													int port=1126;
													int bytesRead;
												    int current = 0;
													Socket sc=new Socket(host,port);
													InputStream source=sc.getInputStream();
													//InputStream in = sc.getInputStream();	
													OutputStream des=sc.getOutputStream();
													DataInputStream dis=new DataInputStream(source);
													PrintStream ps=new PrintStream(des, true);
													//String req=dis.readLine();
													//System.out.println("request :" +req);
														
													name = new String(userText.getText());
													licence = new String(userText1.getText());	
													department =new String(userText2.getText());
													phoneno=new String(userText3.getText());
													mailid=new String(userText4.getText());
												//	phno1=new String(userText5.getText());
												//	mailid1=new String(userText6.getText());
													//cdate1=new String(userText7.getText());
													//ctime1=new String(userText8.getText());
													//status1=new String(userText9.getText());
													//laststatus1=new String(userText10.getText());
													String st[]=new String[12];
													st[0]=name;
													st[1]=licence;
													st[2]=department;
													st[3]=phoneno;
													st[4]=mailid;
												//	st[5]=phno1;
												//	st[6]=mailid1;
													//st[7]=cdate1;
													//st[8]=ctime1;
													//st[9]=status1;
													//st[10]=laststatus1;
													String str= "ADD" +"," + st[0].trim()+ "," + st[1].trim() + "," + st[2].trim() + "," + st[3].trim() + "," +st[4].trim();
													ps.println(str);
													String opt=dis.readLine(); 
													System.out.println(opt);															
													
														//
													if(opt.equals("SUCCESS"))
													{
														
														JOptionPane.showMessageDialog(null, "new user added!!!","Success", JOptionPane.INFORMATION_MESSAGE);
														frame1.setVisible(false);
														frame0.setVisible(true);
													}
													else
													{
														
														JOptionPane.showMessageDialog(null, "new user not added!!!","Success", JOptionPane.INFORMATION_MESSAGE);
														frame1.setVisible(false);
														frame0.setVisible(true);
													}
													ps.close();
													dis.close();
													des.close();
													source.close();
													sc.close();		//}
									        	}
												catch(Exception e1)
												{
													System.out.println("EXCEPTION OCCURED :" +e1);
															
												}	
											
											}
								
										}
									});
									
									
									//ps.close();
									//dis.close();
									//des.close();
									//source.close();
									//sc.close();
								
								//}
										
								//catch(Exception e)
								//{
								//	System.out.println();
														
								//}
								}		
																			
							}
						});
								 
						//DELETING AN EXISTING USER 
									delete.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent arg0) {
											// TODO Auto-generated method stub
											
													if(arg0.getSource()==delete)
													{
														
														try
														{
															
															String host="10.43.15.29";
															int port=1126;
															int bytesRead;
														    int current = 0;
															Socket sc=new Socket(host,port);
															InputStream source=sc.getInputStream();
															//InputStream in = sc.getInputStream();	
															OutputStream des=sc.getOutputStream();
															DataInputStream dis=new DataInputStream(source);
															PrintStream ps=new PrintStream(des, true);
															//String req=dis.readLine();
															//System.out.println("request :" +req);
															//userID = new String(userText.getText());
															//String st[]=new String[1];
															//st[0]=userID;
															String str="SELECTDELETE";
															ps.println(str);
															String opt=dis.readLine();
															System.out.println(opt);
															String data[];
															opt.trim();
															//int a=opt.length();
															//System.out.println("length is :" +a);
															frame0.setVisible(false);
															final JFrame frame1=new JFrame("Select a user for delete licence");
															frame1.setUndecorated(true);
															frame1.setSize(300,220);
															frame1.setLocation(300, 300);
															frame1.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
															frame1.setResizable(false);
															JPanel panel=new JPanel();
															frame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
															frame1.add(panel);
								//							placeComponents(panel);
															frame1.setVisible(true);
																	
															data=opt.split(",");
															int a=data.length;
															System.out.println("length is :" +a);
															panel.setLayout(new BorderLayout());
														    //final DefaultListModel model = new DefaultListModel();
														    final JList list = new JList(data);
														    JScrollPane pane = new JScrollPane(list);
														    final JButton deleteButton = new JButton("DELETE");
														    //JButton updateButton = new JButton("UPDATE");
														    JButton cancelButton=new JButton("CANCEL");
														    panel.add(pane, BorderLayout.NORTH);
														    panel.add(deleteButton, BorderLayout.WEST);
														    //panel.add(updateButton, BorderLayout.CENTER);
														    panel.add(cancelButton,BorderLayout.EAST);
															frame1.setVisible(true);
															cancelButton.addActionListener(new ActionListener() {
																
																@Override
																public void actionPerformed(ActionEvent arg0) {
																	// TODO Auto-generated method stub
																	frame0.setVisible(true);
																	frame1.setVisible(false);
																	
																}
															});
															
															deleteButton.addActionListener(new ActionListener() {
																
																@Override
																public void actionPerformed(ActionEvent arg0) {
																	// TODO Auto-generated method stub
																	//JOptionPane.showConfirmDialog(null, "Are you sure?", "Example 4",
																	//		  JOptionPane.YES_NO_CANCEL_OPTION);

																	if(arg0.getSource()==deleteButton)
																	{
																		try
																		{
																			int a=JOptionPane.showConfirmDialog(null, "Are you sure want to Delete ?", "DELETE",JOptionPane.YES_NO_OPTION);
																			  
																			//frame3.setVisible(false);
																			if(a==0)
																			{
																			String host="10.43.15.29";
																			int port=1126;
																			int bytesRead;
																		    int current = 0;
																			Socket sc=new Socket(host,port);
																			InputStream source=sc.getInputStream();
																			//InputStream in = sc.getInputStream();	
																			OutputStream des=sc.getOutputStream();
																			DataInputStream dis=new DataInputStream(source);
																			PrintStream ps=new PrintStream(des, true);
																			String del=(String) list.getSelectedValue();
																			System.out.println(del);
																			String delete="DELETE"+","+del;
																			ps.println(delete);
																			String line=dis.readLine();
																			System.out.println(line);
																			
																				
																			
																			
																			if(line.equals("SUCCESS"))
																			{
																				
																				JOptionPane.showMessageDialog(null, "user deleted successfully!!!","Success", JOptionPane.INFORMATION_MESSAGE);
																				frame0.setVisible(true);
																				frame1.setVisible(false);
																			}
																					
																			else
																			{
																				
																				JOptionPane.showMessageDialog(null, "user can not deleted!!!","Success", JOptionPane.INFORMATION_MESSAGE);
																				frame0.setVisible(true);
																				frame1.setVisible(false);
																				
																			}
																					
																			}
																			
																		
																		else	
																		{
																			System.out.println("delete cancelled");
																			frame0.setVisible(true);
																					
																		
																		}
																			
																		}
																		catch(Exception e)
																		{
																			
																		}
																	}
																	
																}
															});
															
															
														}
														catch(Exception e)
														{
															System.out.println(e);
																	
														}
													}
												}
										});
									
									
															
														
															
															
									
					 //UPDATING AN EXSTING USER
								
									
									update.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										
									//frame.setVisible(false);
										if(arg0.getSource()==update)
										{
											
											try
											{
								
												String host="10.43.15.29";
												int port=1126;
												int bytesRead;
											    int current = 0;
												Socket sc=new Socket(host,port);
												InputStream source=sc.getInputStream();
												//InputStream in = sc.getInputStream();	
												OutputStream des=sc.getOutputStream();
												DataInputStream dis=new DataInputStream(source);
												PrintStream ps=new PrintStream(des, true);
												//String req=dis.readLine();
												//System.out.println("request :" +req);
												//userID = new String(userText.getText());
												//String st[]=new String[1];
												//st[0]=userID;
												final String str="SELECTUPDATE";
												ps.println(str);
												String opt=dis.readLine();
												System.out.println(opt);
												String data[];
												opt.trim();
												//int a=opt.length();
												//System.out.println("length is :" +a);
												final JFrame frame2=new JFrame("Select a user for update licence");
												frame2.setUndecorated(true);
												frame2.setSize(300,220);
												frame2.setLocation(300, 300);
												frame2.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
												frame2.setResizable(false);
												JPanel panel=new JPanel();
												frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
												frame2.add(panel);
					//							placeComponents(panel);
												frame0.setVisible(false);
												frame2.setVisible(true);
														
												data=opt.split(",");
												int a=data.length;
												System.out.println("length is :" +a);
												panel.setLayout(new BorderLayout());
											    //final DefaultListModel model = new DefaultListModel();
											    final JList list = new JList(data);
											    JScrollPane pane = new JScrollPane(list);
											    final JButton okButton = new JButton("UPDATE");
											    //JButton updateButton = new JButton("UPDATE");
											    JButton cancelButton=new JButton("CANCEL");
											    panel.add(pane, BorderLayout.NORTH);
											    panel.add(okButton, BorderLayout.WEST);
											    //panel.add(updateButton, BorderLayout.CENTER);
											    panel.add(cancelButton,BorderLayout.EAST);
												frame2.setVisible(true);
												cancelButton.addActionListener(new ActionListener() {
													
													@Override
													public void actionPerformed(ActionEvent arg0) {
														// TODO Auto-generated method stub
														frame0.setVisible(true);
														frame2.setVisible(false);
														
													}
												});
									
									//final JButton okButton=new JButton("UPDATE");
									//okButton.setBounds(60, 100, 80, 25);
									//panel.add(okButton);
									okButton.addActionListener(new ActionListener() {
										
										@Override
									public void actionPerformed(ActionEvent e) {
										

										if(e.getSource()==okButton)
									       { 
											try
											{
													
												String host="10.43.15.29";
												int port=1126;
												int bytesRead;
											    int current = 0;
												Socket sc=new Socket(host,port);
												InputStream source=sc.getInputStream();
												//InputStream in = sc.getInputStream();	
												OutputStream des=sc.getOutputStream();
												DataInputStream dis=new DataInputStream(source);
												PrintStream ps=new PrintStream(des, true);
												String upd=(String) list.getSelectedValue();
												System.out.println(upd);
												String update="SELECTEDUPDATE"+"," +upd;
												ps.println(update);
												
												
												String list=dis.readLine();
												String data[];
												data=list.split(",");
												System.out.println("data received from database");
												System.out.println(data[0]);
												System.out.println(data[1]);
												System.out.println(data[2]);
												System.out.println(data[3]);
												System.out.println(data[4]);
												System.out.println(data[5]);
												System.out.println(data[6]);												
												final JFrame frame3=new JFrame("Updating Licence deatils");
												frame3.setUndecorated(true);
												frame3.setSize(400,350);
												frame3.setLocation(300, 300);
												frame3.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
												JPanel panel=new JPanel();
												frame3.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
												frame3.add(panel);
					//							placeComponents(panel);
												frame3.setVisible(true);
												frame2.setVisible(false);
											
												panel.setLayout(null);
												JLabel head=new JLabel("Updating Licence and Details!!!");
												head.setBounds(40, 00, 200, 40);
												panel.add(head);
											
												JLabel user=new JLabel("Name");
												user.setBounds(10, 50, 80, 25);
												panel.add(user);
					
												final JTextField userText = new JTextField(data[0]);
												userText.setBounds(100, 50, 160, 25);
												panel.add(userText);
					
												JLabel licence=new JLabel("Licence");
												licence.setBounds(10, 80, 80, 25);
												panel.add(licence);
					
												final JTextField userText1 = new JTextField(data[1]);
												userText1.setBounds(100, 80, 160, 25);
												panel.add(userText1);
											
												JLabel dept=new JLabel("Department");
												dept.setBounds(10, 110, 80, 25);
												panel.add(dept);
					
												final JTextField userText2 = new JTextField(data[2]);
												userText2.setBounds(100, 110, 160, 25);
												panel.add(userText2);
					
												JLabel phno = new JLabel("Ph_No");
												phno.setBounds(10, 140, 80, 25);
												panel.add(phno);
					
												final JTextField userText3 = new JTextField(data[3]);
												userText3.setBounds(100, 140, 160, 25);
												panel.add(userText3);
												
												JLabel mailid  = new JLabel("Mail_ID");
												mailid.setBounds(10, 170, 80, 25);
												panel.add(mailid);
						
												final JTextField userText4 = new JTextField(data[4]);
												userText4.setBounds(100, 170, 160, 25);
												panel.add(userText4);
				
												JLabel cdate = new JLabel("C_Date");
												cdate.setBounds(10, 200, 80, 25);
												panel.add(cdate);
						
												final JTextField userText5 = new JTextField(data[5]);
												userText5.setBounds(100, 200, 160, 25);
												panel.add(userText5);
				
												JLabel ctime  = new JLabel("C_Time");
												ctime.setBounds(10, 230, 80, 25);
												panel.add(ctime);
											
												final JTextField userText6 = new JTextField(data[6]);
												userText6.setBounds(100, 230, 160, 25);
												panel.add(userText6);
											
																								
												final JButton cancelButton = new JButton("cancel");
												cancelButton.setBounds(180, 260, 80, 25);
												panel.add(cancelButton);
												cancelButton.addActionListener(new ActionListener() {
									
												@Override
												public void actionPerformed(ActionEvent arg0) {
//												TODO Auto-generated method stub
//												JDialog jd=new JDialog();
//												JOptionPane.showMessageDialog(null, "re_enter your details");
														if(arg0.getSource()==cancelButton)
														{
															frame0.setVisible(true);
															frame3.setVisible(false);
														}
											
													}
												});
													

													final JButton okButton=new JButton("UPDATE");
													okButton.setBounds(80, 260, 80, 25);
													panel.add(okButton);
													okButton.addActionListener(new ActionListener() {
										
														@Override
														public void actionPerformed(ActionEvent e) {
															
															if(e.getSource()==okButton)
															{
																String username = new String(userText.getText());
																String licence = new String(userText1.getText());	
																String dept=new String(userText2.getText());
																String phno=new String(userText3.getText());
																String mailid=new String(userText4.getText());
																String cdate=new String(userText5.getText());
																String ctime=new String(userText6.getText());
																
															
															try
															{
																

																int a=JOptionPane.showConfirmDialog(null, "Are you sure want to update ?", "UPDATE",
																		  JOptionPane.YES_NO_OPTION);
																
																if(a==0)
																{
																String host="10.43.15.29";
																int port=1126;
																int bytesRead;
															    int current = 0;
																Socket sc=new Socket(host,port);
																InputStream source=sc.getInputStream();
																//InputStream in = sc.getInputStream();	
																OutputStream des=sc.getOutputStream();
																DataInputStream dis=new DataInputStream(source);
																PrintStream ps=new PrintStream(des, true);
																username = new String(userText.getText());
																licence = new String(userText1.getText());	
																dept=new String(userText2.getText());
																phno=new String(userText3.getText());
																mailid=new String(userText4.getText());
																cdate=new String(userText5.getText());
																ctime=new String(userText6.getText());															
																String st[]=new String[12];
																st[0]=username;
																st[1]=licence;
																st[2]=dept;
																st[3]=phno;
																st[4]=mailid;
																st[5]=cdate;
																st[6]=ctime;
																
																String str= "UPDATE" +"," + st[0].trim()+ "," + st[1].trim() + "," + st[2].trim() + "," + st[3].trim() + "," +st[4].trim() + "," + st[5].trim() + "," +st[6];
																ps.println(str);
																String opt=dis.readLine(); 
																System.out.println(opt);
																		

																	
																if(opt.equals("SUCCESS"))
																{
																	
																	JOptionPane.showMessageDialog(null, " user updated successfully!!!","Success", JOptionPane.INFORMATION_MESSAGE);
																	frame0.setVisible(true);
																	frame3.setVisible(false);
																}
																else
																{
																	frame0.setVisible(true);
																	JOptionPane.showMessageDialog(null, "user not updated successfully!!!","Failure", JOptionPane.INFORMATION_MESSAGE);
																	frame3.setVisible(false);
																}
																ps.close();
																dis.close();
																des.close();
																source.close();
																sc.close();	
																}
																else
																{
																	//frame2.setVisible(true);
																	frame0.setVisible(true);
																	System.out.println("not updating going back to main page");
																			
																}//}
												        	}
															catch(Exception e1)
															{
																System.out.println(e1);
																frame3.setVisible(false);		
															}
															  
															
																
														}
												
													}
												});
											
									
											}
											catch(Exception e1)
											{
												System.out.println(e1);
														
											}
									     }
									}
								});
											}
											
											catch(Exception e1)
											{
												System.out.println(e1);
														
											}
										}
									
									}
								});
							
				
									
					//VIEWING THE USERID			
								
								view.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										
										if(arg0.getSource()==view)
										{
											
											try
											{
													
												String host="10.43.15.29";
												int port=1126;
												int bytesRead;
											    int current = 0;
												Socket sc=new Socket(host,port);
												InputStream source=sc.getInputStream();
												//InputStream in = sc.getInputStream();	
												OutputStream des=sc.getOutputStream();
												DataInputStream dis=new DataInputStream(source);
												PrintStream ps=new PrintStream(des, true);
												//String req=dis.readLine();
												//System.out.println("request :" +req);
												//userID = new String(userText.getText());
												//String st[]=new String[1];
												//st[0]=userID;
												String str="VIEW";
												ps.println(str);
												String opt=dis.readLine();
												System.out.println(opt);
												String data[];
												opt.trim();
												//int a=opt.length();
												//System.out.println("length is :" +a);
												final JFrame frame4=new JFrame("Select a user for View the Licence Details");
												frame4.setUndecorated(true);
												frame4.setSize(300,220);
												frame4.setLocation(300, 300);
												frame4.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
												frame4.setResizable(false);
												JPanel panel=new JPanel();
												frame4.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
												frame4.add(panel);
					//							placeComponents(panel);
												frame0.setVisible(false);
												frame4.setVisible(true);
														
												data=opt.split(",");
												int a=data.length;
												System.out.println("length is :" +a);
												panel.setLayout(new BorderLayout());
											    //final DefaultListModel model = new DefaultListModel();
											    final JList list = new JList(data);
											    JScrollPane pane = new JScrollPane(list);
											    //final JButton deleteButton = new JButton("DELETE");
											   // JButton updateButton = new JButton("UPDATE");
											    JButton cancelButton=new JButton("CLOSE");
											    final JButton view=new JButton("VIEW");
											    
											    //cancelButton.setBounds(60,400,80,25);
											    panel.add(cancelButton);
											    panel.add(pane, BorderLayout.NORTH);
											   // panel.add(deleteButton, BorderLayout.WEST);
											   // panel.add(updateButton, BorderLayout.CENTER);
											    panel.add(view,BorderLayout.WEST);
											    panel.add(cancelButton,BorderLayout.EAST);
												frame4.setVisible(true);
												cancelButton.addActionListener(new ActionListener() {
													
													@Override
													public void actionPerformed(ActionEvent arg0) {
														// TODO Auto-generated method stub
														
														frame0.setVisible(true);
														frame4.setVisible(false);
														
													}
												});
												
												view.addActionListener(new ActionListener() {
													
													@Override
													public void actionPerformed(ActionEvent arg0) {
														// TODO Auto-generated method stub
													if(arg0.getSource()==view)
													{
															
													try
													{
														String host="10.43.15.29";
														int port=1126;
														int bytesRead;
													    int current = 0;
														Socket sc=new Socket(host,port);
														InputStream source=sc.getInputStream();
														//InputStream in = sc.getInputStream();	
														OutputStream des=sc.getOutputStream();
														DataInputStream dis=new DataInputStream(source);
														PrintStream ps=new PrintStream(des, true);
														String view=(String) list.getSelectedValue();
														System.out.println(view);
														String viewuser="VIEWDETAILS"+"," +view;
														ps.println(viewuser);
														
														
														String list=dis.readLine();
														String data[];
														data=list.split(",");
														System.out.println("data received from database");
														System.out.println(data[0]);
														System.out.println(data[1]);
														System.out.println(data[2]);
														System.out.println(data[3]);
														System.out.println(data[4]);
														System.out.println(data[5]);
														System.out.println(data[6]);
//														System.out.println(data[7]);
//														System.out.println(data[8]);
//														System.out.println(data[9]);
//														System.out.println(data[10]);
														final JFrame frame5=new JFrame("Displaying Licence details");
														frame5.setUndecorated(true);
														frame5.setSize(350,350);
														frame5.setLocation(300, 300);
														frame5.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
														JPanel panel=new JPanel();
														frame5.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
														frame5.add(panel);
							//							placeComponents(panel);
														frame5.setVisible(true);
														frame4.setVisible(false);
													
														panel.setLayout(null);
														JLabel head=new JLabel("Details of "+ data[0] +" !!!");
														head.setBounds(40, 00, 200, 40);
														panel.add(head);
													
														JLabel name=new JLabel("Name");
														name.setBounds(60, 50, 80, 25);
														panel.add(name);
							
														JLabel userText=new JLabel(data[0]);
														userText.setBounds(150, 50, 160, 25);
														panel.add(userText);
							
														JLabel licence=new JLabel("Licence");
														licence.setBounds(60, 80, 80, 25);
														panel.add(licence);
							
														JLabel userText1=new JLabel(data[1]);
														userText1.setBounds(150, 80, 160, 25);
														panel.add(userText1);
													
														JLabel dept=new JLabel("Department");
														dept.setBounds(60, 110, 80, 25);
														panel.add(dept);
							
														JLabel userText2=new JLabel(data[2]);
														userText2.setBounds(150, 110, 160, 25);
														panel.add(userText2);
							
														JLabel phno = new JLabel("Phone No.");
														phno.setBounds(60, 140, 80, 25);
														panel.add(phno);
							
														JLabel userText3 = new JLabel(data[3]);
														userText3.setBounds(150, 140, 160, 25);
														panel.add(userText3);
														
														JLabel mailid  = new JLabel("Mail_Id");
														mailid.setBounds(60, 170, 80, 25);
														panel.add(mailid);
								
														JLabel userText4 = new JLabel(data[4]);
														userText4.setBounds(150, 170, 160, 25);
														panel.add(userText4);
						
														JLabel cdate = new JLabel("C_Date");
														cdate.setBounds(60, 200, 80, 25);
														panel.add(cdate);
								
														JLabel userText5 = new JLabel(data[5]);
														userText5.setBounds(150, 200, 160, 25);
														panel.add(userText5);
						
														JLabel ctime = new JLabel("C_Time");
														ctime.setBounds(60, 230, 80, 25);
														panel.add(ctime);
													
														JLabel userText6 = new JLabel(data[6]);
														userText6.setBounds(150, 230, 160, 25);
														panel.add(userText6);
																											
														final JButton cancelButton = new JButton("CLOSE");
														cancelButton.setBounds(100, 260, 80, 25);
														panel.add(cancelButton);
														cancelButton.addActionListener(new ActionListener() {
											
														@Override
														public void actionPerformed(ActionEvent arg0) {
//														TODO Auto-generated method stub
//														JDialog jd=new JDialog();
//														JOptionPane.showMessageDialog(null, "re_enter your details");
																if(arg0.getSource()==cancelButton)
																{
																	frame0.setVisible(true);
																	frame5.setVisible(false);
																}
													
															}
														});
													}
													catch(Exception e)
													{
														System.out.println();
																
													}
												}
														
													}
												});
													
													
											
												
												
											}
											catch(Exception e)
											{
												System.out.println();
														
											}
											
										}
										
									}
								});
								
						    
						  
								
					//CHANGING ADMIN PASSWORD 
								
								
									cpwd.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent arg0) {
											// TODO Auto-generated method stub
											
									System.out.println("Changing administrator password ");
									final JFrame frame5=new JFrame("Administrator Change Password !!!");
									frame5.setUndecorated(true);
									frame5.setSize(350,300);
									frame5.setLocation(300, 300);
									frame5.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
									JPanel panel=new JPanel();
									frame5.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
									frame5.setResizable(false);
									frame5.add(panel);
//									placeComponents(panel);
									frame0.setVisible(false);
									frame5.setVisible(true);	
									panel.setLayout(null);
									
									JLabel userLabel3 = new JLabel("userID");
									userLabel3.setBounds(10, 20, 80, 25);
									panel.add(userLabel3);

									final JTextField userText3 = new JTextField(20);
									userText3.setBounds(120, 20, 160, 25);
									panel.add(userText3);
								
									JLabel passwordLabel = new JLabel("Old Password");
									passwordLabel.setBounds(10, 60, 80, 25);
									panel.add(passwordLabel);

									final JPasswordField userText = new JPasswordField(20);
									userText.setBounds(120, 60, 160, 25);
									panel.add(userText);
								
									JLabel passwordLabel1 = new JLabel("New Password");
									passwordLabel1.setBounds(10, 100, 160, 25);
									panel.add(passwordLabel1);
								
									final JPasswordField userText1 = new JPasswordField(20);
									userText1.setBounds(120, 100, 160, 25);
									panel.add(userText1);
								
								
									JLabel passwordLabel2 = new JLabel("confirm Password");
									passwordLabel2.setBounds(10, 140, 220, 25);
									panel.add(passwordLabel2);

									final JPasswordField userText2 = new JPasswordField(20);
									userText2.setBounds(120, 140, 160, 25);
									panel.add(userText2);
								
									final JButton submitButton=new JButton("OK");
									submitButton.setBounds(40, 180, 80, 25);
									panel.add(submitButton);
									//ps.println("changingpassword");
									submitButton.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent arg0) {
											// TODO Auto-generated method stub
											
										
											
											if(arg0.getSource()==submitButton)
											{
												System.out.println("sending");
												String username=new String(userText3.getText());
												String oldpwd=new String(userText.getText());
												String newpwd= new String(userText1.getText());
												String password=new String(userText2.getText());
												//if(!oldpwd.equals(password))
												//{
												//JDialog jd=new JDialog();
												if(newpwd.equals(password))
												{	
													System.out.println("matching");
													try
													{
														String host="10.43.15.29";
														int port=1126;
														Socket sc=new Socket(host,port);
														String str[]=new String[3];
														str[0]=username;
														str[1]=password;
														String st= "changepassword" +"," +str[0].trim() + "," + str[1].trim();
														//System.out.println("Senting password changing request:" +st);
														InputStream source=sc.getInputStream();
														OutputStream des=sc.getOutputStream();
														DataInputStream dis=new DataInputStream(source);
														PrintStream ps=new PrintStream(des, true);
//														DataInputStream console=new DataInputStream(System.in);
//														String line=dis.readLine();
														String s="changepassword";
														ps.println(st);
														System.out.println("Sending password changing request:" +s);
					 //									response=console.readLine();
//														ps.println(response);
//														console.close();			
														//ps.println(s);
														String option=dis.readLine();
														System.out.println(option);
																
														String status=dis.readLine();				
														System.out.println(status);
														if(status.equals("SUCCESS"))
														{
															frame0.setVisible(true);
															JOptionPane.showMessageDialog(null, "passsword changed successfully");
															frame5.setVisible(false);
														}
														
														else
														{
															JOptionPane.showMessageDialog(null, "error !!! check username and password ");
														}
														ps.close();
														dis.close();
														des.close();
														source.close();
														sc.close();
													}
												
													catch(Exception e)
													{
														System.out.println("Exception" +e);
													}														
												}
												else
												{
													JOptionPane.showMessageDialog(null, "check username and password ");	
												}
												//}
											}
										}
									});

									
									
									final JButton admincancelButton=new JButton("CANCEL");
									admincancelButton.setBounds(150, 180, 100, 25);
									panel.add(admincancelButton);	
									admincancelButton.addActionListener(new ActionListener() {
										
										@Override
										public void actionPerformed(ActionEvent arg0) {
											// TODO Auto-generated method stub
											
											frame0.setVisible(true);
											frame5.setVisible(false);
										}
									});
									
										}
									
									});
								
							
						
						
						    
						    //LOGOUT ADMIN SESSION
						   
								//JButton logout=new JButton();
						    	 logout.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent arg0) {
									 //TODO Auto-generated method stub
									int a=JOptionPane.showConfirmDialog(null, "Are you sure want to logout from the admin?", "LOGOUT",
											  JOptionPane.YES_NO_OPTION);

									if(a==0)
									{
								
									//JOptionPane.showMessageDialog(null, "Logging out from the admin!!!","Success", JOptionPane.INFORMATION_MESSAGE);
									frame0.setVisible(false);	
									}
									else
									{
										frame0.setVisible(true);
									}
								}
							});
						}
						    
						//    deleting.addActionListener(this);

						 //   adding.addActionListener(this);
						 //   close.addActionListener(this);
						  //  JButton submit1 = new JButton("OK");
				
		//ADMIN USERNAME OR PASSWORD INVALID						
							
							else
							{
								JOptionPane.showMessageDialog(null, "Invalid username and password ");
								frame.setVisible(true);
								userText.setText("");
								passwordText.setText("");
							}
						}
						catch(Exception e1)
						{
							System.out.println(e1);
						}
							
						ps.close();
						dis.close();
						des.close();
						source.close();
						sc.close();
		 						
					}
					catch(Exception ex)
					{
						System.out.println(ex);
					}
				}
					
				
			}
			//}
				//catch(Exception e1)
				//{
				//}
		//}//try
			//catch(Exception e1)
			//{
			//	
			//}
		//}
	//}
	
		});			
			
			
	}
		
}