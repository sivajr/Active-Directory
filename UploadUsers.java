import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;

public class UploadUsers {

	public String[] uploadUserRecords(String[][] userRecords, int count) {
		String[] errMsg = new String[100];

		int itr=0;

		Hashtable<Object, Object> properties = new Hashtable<Object, Object>();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		properties.put(Context.PROVIDER_URL, "ldap://localhost:389");
		properties.put(Context.SECURITY_AUTHENTICATION,"simple");
		properties.put(Context.SECURITY_PRINCIPAL,"cn=Administrator,cn=Users,dc=admin,dc=local"); 
		properties.put(Context.SECURITY_CREDENTIALS,"Siva123123");
		
		try{
			DirContext context = new InitialDirContext(properties);
			for (int i=0; i<count; i++) {
				try {
				
					Attributes container = new BasicAttributes();
					Attribute fname,lname,in,logon,userPassword,dname,userJob,officeName,deptName,phNo,mail,website,pbNo,cname,st,pcode;

					Attribute objClass = new BasicAttribute("objectClass","User");
					container.put(objClass);

					if(!userRecords[i][1].equals("")){
						fname = new BasicAttribute("givenName", userRecords[i][1]);
						container.put(fname);
					}
					if(!userRecords[i][2].equals("")){
						lname = new BasicAttribute("sn", userRecords[i][2]);
						container.put(lname);
					}
					if(!userRecords[i][3].equals("")){
						in = new BasicAttribute("initials", userRecords[i][3]);
						container.put(in);
					}
					if(!userRecords[i][4].equals("")){
						logon = new BasicAttribute("sAMAccountName", userRecords[i][4]);
						container.put(logon);
					}
					if(!userRecords[i][5].equals("")){
						userPassword = new BasicAttribute("userpassword", userRecords[i][5]);
						container.put(userPassword);
					}
					if(!userRecords[i][6].equals("")){
						dname = new BasicAttribute("displayName", userRecords[i][6]);
						container.put(dname);
					}
					if(!userRecords[i][7].equals("")){
						userJob = new BasicAttribute("title", userRecords[i][7]);
						container.put(userJob);
					}
					if(!userRecords[i][8].equals("")){
						officeName = new BasicAttribute("physicalDeliveryOfficeName",userRecords[i][8]);
						container.put(officeName);
					}
					if(!userRecords[i][9].equals("")){
						deptName = new BasicAttribute("department", userRecords[i][9]);
						container.put(deptName);
					}
					if(!userRecords[i][10].equals("")){
						phNo = new BasicAttribute("telephoneNumber", userRecords[i][10]);
						container.put(phNo);
					}
					if(!userRecords[i][11].equals("")){
						mail = new BasicAttribute("mail", userRecords[i][11]);
						container.put(mail);
					}
					if(!userRecords[i][12].equals("")){
						website = new BasicAttribute("wWWHomePage", userRecords[i][12]);
						container.put(website);
					}
					if(!userRecords[i][13].equals("")){
						pbNo = new BasicAttribute("postOfficeBox", userRecords[i][13]);
						container.put(pbNo);
					}
					if(!userRecords[i][14].equals("")){
						cname = new BasicAttribute("l", userRecords[i][14]);
						container.put(cname);
					}
					if(!userRecords[i][15].equals("")){
						st = new BasicAttribute("st", userRecords[i][15]);
						container.put(st);
					}
					if(!userRecords[i][16].equals("")){
						pcode = new BasicAttribute("postalCode", userRecords[i][16]);
						container.put(pcode);
					}
					
					context.bind("cn="+userRecords[i][0]+",cn=Users,dc=admin,dc=local", null, container);
				}catch (Exception e) {
					errMsg[itr++] = userRecords[i][0];
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return errMsg;
	}
}