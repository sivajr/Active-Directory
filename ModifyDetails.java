import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;

public class ModifyDetails {

	public void modifyUser(String userName,String firstName,String lastName,String initials,String logon,
        String displayName,String job,String office,String dept,String pNumber,String email,
        String webpage,String postNo,String city,String state,String pinCode) {
		try {
			Hashtable<Object, Object> properties = new Hashtable<Object, Object>();

			properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			properties.put(Context.PROVIDER_URL, "ldap://localhost:389");
			properties.put(Context.SECURITY_AUTHENTICATION, "simple");
			properties.put(Context.SECURITY_PRINCIPAL, "cn=Administrator,cn=Users,dc=admin,dc=local");
			properties.put(Context.SECURITY_CREDENTIALS, "Siva123123");
			DirContext context = new InitialDirContext(properties);

			Attributes container = new BasicAttributes();
			if(firstName.equals(""))
				firstName = null;
			if(lastName.equals(""))
				lastName = null;
			if(initials.equals(""))
				initials = null;
			if(logon.equals(""))
				logon = null;
			if(displayName.equals(""))
				displayName = null;
			if(job.equals(""))
				job = null;
			if(office.equals(""))
				office = null;
			if(dept.equals(""))
				dept = null;
			if(pNumber.equals(""))
				pNumber = null;
			if(email.equals(""))
				email = null;
			if(webpage.equals(""))
				webpage = null;
			if(postNo.equals(""))
				postNo = null;
			if(city.equals(""))
				city = null;
			if(state.equals(""))
				state = null;
			if(pinCode.equals(""))
				pinCode = null;
			
			Attribute fname = new BasicAttribute("givenName", firstName);
			Attribute lname = new BasicAttribute("sn", lastName);
			Attribute in = new BasicAttribute("initials", initials);
			Attribute logonName = new BasicAttribute("sAMAccountName", logon);
			Attribute dname = new BasicAttribute("displayName", displayName);
			Attribute userJob = new BasicAttribute("title", job);
			Attribute officeName = new BasicAttribute("physicalDeliveryOfficeName",office);
			Attribute deptName = new BasicAttribute("department", dept);
			Attribute phNo = new BasicAttribute("telephoneNumber", pNumber);
			Attribute mail = new BasicAttribute("mail", email);
			Attribute website = new BasicAttribute("wWWHomePage", webpage);
			Attribute pbNo = new BasicAttribute("postOfficeBox", postNo);
			Attribute cname = new BasicAttribute("l", city);
			Attribute st = new BasicAttribute("st", state);
			Attribute pcode = new BasicAttribute("postalCode", pinCode);
			
			container.put(fname);
			container.put(lname);
			container.put(in);
			container.put(logonName);
			container.put(dname);
			container.put(userJob);
			container.put(officeName);
			container.put(deptName);
			container.put(phNo);
			container.put(mail);
			container.put(website);
			container.put(pbNo);
			container.put(cname);
			container.put(st);
			container.put(pcode);
			
			context.modifyAttributes("cn="+userName+",cn=Users,dc=admin,dc=local", DirContext.REPLACE_ATTRIBUTE, container);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}