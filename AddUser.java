import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;

public class AddUser {

	public boolean addUser(String username, String logonName, String password) {
		try {
			Hashtable<Object, Object> properties = new Hashtable<Object, Object>();

			properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			properties.put(Context.PROVIDER_URL, "ldap://localhost:389");
			properties.put(Context.SECURITY_AUTHENTICATION,"simple");
			properties.put(Context.SECURITY_PRINCIPAL,"cn=Administrator,cn=Users,dc=admin,dc=local"); 
			properties.put(Context.SECURITY_CREDENTIALS,"Siva123123");
			DirContext context = new InitialDirContext(properties);

			Attributes container = new BasicAttributes();

			Attribute objClass = new BasicAttribute("objectClass","User");
			Attribute logon = new BasicAttribute("sAMAccountName", logonName);
			Attribute userPassword = new BasicAttribute("userpassword", password);

			container.put(objClass);
			container.put(logon);
			container.put(userPassword);


			context.bind("cn="+username+",cn=Users,dc=admin,dc=local",null, container);
			return true;

		}catch (NameAlreadyBoundException ae) {
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}