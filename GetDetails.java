import java.util.Hashtable;
import java.util.Enumeration;
import javax.naming.*;
import javax.naming.directory.*;

public class GetDetails {

	public String[] userDetails(String username) {
		String[] details = new String[15];
		try {
			Hashtable<Object, Object> properties = new Hashtable<Object, Object>();

			properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			properties.put(Context.PROVIDER_URL, "ldap://localhost:389");
			properties.put(Context.SECURITY_AUTHENTICATION, "simple");
			properties.put(Context.SECURITY_PRINCIPAL, "cn=Administrator,cn=Users,dc=admin,dc=local");
			properties.put(Context.SECURITY_CREDENTIALS, "Siva123123");
			DirContext context = new InitialDirContext(properties);

			Attributes container = context.getAttributes("cn="+username+",cn=Users,dc=admin,dc=local");

			if (container != null){
				Attribute atr = (Attribute) container.get("givenName");
				Enumeration vals;
				if(atr != null){
					vals = atr.getAll();
					details[0] = (String)vals.nextElement();
				}
				else
					details[0] = "";

				atr = (Attribute) container.get("sn");
				if(atr != null){
					vals = atr.getAll();
					details[1] = (String)vals.nextElement();
				}
				else
					details[1] = "";

				atr = (Attribute) container.get("initials");
				if(atr != null){
					vals = atr.getAll();
					details[2] = (String)vals.nextElement();
				}
				else
					details[2] = "";
				
				atr = (Attribute) container.get("sAMAccountName");
				if(atr != null){
					vals = atr.getAll();
					details[3] = (String)vals.nextElement();
				}
				else
					details[3] = "";
				
				atr = (Attribute) container.get("displayName");
				if(atr != null){
					vals = atr.getAll();
					details[4] = (String)vals.nextElement();
				}
				else
					details[4] = "";
				
				atr = (Attribute) container.get("title");
				if(atr != null){
					vals = atr.getAll();
					details[5] = (String)vals.nextElement();
				}
				else
					details[5] = "";
				
				atr = (Attribute) container.get("physicalDeliveryOfficeName");
				if(atr != null){
					vals = atr.getAll();
					details[6] = (String)vals.nextElement();
				}
				else
					details[6] = "";
				
				atr = (Attribute) container.get("title");
				if(atr != null){
					vals = atr.getAll();
					details[7] = (String)vals.nextElement();
				}
				else
					details[7] = "";
				
				atr = (Attribute) container.get("telephoneNumber");
				if(atr != null){
					vals = atr.getAll();
					details[8] = (String)vals.nextElement();
				}
				else
					details[8] = "";
				
				atr = (Attribute) container.get("mail");
				if(atr != null){
					vals = atr.getAll();
					details[9] = (String)vals.nextElement();
				}
				else
					details[9] = "";
				
				atr = (Attribute) container.get("wWWHomePage");
				if(atr != null){
					vals = atr.getAll();
					details[10] = (String)vals.nextElement();
				}
				else
					details[10] = "";
				
				atr = (Attribute) container.get("postOfficeBox");
				if(atr != null){
					vals = atr.getAll();
					details[11] = (String)vals.nextElement();
				}
				else
					details[11] = "";
				
				atr = (Attribute) container.get("l");
				if(atr != null){
					vals = atr.getAll();
					details[12] = (String)vals.nextElement();
				}
				else
					details[12] = "";
				
				atr = (Attribute) container.get("st");
				if(atr != null){
					vals = atr.getAll();
					details[13] = (String)vals.nextElement();
				}
				else
					details[13] = "";
				
				atr = (Attribute) container.get("postalCode");
				if(atr != null){
					vals = atr.getAll();
					details[14] = (String)vals.nextElement();
				}
				else
					details[14] = "";
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return details;
	}
}